package com.crlesage.twittersearchapp.activities;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.crlesage.twittersearchapp.R;
import com.crlesage.twittersearchapp.adapters.TwitterFeedAdapter;
import com.crlesage.twittersearchapp.customUI.CustomRecyclerView;
import com.crlesage.twittersearchapp.interfaces.Constants;
import com.crlesage.twittersearchapp.presenters.TwitterFeedPresenter;
import com.crlesage.twittersearchapp.utils.ApiService;
import com.crlesage.twittersearchapp.utils.DataHelper;
import com.crlesage.twittersearchapp.utils.HashtagSuggestion;
import com.crlesage.twittersearchapp.utils.SharedPref;
import com.crlesage.twittersearchapp.utils.Util;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetView extends AppCompatActivity {

    private static final String BUNDLE_RECYCLER_LAYOUT = "TweetView.recycler.layout";
    @BindView(R.id.floating_search_view)
    protected FloatingSearchView mSearchView;
    @BindView(R.id.twitter_feed_list)
    protected CustomRecyclerView mTwitterFeed;
    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    TwitterFeedPresenter mTwitterFeedPresenter;
    protected TwitterFeedAdapter mTwitterFeedAdapter;
    protected StaggeredGridLayoutManager mLayoutManager;
    ApiService mApiService;
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;
    private boolean isDefaultTwitterSearch;
    public static Typeface robotoFont;

    // Variables for endless scrolling
    public static boolean loading = true;
    int pastVisibleItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_view);

        // Initialize default feed for first creation
        isDefaultTwitterSearch = false;

        // Bind views
        ButterKnife.bind(this);

        // Create ApiService for calls
        mApiService = new ApiService();

        // Initialize and set fonts to text
        initializeTypeFace();

        // Set up recycler view of Twitter Feed
        setUpRecyclerView();

        // Set up the Search Bar
        setupFloatingSearch();
    }

    // Initialize Typeface
    public void initializeTypeFace() {
        AssetManager am = getApplicationContext().getApplicationContext().getAssets();
        robotoFont = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Roboto-Regular.ttf"));
    }

    private void setUpRecyclerView() {
        // Add padding to the swipe refresh icon
        int top_padding = 400;
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, top_padding);
        // sets the colors used in the refresh animation
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // SwipeRefreshLayout onRefresh method (if triggered by User)
                onStart();
            }
        });

        // Setting up Twitter Recycler variables
        mTwitterFeed.setHasFixedSize(true);
        mTwitterFeed.setItemViewCacheSize(20);
        mTwitterFeed.setDrawingCacheEnabled(true);
        mTwitterFeed.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        // Add an "animation" to help prevent the "blinking" of items in feed
        RecyclerView.ItemAnimator animator = mTwitterFeed.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        // Use a StaggeredGrid layout manager for recycler view
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new StaggeredGridLayoutManager(Constants.SPAN_COUNT_LANDSCAPE, Constants.ORIENTATION_VERTICAL);
        } else {
            mLayoutManager = new StaggeredGridLayoutManager(Constants.SPAN_COUNT_PORTRAIT, Constants.ORIENTATION_VERTICAL);
        }
        mTwitterFeed.setLayoutManager(mLayoutManager);

        // Initialize Adapter for recycler view
        mTwitterFeedAdapter = new TwitterFeedAdapter(this);

        // Attach adapter to recycler view
        mTwitterFeed.setAdapter(mTwitterFeedAdapter);

        // Add endless scrolling to recycler view (detect when needing to fetch more data from
        // pagination
        addEndListScrolling();
    }

    // Got code from:
    // http://stackoverflow.com/questions/29079478/how-to-implement-endless-scrolling-using-staggeredlayoutmanager
    private void addEndListScrolling() {
        mTwitterFeed.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    int[] firstVisibleItems = null;
                    firstVisibleItems = mLayoutManager.findFirstVisibleItemPositions(firstVisibleItems);
                    if (firstVisibleItems != null && firstVisibleItems.length > 0) {
                        pastVisibleItems = firstVisibleItems[0];
                    }

                    if (loading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loading = false;
                            Log.d(Util.TAG, "LOAD NEXT ITEM");
                            //Do pagination.. i.e. fetch new data
                            if (mTwitterFeedPresenter.getCurrentTwitterResponse() == null) {
                                Log.d(Util.TAG, "Response is null");
                                return;
                            }
                            if (mTwitterFeedPresenter.getCurrentTwitterResponse().getSearch_metadata().getNext_results() != null
                                    && !mTwitterFeedPresenter.getCurrentTwitterResponse().getSearch_metadata().getNext_results().equals("")) {
                                getMoreTweets();
                            } else {
                                Log.d(Util.TAG, "No more Tweets!");
                            }
                        }
                    }
                }
            }
        });
    }

    private void setupFloatingSearch() {
        EditText editText = (EditText) mSearchView.findViewById(R.id.search_bar_text);
        editText.setTypeface(robotoFont);
        Util.setCursorDrawableColor(editText, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                } else {
                    mSearchView.showProgress();

                    // Simulates a query call to a data source
                    DataHelper.findAllSuggestions(TweetView.this, newQuery, 5, FIND_SUGGESTION_SIMULATED_DELAY, new DataHelper.OnFindSuggestionsListener() {

                        @Override
                        public void onResults(List<HashtagSuggestion> results) {

                            // This will swap the data and render the collapse/expand animations as necessary
                            mSearchView.swapSuggestions(results);

                            // Let the users know that the background process has completed
                            mSearchView.hideProgress();
                        }
                    });
                }
                Log.d(Util.TAG, "onSearchTextChanged()");
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

                HashtagSuggestion hashtagSuggestion = (HashtagSuggestion) searchSuggestion;
                hashtagSuggestion.setIsHistory(true);
                SharedPref.addSearchHistory(getApplicationContext(), hashtagSuggestion);

                mTwitterFeed.smoothScrollToPosition(0);
                getTweets(hashtagSuggestion.getBody());
                Log.d(Util.TAG, "onSuggestionClicked()");
            }

            @Override
            public void onSearchAction(String query) {

                HashtagSuggestion hashtagSuggestion = new HashtagSuggestion(query);
                hashtagSuggestion.setIsHistory(true);
                SharedPref.addSearchHistory(getApplicationContext(), hashtagSuggestion);

                mTwitterFeed.smoothScrollToPosition(0);
                getTweets(query);
                Log.d(Util.TAG, "onSearchAction()");
            }
        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                mSearchView.clearQuery();
                mSearchView.hideProgress();

                // Show suggestions when search bar gains focus (typically history suggestions)
                mSearchView.swapSuggestions(DataHelper.getSuggestionsAndHistory(getApplicationContext(), 3));

                Log.d(Util.TAG, "onFocus()");
            }

            @Override
            public void onFocusCleared() {

                Log.d(Util.TAG, "onFocusCleared()");
            }
        });

        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.action_voice_rec) {
                    Toast.makeText(getApplicationContext(), "Voice recognition in development.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // This callback is used when the suggestion is bound to the "Search" view display
        // Also where I can modify the results icon/text value, etc.
        mSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon,
                                         TextView textView, SearchSuggestion item, int itemPosition) {
                HashtagSuggestion hashtagSuggestion = (HashtagSuggestion) item;

                if (hashtagSuggestion.getIsHistory()) {
                    Log.d(Util.TAG, "Setting history icon to... " + hashtagSuggestion.getHashtag());
                    leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                            R.drawable.ic_history_grey600_48dp, null));
                } else {
                    leftIcon.setAlpha(0.0f);
                    leftIcon.setImageDrawable(null);
                }

                textView.setTypeface(robotoFont);
                if(hashtagSuggestion.getBody() != null) {
                    String text = hashtagSuggestion.getBody()
                            .replaceFirst(mSearchView.getQuery(),
                                    mSearchView.getQuery());
                    textView.setText(Html.fromHtml(text));
                }
                else {
                    textView.setText(Constants.DEFAULT_HASH_TAG_VALUE);
                }
                Log.d(Util.TAG, "OnBindSuggestion()");
            }

        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(Util.TAG, "LANDSCAPE");
            mLayoutManager.setSpanCount(Constants.SPAN_COUNT_LANDSCAPE);
        } else {
            Log.d(Util.TAG, "PORTRAIT");
            mLayoutManager.setSpanCount(Constants.SPAN_COUNT_PORTRAIT);
        }
    }

    // Methods for keeping recycler view position with orientation change
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            mTwitterFeed.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    // Methods for keeping recycler view position with orientation change
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, mTwitterFeed.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (TextUtils.isEmpty(SharedPref.getAccessToken(getApplicationContext()))) {
            Log.d(Util.TAG, "Getting token");
            getToken();
            getTweets(Constants.DEFAULT_HASH_TAG_VALUE);
        } else {
            // Default search term is loaded (first time loading or no search term)
            if (!isDefaultTwitterSearch) {
                Log.d(Util.TAG, "isTwitterFeedActive: " + isDefaultTwitterSearch);
                getTweets(Constants.DEFAULT_HASH_TAG_VALUE);
                isDefaultTwitterSearch = true;
            } else {
                Log.d(Util.TAG, "Already have a search term");
                if (!mSearchView.getQuery().equals("")) {
                    getTweets(mSearchView.getQuery());
                } else {
                    getTweets(Constants.DEFAULT_HASH_TAG_VALUE);
                }
                isDefaultTwitterSearch = true;
            }
        }
    }

    @Override
    public void onBackPressed() {
        //if mSearchView.setSearchFocused(false) causes the focused search
        //to close, then we don't want to close the activity. if mSearchView.setSearchFocused(false)
        //returns false, we know that the search was already closed so the call didn't change the focus
        //state and it makes sense to call supper onBackPressed() and close the activity
        if (!mSearchView.setSearchFocused(false)) {
            super.onBackPressed();
        }
    }

    // Retrofit call to get Feed of tweets
    public void getTweets(String hashTagValue) {
        if (mTwitterFeedPresenter == null) {
            mTwitterFeedPresenter = new TwitterFeedPresenter(mTwitterFeed, mTwitterFeedAdapter, mApiService, getApplicationContext(), mSwipeRefreshLayout);
        }
        mSwipeRefreshLayout.setRefreshing(true);
        mTwitterFeedPresenter.loadPosts(hashTagValue);
    }

    // Retrofit call to get Feed of tweets
    public void getMoreTweets() {
        if (mTwitterFeedPresenter == null) {
            mTwitterFeedPresenter = new TwitterFeedPresenter(mTwitterFeed, mTwitterFeedAdapter, mApiService, getApplicationContext(), mSwipeRefreshLayout);
        }
        mTwitterFeedPresenter.loadMorePosts();
    }

    // Retrofit call to get and save access token
    public void getToken() {
        if (mTwitterFeedPresenter == null) {
            mTwitterFeedPresenter = new TwitterFeedPresenter(mTwitterFeed, mTwitterFeedAdapter, mApiService, getApplicationContext(), mSwipeRefreshLayout);
        }
        mSwipeRefreshLayout.setRefreshing(true);
        mTwitterFeedPresenter.saveToken();
    }
}
