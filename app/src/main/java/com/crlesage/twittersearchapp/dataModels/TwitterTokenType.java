package com.crlesage.twittersearchapp.dataModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chris on 7/11/2016.
 * TokenType POJO
 */
public class TwitterTokenType {

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("access_token")
    public String accessToken;
}
