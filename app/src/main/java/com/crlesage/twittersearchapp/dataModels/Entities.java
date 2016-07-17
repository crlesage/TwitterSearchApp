package com.crlesage.twittersearchapp.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Chris on 7/11/2016.
 *
 */
public class Entities implements Parcelable {
    private Media[] media;

//    private User_mentions[] user_mentions;

    public Media[] getMedia ()
    {
        return media;
    }

    public void setMedia (Media[] media)
    {
        this.media = media;
    }

//    public User_mentions[] getUser_mentions ()
//    {
//        return user_mentions;
//    }
//
//    public void setUser_mentions (User_mentions[] user_mentions)
//    {
//        this.user_mentions = user_mentions;
//    }

//    @Override
//    public String toString()
//    {
//        return "ClassPojo [media = "+media+", user_mentions = "+user_mentions+"]";
//    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.media, flags);
//        dest.writeTypedArray(this.user_mentions, flags);
    }

    public Entities() {
    }

    protected Entities(Parcel in) {
        this.media = in.createTypedArray(Media.CREATOR);
//        this.user_mentions = in.createTypedArray(User_mentions.CREATOR);
    }

    public static final Parcelable.Creator<Entities> CREATOR = new Parcelable.Creator<Entities>() {
        @Override
        public Entities createFromParcel(Parcel source) {
            return new Entities(source);
        }

        @Override
        public Entities[] newArray(int size) {
            return new Entities[size];
        }
    };
}
