package com.crlesage.twittersearchapp.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chris on 7/11/2016.
 *
 */
//public class User_mentions implements Parcelable {
//    private String id;
//
//    private String name;
//
//    private String[] indices;
//
//    private String screen_name;
//
//    private String id_str;
//
//    public String getId ()
//    {
//        return id;
//    }
//
//    public void setId (String id)
//    {
//        this.id = id;
//    }
//
//    public String getName ()
//    {
//        return name;
//    }
//
//    public void setName (String name)
//    {
//        this.name = name;
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
//    public String getScreen_name ()
//    {
//        return screen_name;
//    }
//
//    public void setScreen_name (String screen_name)
//    {
//        this.screen_name = screen_name;
//    }
//
//    public String getId_str ()
//    {
//        return id_str;
//    }
//
//    public void setId_str (String id_str)
//    {
//        this.id_str = id_str;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "ClassPojo [id = "+id+", name = "+name+", indices = "+indices+", screen_name = "+screen_name+", id_str = "+id_str+"]";
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
//        dest.writeString(this.id);
//        dest.writeString(this.name);
//        dest.writeStringArray(this.indices);
//        dest.writeString(this.screen_name);
//        dest.writeString(this.id_str);
//    }
//
//    public User_mentions() {
//    }
//
//    protected User_mentions(Parcel in) {
//        this.id = in.readString();
//        this.name = in.readString();
//        this.indices = in.createStringArray();
//        this.screen_name = in.readString();
//        this.id_str = in.readString();
//    }
//
//    public static final Parcelable.Creator<User_mentions> CREATOR = new Parcelable.Creator<User_mentions>() {
//        @Override
//        public User_mentions createFromParcel(Parcel source) {
//            return new User_mentions(source);
//        }
//
//        @Override
//        public User_mentions[] newArray(int size) {
//            return new User_mentions[size];
//        }
//    };
//}
