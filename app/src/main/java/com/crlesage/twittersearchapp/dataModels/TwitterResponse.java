package com.crlesage.twittersearchapp.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 7/11/2016.
 *
 */
public class TwitterResponse implements Parcelable {
    @SerializedName("statuses")
    private Tweet[] tweets;

    private Search_metadata search_metadata;

    public Tweet[] getTweets() {
        return tweets;
    }

    public void setTweets(Tweet[] tweets) {
        this.tweets = tweets;
    }

    public Search_metadata getSearch_metadata() {
        return search_metadata;
    }

    public void setSearch_metadata(Search_metadata search_metadata) {
        this.search_metadata = search_metadata;
    }

    @Override
    public String toString() {
        return "ClassPojo [tweets = " + tweets + ", search_metadata = " + search_metadata + "]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.tweets, flags);
        dest.writeParcelable(this.search_metadata, flags);
    }

    public TwitterResponse() {
    }

    protected TwitterResponse(Parcel in) {
        this.tweets = in.createTypedArray(Tweet.CREATOR);
        //this.tweets = in.readParcelable(Tweet[].class.getClassLoader());
        this.search_metadata = in.readParcelable(Search_metadata.class.getClassLoader());
    }

    public static final Parcelable.Creator<TwitterResponse> CREATOR = new Parcelable.Creator<TwitterResponse>() {
        @Override
        public TwitterResponse createFromParcel(Parcel source) {
            return new TwitterResponse(source);
        }

        @Override
        public TwitterResponse[] newArray(int size) {
            return new TwitterResponse[size];
        }
    };
}
