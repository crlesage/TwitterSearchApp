package com.crlesage.twittersearchapp.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chris on 7/13/2016.
 * A library part of the FloatingSearchView to help with suggesting hashtags for the user!
 * Libray can be found at:
 * https://github.com/arimorty/floatingsearchview
 */
public class HashtagSuggestion implements SearchSuggestion, Parcelable {

    @SerializedName("hashtag")
    @Expose
    private String mHashtagName;

    private transient boolean mIsHistory = false;

    public HashtagSuggestion(String suggestion) {
        this.mHashtagName = suggestion.toLowerCase();
    }

    public HashtagSuggestion(Parcel source) {
        this.mHashtagName = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    public String getHashtag() {
        return mHashtagName;
    }

    public void setHashtag(String hashtag) {
        this.mHashtagName = hashtag;
    }

    @Override
    public String getBody() {
        return mHashtagName;
    }

    public static final Creator<HashtagSuggestion> CREATOR = new Creator<HashtagSuggestion>() {
        @Override
        public HashtagSuggestion createFromParcel(Parcel in) {
            return new HashtagSuggestion(in);
        }

        @Override
        public HashtagSuggestion[] newArray(int size) {
            return new HashtagSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mHashtagName);
        dest.writeInt(mIsHistory ? 1 : 0);
    }

    public boolean isEqualHashtag (HashtagSuggestion hashtagSuggestion){
        return this.getHashtag().equals(hashtagSuggestion.getHashtag())
                && this.getIsHistory() == hashtagSuggestion.getIsHistory();
    }
}
