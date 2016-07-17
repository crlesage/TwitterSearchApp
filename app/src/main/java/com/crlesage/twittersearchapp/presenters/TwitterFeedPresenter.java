package com.crlesage.twittersearchapp.presenters;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.crlesage.twittersearchapp.activities.TweetView;
import com.crlesage.twittersearchapp.adapters.TwitterFeedAdapter;
import com.crlesage.twittersearchapp.dataModels.TwitterResponse;
import com.crlesage.twittersearchapp.dataModels.TwitterTokenType;
import com.crlesage.twittersearchapp.interfaces.Constants;
import com.crlesage.twittersearchapp.utils.ApiService;
import com.crlesage.twittersearchapp.utils.SharedPref;
import com.crlesage.twittersearchapp.utils.Util;

import java.io.UnsupportedEncodingException;

import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Chris on 7/11/2016.
 * Connecting the API call's data to the UI
 */
public class TwitterFeedPresenter {

    RecyclerView mRecyclerView;
    TwitterFeedAdapter mAdapter;
    ApiService mApiService;
    Context mContext;
    TwitterResponse mCurrentTwitterResponse;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public TwitterFeedPresenter(RecyclerView view, TwitterFeedAdapter adapter, ApiService apiService, Context context, SwipeRefreshLayout swipeRefreshLayout) {
        mRecyclerView = view;
        mAdapter = adapter;
        mApiService = apiService;
        mContext = context;
        mSwipeRefreshLayout = swipeRefreshLayout;
    }

    public TwitterResponse getCurrentTwitterResponse() {
        return mCurrentTwitterResponse;
    }

    public void loadPosts(final String hashTagValue) {
        Log.d(Util.TAG, "Loading posts...");
        final Observable<Response<TwitterResponse>> call = mApiService.getApi().getTweetList(
                "Bearer " + SharedPref.getAccessToken(mContext),
                Constants.TWITTER_FILTER,
                Constants.TWITTER_INCLUDE_ENTITIES,
                hashTagValue,
                Constants.RETURN_COUNT);

        final Subscription subscription = call
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<TwitterResponse>>() {
                    @Override
                    public void onCompleted() {
//                        Log.d(Util.TAG, "Complete loading posts!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Util.TAG, "Failed to get twitter feed from : " + hashTagValue + ".");
                        Log.e(Util.TAG, e.toString());
                    }

                    @Override
                    public void onNext(Response<TwitterResponse> response) {
                        TwitterResponse tweets = response.body();
                        Log.d(Util.TAG, "onNext loading posts!");

                        // Set as current Twitter response (used for pagination for accessing
                        // Extension for additional tweets.
                        mCurrentTwitterResponse = tweets;

                        // Add more tweets to adapter
                        mAdapter.addFeed(tweets.getTweets());
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    public void loadMorePosts() {
        Log.d(Util.TAG, "Loading more posts...");

        // Error checking for if there is a "next" pagination of Twitter posts
        if (mCurrentTwitterResponse == null
                && mCurrentTwitterResponse.getSearch_metadata() == null) {
            Log.d(Util.TAG, "Error in loading more tweets, no reference to previous payload.");

            return;
        }

        if (mCurrentTwitterResponse.getSearch_metadata().getNext_results() != null &&
                !mCurrentTwitterResponse.getSearch_metadata().getNext_results().equals("")) {
            String extension = mCurrentTwitterResponse.getSearch_metadata().getNext_results();
            String url = Constants.TWITTER_SEARCH_URL
                    + Constants.TWITTER_HASHTAG_SEARCH_CODE
                    + extension
                    + "&" + Constants.RETURN_COUNT;
            final Observable<Response<TwitterResponse>> call = mApiService.getApi().getNextTweetList(
                    "Bearer " + SharedPref.getAccessToken(mContext),
                    url);

            final Subscription subscription = call
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<TwitterResponse>>() {
                        @Override
                        public void onCompleted() {
//                            Log.d(Util.TAG, "Complete loading posts!");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(Util.TAG, "Failed to get more twitter feed.");
                            Log.e(Util.TAG, e.toString());
                        }

                        @Override
                        public void onNext(Response<TwitterResponse> response) {
                            TwitterResponse tweets = response.body();
                            Log.d(Util.TAG, "onNext loading more posts!");

                            // Add "new"/more tweets to the adapter to update recyclerview
                            mCurrentTwitterResponse = tweets;
                            mAdapter.addMoreFeed(tweets.getTweets());

                            // Set loading to true for next pagination
                            TweetView.loading = true;
                        }
                    });
        }
        else {
            Util.makeToast(mContext, "No more Tweets!");
        }
    }

    public void saveToken() {
        try {
            mApiService.getApi()
                    .getToken("Basic " + Util.getBase64String(Constants.BEARER_TOKEN_CREDENTIALS), "client_credentials")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TwitterTokenType>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(Util.TAG, "Failed to get token.");
                        }

                        @Override
                        public void onNext(TwitterTokenType token) {
                            SharedPref.setAccessToken(mContext, token.accessToken);
                            SharedPref.setTokenType(mContext, token.tokenType);
                            Log.d(Util.TAG, "Token Saved, Next");

                            // After saving token, initialize default search
                            loadPosts(Constants.DEFAULT_HASH_TAG_VALUE);
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            Log.e(Util.TAG, e.toString(), e);
            e.printStackTrace();
        }
    }
}
