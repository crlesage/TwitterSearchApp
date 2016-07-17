package com.crlesage.twittersearchapp.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chris on 7/11/2016.
 *
 */
public class Medium implements Parcelable {
    private String w;

    private String resize;

    private String h;

    public String getW ()
    {
        return w;
    }

    public void setW (String w)
    {
        this.w = w;
    }

    public String getResize ()
    {
        return resize;
    }

    public void setResize (String resize)
    {
        this.resize = resize;
    }

    public String getH ()
    {
        return h;
    }

    public void setH (String h)
    {
        this.h = h;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [w = "+w+", resize = "+resize+", h = "+h+"]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.w);
        dest.writeString(this.resize);
        dest.writeString(this.h);
    }

    public Medium() {
    }

    protected Medium(Parcel in) {
        this.w = in.readString();
        this.resize = in.readString();
        this.h = in.readString();
    }

    public static final Parcelable.Creator<Medium> CREATOR = new Parcelable.Creator<Medium>() {
        @Override
        public Medium createFromParcel(Parcel source) {
            return new Medium(source);
        }

        @Override
        public Medium[] newArray(int size) {
            return new Medium[size];
        }
    };
}
