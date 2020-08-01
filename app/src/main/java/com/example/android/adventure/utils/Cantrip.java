package com.example.android.adventure.utils;

public class Cantrip {
    private String title;
    private String description;
    private String duration;

    public Cantrip() { super(); }

    /**
     *
     * @param mTitle of the cantrip
     * @param mDescription is the description of the cantrip
     */
    public Cantrip(String mTitle, String mDescription) {
        title = mTitle;
        description = mDescription;
    }

    /**
     *
     * @param mTitle of the cantrip
     * @param mDescription is the description of the cantrip
     * @param mDuration is casting duration
     */
    public Cantrip(String mTitle, String mDescription, String mDuration) {
        title = mTitle;
        description = mDescription;
        duration = mDuration;
    }

    /**
     *
     * @return title of the cantrip
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    /**
     *
     * @return the description of the cantrip
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String mDesription) {
        description = mDesription;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String mDuration) {
        duration = mDuration;
    }
}
