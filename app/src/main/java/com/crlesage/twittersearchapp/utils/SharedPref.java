package com.crlesage.twittersearchapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Chris on 7/11/2016.
 * SharedPreference Utility to store tokens/IDs
 */
public class SharedPref {

    private SharedPref() {
    }
    private static final String GENERAL_PREFS_ID = "general_prefs";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String ACCESS_TOKEN_TYPE = "token_status";
    private static final String SEARCH_HISTORY = "search_history";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(GENERAL_PREFS_ID, Context.MODE_PRIVATE);
    }

    public static void setAccessToken(Context context, String token) {
        getPrefs(context).edit().putString(ACCESS_TOKEN, token).apply();
    }

    public static String getAccessToken(Context context) {
        return getPrefs(context).getString(ACCESS_TOKEN, "");
    }

    public static void setTokenType(Context context, String tokenType) {
        getPrefs(context).edit().putString(ACCESS_TOKEN_TYPE, tokenType).apply();
    }

    public static String getTokenType(Context context) {
        return getPrefs(context).getString(ACCESS_TOKEN_TYPE, "");
    }

    public static void saveSearches(Context context, LinkedHashSet<HashtagSuggestion> searches){
        Gson gson = new Gson();
        String jsonSearches = gson.toJson(searches);
        getPrefs(context).edit().putString(SEARCH_HISTORY, jsonSearches).apply();
    }

    public static void addSearchHistory(Context context, HashtagSuggestion search){
        LinkedHashSet<HashtagSuggestion> searches = getSearchHistory(context);
        if (searches == null)
            searches = new LinkedHashSet<>();

        if(searches.size() > 0) {
            for (HashtagSuggestion hashtagSuggestion : searches) {
                if (!search.getHashtag().equals(hashtagSuggestion.getHashtag())) {
                    searches.add(hashtagSuggestion);
                }
            }
        }
        else
            searches.add(search);
        saveSearches(context, searches);
    }

    public static void removeSearch(Context context, String search) {
        LinkedHashSet<HashtagSuggestion> searches = getSearchHistory(context);
        if (searches != null) {
            searches.remove(search);
            saveSearches(context, searches);
        }
    }

    public static void clearSearch(Context context) {
        LinkedHashSet<HashtagSuggestion> searches = getSearchHistory(context);
        if (searches != null) {
            searches.clear();
            saveSearches(context, searches);
        }
    }

    public static LinkedHashSet<HashtagSuggestion> getSearchHistory(Context context) {
        List<HashtagSuggestion> searches;
        LinkedHashSet<HashtagSuggestion> linkedSearches;
        SharedPreferences sp = getPrefs(context);

        if (sp.contains(SEARCH_HISTORY)) {
            String jsonSearches = sp.getString(SEARCH_HISTORY, null);
            Gson gson = new Gson();
            HashtagSuggestion[] searchItems = gson.fromJson(jsonSearches,
                    HashtagSuggestion[].class);

            for(HashtagSuggestion search : searchItems){
                search.setIsHistory(true);
            }
            searches = Arrays.asList(searchItems);
            linkedSearches = new LinkedHashSet<>(searches);
        } else
            return null;

        for(HashtagSuggestion hashtag : linkedSearches){
            Log.d(Util.TAG, "history: " + hashtag.getBody() + " && isHistory: " + hashtag.getIsHistory());
        }

        return linkedSearches;
    }
}
