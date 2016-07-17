package com.crlesage.twittersearchapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.crlesage.twittersearchapp.R;
import com.crlesage.twittersearchapp.activities.DetailedTweetView;
import com.crlesage.twittersearchapp.customUI.DynamicHeightImageView;
import com.crlesage.twittersearchapp.dataModels.Tweet;
import com.crlesage.twittersearchapp.interfaces.Constants;
import com.crlesage.twittersearchapp.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Chris on 7/11/2016.
 * An adapter for the RecyclerView holding the Twitter Feed.
 */
public class TwitterFeedAdapter extends RecyclerView.Adapter<TwitterFeedAdapter.PostViewHolder> {
    private ArrayList<Tweet> mTweetData = new ArrayList<Tweet>();
    private Context mContext;

    public TwitterFeedAdapter(Context context) {
        this.mContext = context;
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        DynamicHeightImageView tweetImage;

        PostViewHolder(View itemView) {
            super(itemView);
            tweetImage = (DynamicHeightImageView) itemView.findViewById(R.id.tweet_image);
        }
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.entry_tweet_view, viewGroup, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostViewHolder postViewHolder, final int position) {
        // Get reference to the post's data
        final Tweet tweet = mTweetData.get(position);
        String tweetImage;

        // Get reference to the view holder's views
        DynamicHeightImageView tweetImageView = postViewHolder.tweetImage;



        // Set the post's data
        if (tweet.getEntities().getMedia() != null) {
            // Size the imageview to be the ratio of the "medium" image
            FrameLayout.LayoutParams rlp = (FrameLayout.LayoutParams) tweetImageView.getLayoutParams();
            float ratio = Float.parseFloat(tweet.getEntities().getMedia()[0].getSizes().getMedium().getH())
                    / Float.parseFloat(tweet.getEntities().getMedia()[0].getSizes().getMedium().getW());
            rlp.height = (int) (rlp.width * ratio);
            tweetImageView.setLayoutParams(rlp);
            tweetImageView.setRatio(ratio);

            tweetImage = tweet.getEntities().getMedia()[0].getMedia_url_https();
            Picasso.with(mContext)
                    .load(tweetImage)
                    .config(Bitmap.Config.ARGB_4444)
                    .into(tweetImageView);

            // Set onClickListener for clicking a particular post
            postViewHolder.tweetImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tweet tweet = mTweetData.get(position);
                    Intent intent = new Intent(mContext, DetailedTweetView.class);
                    intent.putExtra("tweet", tweet);
                    mContext.startActivity(intent);
                }
            });
        } else {
            Log.d(Util.TAG, "Tweet without media got through");
            removeItemAt(position);
            tweetImage = Constants.DEFAULT_IMAGE;
        }
    }

    @Override
    public int getItemCount() {
        return mTweetData.size();
    }

    // Remove all posts from feed
    public void removeAllItems() {
        mTweetData.clear();
        notifyDataSetChanged();
    }

    // Remove particular item (if no image part of the "tweet")
    public void removeItemAt(final int position) {

        // Got from to help with removing an item (item without an image)
        //http://stackoverflow.com/questions/26555428/recyclerview-notifyiteminserted-illegalstateexception
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                mTweetData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mTweetData.size());
            }
        };
        handler.post(r);

    }

    // Add single tweet to the feed
    public void addTweet(Tweet tweet) {
        tweet.position = mTweetData.size();
        mTweetData.add(tweet);
        notifyDataSetChanged();
    }

    // Add the feed to the adapter
    public void addFeed(Tweet[] tweets) {
        mTweetData = new ArrayList<Tweet>(Arrays.asList(tweets));
        notifyDataSetChanged();
    }

    // Add more posts to the feed (Paging)
    public void addMoreFeed(Tweet[] tweets) {
        Collections.addAll(mTweetData, tweets);
        notifyDataSetChanged();
    }
}
