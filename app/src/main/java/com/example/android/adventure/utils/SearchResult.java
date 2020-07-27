package com.example.android.adventure.utils;

public class SearchResult {
    private String mTitle;
    private String mDescription;

    public SearchResult() {

    }

    public SearchResult(String title, String description) {
        mTitle = title;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }
}
