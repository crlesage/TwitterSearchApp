package com.crlesage.twittersearchapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crlesage.twittersearchapp.R;
import com.crlesage.twittersearchapp.customUI.DynamicHeightImageView;
import com.crlesage.twittersearchapp.dataModels.Tweet;
import com.crlesage.twittersearchapp.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chris on 7/11/2016.
 * A more descriptive and larger view of an individual tweet.
 */
public class DetailedTweetView extends AppCompatActivity {


    @BindView(R.id.detailed_tweet_image)
    protected DynamicHeightImageView mTweetImage;
    @BindView(R.id.detailed_tweet_profile_image)
    protected ImageView mProfileImage;
    @BindView(R.id.detailed_tweet_profile_at_name)
    protected TextView mProfileAtName;
    @BindView(R.id.detailed_tweet_profile_name)
    protected TextView mProfileName;
    @BindView(R.id.detailed_tweet_text)
    protected TextView mTweetText;
    @BindView(R.id.detailed_tweet_back)
    protected ImageView mBackButton;
    @BindView(R.id.detailed_tweet_view_tweet)
    protected TextView mViewTweet;
    Tweet mTweet;
    Activity mActivity = this;
    public Typeface robotoFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_tweet_view);
        ButterKnife.bind(this);

        // Initialize and set fonts to text
        initializeTypeFace();

        // Set up Back button
        setUpBack();

        // Grab photo data that was clicked on
        getIntentData();

        // Set up the post's images and texts
        setUpPostUI();
    }

    // Override the "home" back button to go back to Feed
    private void setUpBack() {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    // Get the data from the intent from the certain post clicked
    private void getIntentData() {
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        mTweet = (Tweet) data.getParcelable("tweet");

        Log.d(Util.TAG, "Data:  " + mTweet.toString());
    }

    // Initialize Typeface
    public void initializeTypeFace() {
        AssetManager am = getApplicationContext().getApplicationContext().getAssets();
        robotoFont = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Roboto-Regular.ttf"));

        mProfileName.setTypeface(robotoFont);
        mTweetText.setTypeface(robotoFont);
        mProfileAtName.setTypeface(robotoFont);
        mViewTweet.setTypeface(robotoFont);
    }

    // Set up the post detail UI from the intent's data
    private void setUpPostUI() {
        // Set text
        String tweetText = mTweet.getText();
        mTweetText.setText(tweetText);

        // Set user name
        String tweetProfileName = mTweet.getUser().getName();
        mProfileName.setText(tweetProfileName);

        // Set @ user name
        String tweetAtProfileName = mTweet.getUser().getScreen_name();
        mProfileAtName.setText("@" + tweetAtProfileName);

        // Set user profile image
        String tweetProfileImage = mTweet.getUser().getProfile_background_image_url_https();
        Picasso.with(mActivity.getApplicationContext())
                .load(tweetProfileImage)
                .fit().centerCrop()
                .into(mProfileImage);

        // Set tweet background image
        // Size the imageview to be the ratio of the "large" image
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mTweetImage.getLayoutParams();
        float ratio = Float.parseFloat(mTweet.getEntities().getMedia()[0].getSizes().getLarge().getH())
                / Float.parseFloat(mTweet.getEntities().getMedia()[0].getSizes().getLarge().getW());
        rlp.height = (int) (rlp.width * ratio);
        mTweetImage.setLayoutParams(rlp);
        mTweetImage.setRatio(ratio);

        String tweetImage = mTweet.getEntities().getMedia()[0].getMedia_url_https();
        Picasso.with(mActivity.getApplicationContext())
                .load(tweetImage)
                .fit().centerCrop()
                .into(mTweetImage);

        // Onclicklistener for "deeplink" to view the tweet either in the twitter app or browser
        // depending on if the user has the app installed or not
        mViewTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tweet_id = mTweet.getId_str();
                String tweet_user_screen_name = mTweet.getUser().getScreen_name();

                Intent intent = null;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://twitter.com/"
                                + tweet_user_screen_name
                                + "/status/"
                                + tweet_id));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });

        // Onclicklistener for "deeplink" to view the tweet's user either in the twitter app or browser
        // depending on if the user has the app installed or not
        mProfileAtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String screen_name = mTweet.getUser().getScreen_name();

                Intent intent = null;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://twitter.com/"
                                + screen_name));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });
    }
}
