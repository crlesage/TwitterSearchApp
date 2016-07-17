package com.crlesage.twittersearchapp.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chris on 7/11/2016.
 *
 */
//public class Metadata implements Parcelable {
//    private String result_type;
//
//    private String iso_language_code;
//
//    public String getResult_type ()
//    {
//        return result_type;
//    }
//
//    public void setResult_type (String result_type)
//    {
//        this.result_type = result_type;
//    }
//
//    public String getIso_language_code ()
//    {
//        return iso_language_code;
//    }
//
//    public void setIso_language_code (String iso_language_code)
//    {
//        this.iso_language_code = iso_language_code;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "ClassPojo [result_type = "+result_type+", iso_language_code = "+iso_language_code+"]";
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.result_type);
//        dest.writeString(this.iso_language_code);
//    }
//
//    public Metadata() {
//    }
//
//    protected Metadata(Parcel in) {
//        this.result_type = in.readString();
//        this.iso_language_code = in.readString();
//    }
//
//    public static final Parcelable.Creator<Metadata> CREATOR = new Parcelable.Creator<Metadata>() {
//        @Override
//        public Metadata createFromParcel(Parcel source) {
//            return new Metadata(source);
//        }
//
//        @Override
//        public Metadata[] newArray(int size) {
//            return new Metadata[size];
//        }
//    };
//}
