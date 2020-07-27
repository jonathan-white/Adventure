package com.example.android.adventure.utils;

public class Cantrip {
    private String mTitle;
    private String mContent;
    private String mDuration;

    /**
     *
     * @param title of the cantrip
     * @param content is the description of the cantrip
     */
    public Cantrip(String title, String content) {
        mTitle = title;
        mContent = content;
    }

    /**
     *
     * @param title of the cantrip
     * @param content is the description of the cantrip
     * @param duration is casting duration
     */
    public Cantrip(String title, String content, String duration) {
        mTitle = title;
        mContent = content;
        mDuration = duration;
    }

    /**
     *
     * @return title of the cantrip
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     *
     * @return the description of the cantrip
     */
    public String getContent() {
        return mContent;
    }
}
