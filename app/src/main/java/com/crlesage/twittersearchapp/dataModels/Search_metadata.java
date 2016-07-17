package com.crlesage.twittersearchapp.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chris on 7/11/2016.
 *
 */
public class Search_metadata implements Parcelable {
    private String since_id;

    private String count;

    private String max_id;

    private String refresh_url;

    private String query;

    private String max_id_str;

    private String next_results;

    private String since_id_str;

    private String completed_in;

    public String getSince_id ()
    {
        return since_id;
    }

    public void setSince_id (String since_id)
    {
        this.since_id = since_id;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getMax_id ()
    {
        return max_id;
    }

    public void setMax_id (String max_id)
    {
        this.max_id = max_id;
    }

    public String getRefresh_url ()
    {
        return refresh_url;
    }

    public void setRefresh_url (String refresh_url)
    {
        this.refresh_url = refresh_url;
    }

    public String getQuery ()
    {
        return query;
    }

    public void setQuery (String query)
    {
        this.query = query;
    }

    public String getMax_id_str ()
    {
        return max_id_str;
    }

    public void setMax_id_str (String max_id_str)
    {
        this.max_id_str = max_id_str;
    }

    public String getNext_results ()
    {
        return next_results;
    }

    public void setNext_results (String next_results)
    {
        this.next_results = next_results;
    }

    public String getSince_id_str ()
    {
        return since_id_str;
    }

    public void setSince_id_str (String since_id_str)
    {
        this.since_id_str = since_id_str;
    }

    public String getCompleted_in ()
    {
        return completed_in;
    }

    public void setCompleted_in (String completed_in)
    {
        this.completed_in = completed_in;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [since_id = "+since_id+", count = "+count+", max_id = "+max_id+", refresh_url = "+refresh_url+", query = "+query+", max_id_str = "+max_id_str+", next_results = "+next_results+", since_id_str = "+since_id_str+", completed_in = "+completed_in+"]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.since_id);
        dest.writeString(this.count);
        dest.writeString(this.max_id);
        dest.writeString(this.refresh_url);
        dest.writeString(this.query);
        dest.writeString(this.max_id_str);
        dest.writeString(this.next_results);
        dest.writeString(this.since_id_str);
        dest.writeString(this.completed_in);
    }

    public Search_metadata() {
    }

    protected Search_metadata(Parcel in) {
        this.since_id = in.readString();
        this.count = in.readString();
        this.max_id = in.readString();
        this.refresh_url = in.readString();
        this.query = in.readString();
        this.max_id_str = in.readString();
        this.next_results = in.readString();
        this.since_id_str = in.readString();
        this.completed_in = in.readString();
    }

    public static final Parcelable.Creator<Search_metadata> CREATOR = new Parcelable.Creator<Search_metadata>() {
        @Override
        public Search_metadata createFromParcel(Parcel source) {
            return new Search_metadata(source);
        }

        @Override
        public Search_metadata[] newArray(int size) {
            return new Search_metadata[size];
        }
    };
}