package com.crlesage.twittersearchapp.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chris on 7/11/2016.
 *
 */
public class Sizes implements Parcelable {
    private Thumb thumb;

    private Small small;

    private Medium medium;

    private Large large;

    public Thumb getThumb ()
    {
        return thumb;
    }

    public void setThumb (Thumb thumb)
    {
        this.thumb = thumb;
    }

    public Small getSmall ()
    {
        return small;
    }

    public void setSmall (Small small)
    {
        this.small = small;
    }

    public Medium getMedium ()
    {
        return medium;
    }

    public void setMedium (Medium medium)
    {
        this.medium = medium;
    }

    public Large getLarge ()
    {
        return large;
    }

    public void setLarge (Large large)
    {
        this.large = large;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [thumb = "+thumb+", small = "+small+", medium = "+medium+", large = "+large+"]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.thumb, flags);
        dest.writeParcelable(this.small, flags);
        dest.writeParcelable(this.medium, flags);
        dest.writeParcelable(this.large, flags);
    }

    public Sizes() {
    }

    protected Sizes(Parcel in) {
        this.thumb = in.readParcelable(Thumb.class.getClassLoader());
        this.small = in.readParcelable(Small.class.getClassLoader());
        this.medium = in.readParcelable(Medium.class.getClassLoader());
        this.large = in.readParcelable(Large.class.getClassLoader());
    }

    public static final Parcelable.Creator<Sizes> CREATOR = new Parcelable.Creator<Sizes>() {
        @Override
        public Sizes createFromParcel(Parcel source) {
            return new Sizes(source);
        }

        @Override
        public Sizes[] newArray(int size) {
            return new Sizes[size];
        }
    };
}

