package com.crlesage.twittersearchapp.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chris on 7/11/2016.
 *
 */
public class Media implements Parcelable {
    private Sizes sizes;

    private String id;

    private String media_url_https;

    private String media_url;

    private String expanded_url;

    private String[] indices;

    private String id_str;

    private String type;

    private String display_url;

    private String url;

    public Sizes getSizes ()
    {
        return sizes;
    }

    public void setSizes (Sizes sizes)
    {
        this.sizes = sizes;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getMedia_url_https ()
    {
        return media_url_https;
    }

    public void setMedia_url_https (String media_url_https)
    {
        this.media_url_https = media_url_https;
    }

    public String getMedia_url ()
    {
        return media_url;
    }

    public void setMedia_url (String media_url)
    {
        this.media_url = media_url;
    }

    public String getExpanded_url ()
    {
        return expanded_url;
    }

    public void setExpanded_url (String expanded_url)
    {
        this.expanded_url = expanded_url;
    }

    public String[] getIndices ()
    {
        return indices;
    }

    public void setIndices (String[] indices)
    {
        this.indices = indices;
    }

    public String getId_str ()
    {
        return id_str;
    }

    public void setId_str (String id_str)
    {
        this.id_str = id_str;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getDisplay_url ()
    {
        return display_url;
    }

    public void setDisplay_url (String display_url)
    {
        this.display_url = display_url;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sizes = "+sizes+", id = "+id+", media_url_https = "+media_url_https+", media_url = "+media_url+", expanded_url = "+expanded_url+", indices = "+indices+", id_str = "+id_str+", type = "+type+", display_url = "+display_url+", url = "+url+"]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.sizes, flags);
        dest.writeString(this.id);
        dest.writeString(this.media_url_https);
        dest.writeString(this.media_url);
        dest.writeString(this.expanded_url);
        dest.writeStringArray(this.indices);
        dest.writeString(this.id_str);
        dest.writeString(this.type);
        dest.writeString(this.display_url);
        dest.writeString(this.url);
    }

    public Media() {
    }

    protected Media(Parcel in) {
        this.sizes = in.readParcelable(Sizes.class.getClassLoader());
        this.id = in.readString();
        this.media_url_https = in.readString();
        this.media_url = in.readString();
        this.expanded_url = in.readString();
        this.indices = in.createStringArray();
        this.id_str = in.readString();
        this.type = in.readString();
        this.display_url = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Media> CREATOR = new Parcelable.Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel source) {
            return new Media(source);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };
}
