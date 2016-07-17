package com.crlesage.twittersearchapp.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chris on 7/12/2016.
 *
 */
//public class Retweeted_status implements Parcelable {
//    private String retweeted;
//
//    private String in_reply_to_screen_name;
//
//    private String possibly_sensitive;
//
//    private String truncated;
//
//    private String lang;
//
//    private String in_reply_to_status_id_str;
//
//    private String id;
//
//    private String in_reply_to_user_id_str;
//
//    private String in_reply_to_status_id;
//
//    private String created_at;
//
//    private String favorite_count;
//
//    private String place;
//
//    private String coordinates;
//
//    private Metadata metadata;
//
//    private String text;
//
//    private String contributors;
//
//    private String geo;
//
//    private Entities entities;
//
//    private String is_quote_status;
//
//    private String source;
//
//    private String favorited;
//
//    private String in_reply_to_user_id;
//
//    private String retweet_count;
//
//    private String id_str;
//
//    private User user;
//
//    public String getRetweeted ()
//    {
//        return retweeted;
//    }
//
//    public void setRetweeted (String retweeted)
//    {
//        this.retweeted = retweeted;
//    }
//
//    public String getIn_reply_to_screen_name ()
//    {
//        return in_reply_to_screen_name;
//    }
//
//    public void setIn_reply_to_screen_name (String in_reply_to_screen_name)
//    {
//        this.in_reply_to_screen_name = in_reply_to_screen_name;
//    }
//
//    public String getPossibly_sensitive ()
//    {
//        return possibly_sensitive;
//    }
//
//    public void setPossibly_sensitive (String possibly_sensitive)
//    {
//        this.possibly_sensitive = possibly_sensitive;
//    }
//
//    public String getTruncated ()
//    {
//        return truncated;
//    }
//
//    public void setTruncated (String truncated)
//    {
//        this.truncated = truncated;
//    }
//
//    public String getLang ()
//    {
//        return lang;
//    }
//
//    public void setLang (String lang)
//    {
//        this.lang = lang;
//    }
//
//    public String getIn_reply_to_status_id_str ()
//    {
//        return in_reply_to_status_id_str;
//    }
//
//    public void setIn_reply_to_status_id_str (String in_reply_to_status_id_str)
//    {
//        this.in_reply_to_status_id_str = in_reply_to_status_id_str;
//    }
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
//    public String getIn_reply_to_user_id_str ()
//    {
//        return in_reply_to_user_id_str;
//    }
//
//    public void setIn_reply_to_user_id_str (String in_reply_to_user_id_str)
//    {
//        this.in_reply_to_user_id_str = in_reply_to_user_id_str;
//    }
//
//    public String getIn_reply_to_status_id ()
//    {
//        return in_reply_to_status_id;
//    }
//
//    public void setIn_reply_to_status_id (String in_reply_to_status_id)
//    {
//        this.in_reply_to_status_id = in_reply_to_status_id;
//    }
//
//    public String getCreated_at ()
//    {
//        return created_at;
//    }
//
//    public void setCreated_at (String created_at)
//    {
//        this.created_at = created_at;
//    }
//
//    public String getFavorite_count ()
//    {
//        return favorite_count;
//    }
//
//    public void setFavorite_count (String favorite_count)
//    {
//        this.favorite_count = favorite_count;
//    }
//
//    public String getPlace ()
//{
//    return place;
//}
//
//    public void setPlace (String place)
//    {
//        this.place = place;
//    }
//
//    public String getCoordinates ()
//{
//    return coordinates;
//}
//
//    public void setCoordinates (String coordinates)
//    {
//        this.coordinates = coordinates;
//    }
//
//    public Metadata getMetadata ()
//    {
//        return metadata;
//    }
//
//    public void setMetadata (Metadata metadata)
//    {
//        this.metadata = metadata;
//    }
//
//    public String getText ()
//    {
//        return text;
//    }
//
//    public void setText (String text)
//    {
//        this.text = text;
//    }
//
//    public String getContributors ()
//{
//    return contributors;
//}
//
//    public void setContributors (String contributors)
//    {
//        this.contributors = contributors;
//    }
//
//    public String getGeo ()
//{
//    return geo;
//}
//
//    public void setGeo (String geo)
//    {
//        this.geo = geo;
//    }
//
//    public Entities getEntities ()
//    {
//        return entities;
//    }
//
//    public void setEntities (Entities entities)
//    {
//        this.entities = entities;
//    }
//
//    public String getIs_quote_status ()
//    {
//        return is_quote_status;
//    }
//
//    public void setIs_quote_status (String is_quote_status)
//    {
//        this.is_quote_status = is_quote_status;
//    }
//
//    public String getSource ()
//    {
//        return source;
//    }
//
//    public void setSource (String source)
//    {
//        this.source = source;
//    }
//
//    public String getFavorited ()
//    {
//        return favorited;
//    }
//
//    public void setFavorited (String favorited)
//    {
//        this.favorited = favorited;
//    }
//
//    public String getIn_reply_to_user_id ()
//    {
//        return in_reply_to_user_id;
//    }
//
//    public void setIn_reply_to_user_id (String in_reply_to_user_id)
//    {
//        this.in_reply_to_user_id = in_reply_to_user_id;
//    }
//
//    public String getRetweet_count ()
//    {
//        return retweet_count;
//    }
//
//    public void setRetweet_count (String retweet_count)
//    {
//        this.retweet_count = retweet_count;
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
//    public User getUser ()
//    {
//        return user;
//    }
//
//    public void setUser (User user)
//    {
//        this.user = user;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "ClassPojo [retweeted = "+retweeted+", in_reply_to_screen_name = "+in_reply_to_screen_name+", possibly_sensitive = "+possibly_sensitive+", truncated = "+truncated+", lang = "+lang+", in_reply_to_status_id_str = "+in_reply_to_status_id_str+", id = "+id+", in_reply_to_user_id_str = "+in_reply_to_user_id_str+", in_reply_to_status_id = "+in_reply_to_status_id+", created_at = "+created_at+", favorite_count = "+favorite_count+", place = "+place+", coordinates = "+coordinates+", metadata = "+metadata+", text = "+text+", contributors = "+contributors+", geo = "+geo+", entities = "+entities+", is_quote_status = "+is_quote_status+", source = "+source+", favorited = "+favorited+", in_reply_to_user_id = "+in_reply_to_user_id+", retweet_count = "+retweet_count+", id_str = "+id_str+", user = "+user+"]";
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
//        dest.writeString(this.retweeted);
//        dest.writeString(this.in_reply_to_screen_name);
//        dest.writeString(this.possibly_sensitive);
//        dest.writeString(this.truncated);
//        dest.writeString(this.lang);
//        dest.writeString(this.in_reply_to_status_id_str);
//        dest.writeString(this.id);
//        dest.writeString(this.in_reply_to_user_id_str);
//        dest.writeString(this.in_reply_to_status_id);
//        dest.writeString(this.created_at);
//        dest.writeString(this.favorite_count);
//        dest.writeString(this.place);
//        dest.writeString(this.coordinates);
//        dest.writeParcelable(this.metadata, flags);
//        dest.writeString(this.text);
//        dest.writeString(this.contributors);
//        dest.writeString(this.geo);
//        dest.writeParcelable(this.entities, flags);
//        dest.writeString(this.is_quote_status);
//        dest.writeString(this.source);
//        dest.writeString(this.favorited);
//        dest.writeString(this.in_reply_to_user_id);
//        dest.writeString(this.retweet_count);
//        dest.writeString(this.id_str);
//        dest.writeParcelable(this.user, flags);
//    }
//
//    public Retweeted_status() {
//    }
//
//    protected Retweeted_status(Parcel in) {
//        this.retweeted = in.readString();
//        this.in_reply_to_screen_name = in.readString();
//        this.possibly_sensitive = in.readString();
//        this.truncated = in.readString();
//        this.lang = in.readString();
//        this.in_reply_to_status_id_str = in.readString();
//        this.id = in.readString();
//        this.in_reply_to_user_id_str = in.readString();
//        this.in_reply_to_status_id = in.readString();
//        this.created_at = in.readString();
//        this.favorite_count = in.readString();
//        this.place = in.readString();
//        this.coordinates = in.readString();
//        this.metadata = in.readParcelable(Metadata.class.getClassLoader());
//        this.text = in.readString();
//        this.contributors = in.readString();
//        this.geo = in.readString();
//        this.entities = in.readParcelable(Entities.class.getClassLoader());
//        this.is_quote_status = in.readString();
//        this.source = in.readString();
//        this.favorited = in.readString();
//        this.in_reply_to_user_id = in.readString();
//        this.retweet_count = in.readString();
//        this.id_str = in.readString();
//        this.user = in.readParcelable(User.class.getClassLoader());
//    }
//
//    public static final Parcelable.Creator<Retweeted_status> CREATOR = new Parcelable.Creator<Retweeted_status>() {
//        @Override
//        public Retweeted_status createFromParcel(Parcel source) {
//            return new Retweeted_status(source);
//        }
//
//        @Override
//        public Retweeted_status[] newArray(int size) {
//            return new Retweeted_status[size];
//        }
//    };
//}
