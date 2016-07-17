package com.crlesage.twittersearchapp.interfaces;

/**
 * Created by Chris on 7/11/2016.
 * Constants found throughout the application
 */
public interface Constants {
    //String URL = "http://twitter.com";

    String CONSUMER_KEY = "fFexrye5XVDsvWRRitABiLxXb";

    String CONSUMER_SECRET = "Ge6Su6tslxm0RJ40AiHfYmuQfCY48ktdlPqz1YWFjsSBrDOV8x";

    String TWITTER_SEARCH_URL = "https://api.twitter.com";

    String BEARER_TOKEN_CREDENTIALS = CONSUMER_KEY + ":" + CONSUMER_SECRET;

    String TWITTER_HASHTAG_SEARCH_CODE = "/1.1/search/tweets.json";

    String DEFAULT_HASH_TAG_VALUE = "puffins";

    int SPAN_COUNT_PORTRAIT = 2;

    int SPAN_COUNT_LANDSCAPE = 3;

    int ORIENTATION_VERTICAL = 1;

    int ORIENTATION_HORIZONTAL = 0;

    String TWITTER_FILTER = "images";

    String TWITTER_INCLUDE_ENTITIES = "true";

    String DEFAULT_IMAGE = "https://blog.twitter.com/sites/all/themes/gazebo/img/twitter-bird-white-on-blue.png";

    int RETURN_COUNT = 50;

    double FLING_SCALE_DOWN = 0.5;
}
