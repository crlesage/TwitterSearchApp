package com.crlesage.twittersearchapp.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chris on 7/11/2016.
 *
 */
//public class Urls implements Parcelable {
//    private String expanded_url;
//
//    private String[] indices;
//
//    private String display_url;
//
//    private String url;
//
//    public String getExpanded_url ()
//    {
//        return expanded_url;
//    }
//
//    public void setExpanded_url (String expanded_url)
//    {
//        this.expanded_url = expanded_url;
//    }
//
//    public String[] getIndices ()
//    {
//        return indices;
//    }
//
//    public void setIndices (String[] indices)
//    {
//        this.indices = indices;
//    }
//
//    public String getDisplay_url ()
//    {
//        return display_url;
//    }
//
//    public void setDisplay_url (String display_url)
//    {
//        this.display_url = display_url;
//    }
//
//    public String getUrl ()
//    {
//        return url;
//    }
//
//    public void setUrl (String url)
//    {
//        this.url = url;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "ClassPojo [expanded_url = "+expanded_url+", indices = "+indices+", display_url = "+display_url+", url = "+url+"]";
//    }
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.expanded_url);
//        dest.writeStringArray(this.indices);
//        dest.writeString(this.display_url);
//        dest.writeString(this.url);
//    }
//
//    public Urls() {
//    }
//
//    protected Urls(Parcel in) {
//        this.expanded_url = in.readString();
//        this.indices = in.createStringArray();
//        this.display_url = in.readString();
//        this.url = in.readString();
//    }
//
//    public static final Parcelable.Creator<Urls> CREATOR = new Parcelable.Creator<Urls>() {
//        @Override
//        public Urls createFromParcel(Parcel source) {
//            return new Urls(source);
//        }
//
//        @Override
//        public Urls[] newArray(int size) {
//            return new Urls[size];
//        }
//    };
//}
