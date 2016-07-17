package com.crlesage.twittersearchapp.interfaces;

import com.crlesage.twittersearchapp.dataModels.TwitterResponse;
import com.crlesage.twittersearchapp.dataModels.TwitterTokenType;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Chris on 7/11/2016.
 * API interface for Twitter services.
 */
public interface ApiInterface {

    @GET(Constants.TWITTER_HASHTAG_SEARCH_CODE)
    Observable<Response<TwitterResponse>> getTweetList(
            @Header("Authorization") String authorization,
            @Query("filter") String filter,
            @Query("include_entities") String include_entities,
            @Query("q") String hashTagValue,
            @Query("count") int count
    );

    @GET()
    Observable<Response<TwitterResponse>> getNextTweetList(
            @Header("Authorization") String authorization,
            @Url String url
    );

    @FormUrlEncoded
    @POST("/oauth2/token")
    Observable<TwitterTokenType> getToken(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType
    );
}