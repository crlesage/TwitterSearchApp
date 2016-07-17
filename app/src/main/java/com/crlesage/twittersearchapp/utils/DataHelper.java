package com.crlesage.twittersearchapp.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Chris on 7/13/2016.
 * A library part of the FloatingSearchView to help with suggesting hashtags for the user!
 * Libray can be found at:
 * https://github.com/arimorty/floatingsearchview
 */
public class DataHelper {

    //TODO Have a service to run an update this json file with top current hashtags!!
    private static final String HASHTAG_FILE_NAME = "hashtag.json";

    private static LinkedHashSet<HashtagSuggestion> mHashtagSuggestionsJson = new LinkedHashSet<>();

    private static LinkedHashSet<HashtagSuggestion> mHashtagAll = new LinkedHashSet<>();

    private static LinkedHashSet<HashtagSuggestion> mHashHistory = new LinkedHashSet<>();

    public interface OnFindSuggestionsListener {
        void onResults(List<HashtagSuggestion> results);
    }

    public static List<HashtagSuggestion> getSuggestionsAndHistory (Context context, int count) {

        initHistoryAndSuggestionList(context);

        List<HashtagSuggestion> suggestionList = new ArrayList<>();
        // Get suggestions from History first if available
        if (mHashHistory != null) {
            for (HashtagSuggestion hashtagSuggestion : mHashHistory) {
                hashtagSuggestion.setIsHistory(true);
                suggestionList.add(hashtagSuggestion);
                if (suggestionList.size() == count) {
                    return suggestionList;
                }
            }
        }
        // Get suggestions from Json second
        if (mHashtagSuggestionsJson != null) {
            for (HashtagSuggestion hashtagSuggestion : mHashtagSuggestionsJson) {
                //hashtagSuggestion.setIsHistory(true);
                suggestionList.add(hashtagSuggestion);
                if (suggestionList.size() == count) {
                    return suggestionList;
                }
            }
        }
        return suggestionList;
    }

    public static void findAllSuggestions(Context context, String query, final int limit, final long simulatedDelay,
                                       final OnFindSuggestionsListener listener) {
        initHistoryAndSuggestionList(context);
        new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                try {
                    Thread.sleep(simulatedDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<HashtagSuggestion> suggestionList = new ArrayList<>();
                FilterResults results = new FilterResults();
                if (!(constraint == null || constraint.length() == 0)) {

                    for (HashtagSuggestion suggestion : mHashHistory) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestion.setIsHistory(true);
                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                results.values = suggestionList;
                                results.count = suggestionList.size();
                                return results;
                            }
                        }
                    }

                    for (HashtagSuggestion suggestion : mHashtagSuggestionsJson) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                results.values = suggestionList;
                                results.count = suggestionList.size();
                                return results;
                            }
                        }
                    }
                }

                //TODO test this!!
                Collections.sort(suggestionList, new Comparator<HashtagSuggestion>() {
                    @Override
                    public int compare(HashtagSuggestion lhs, HashtagSuggestion rhs) {
                        return lhs.getIsHistory() ? -1 : 0;
                    }
                });
                results.values = suggestionList;
                results.count = suggestionList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<HashtagSuggestion>) results.values);
                }
            }
        }.filter(query);

    }

    public static void initHistory(Context context) {
        //TODO: Make a boolean to see if history is changed (don't need to reset everytime)
        mHashHistory = SharedPref.getSearchHistory(context);
        if(mHashHistory == null){
            mHashHistory = new LinkedHashSet<>();
        }
    }

    private static void initSuggestions(Context context){
        if (mHashtagSuggestionsJson.isEmpty()) {
            String jsonString = loadJson(context);
            mHashtagSuggestionsJson = deserializeHashtags(jsonString);
        }
    }
    private static void initHistoryAndSuggestionList(Context context) {
        initSuggestions(context);
        initHistory(context);
    }

    private static String loadJson(Context context) {

        String jsonString;

        try {
            InputStream is = context.getAssets().open(HASHTAG_FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonString;
    }

    private static LinkedHashSet<HashtagSuggestion> deserializeHashtags(String jsonString) {

        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<LinkedHashSet<HashtagSuggestion>>(){}.getType());
    }

}