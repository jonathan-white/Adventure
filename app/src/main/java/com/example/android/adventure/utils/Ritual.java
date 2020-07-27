package com.example.android.adventure.utils;

public class Ritual {
    private String mTitle;
    private String mContent;
    private String mRange;
    private String mDuration;
    private boolean mSave;
    private String mSchool;

    /**
     * Constructor for the Ritual class
     * @param title is the name of the ritual
     * @param content outlines the scope of the ritual
     * @param range is the specific range of the ritual
     * @param duration is how the ritual will last
     * @param save is whether or not the ritual allows saving throws
     */
    public Ritual(String title, String content, String range, String duration, boolean save) {
        mTitle = title;
        mContent = content;
        mRange = range;
        mDuration = duration;
        mSave = save;
    }


    /**
     * Constructor for the Ritual class
     * @param title is the name of the ritual
     * @param content outlines the scope of the ritual
     * @param range is the specific range of the ritual
     * @param duration is how the ritual will last
     * @param save is whether or not the ritual allows saving throws
     * @param school is the school the ritual belongs to
     */
    public Ritual(String title, String content, String range, String duration, boolean save,
                  String school) {
        mTitle = title;
        mContent = content;
        mRange = range;
        mDuration = duration;
        mSave = save;
        mSchool = school;
    }

    /**
     *
     * @return the title of the ritual
     */
    public String getTitle(){
        return mTitle;
    }

    /**
     *
     * @return a general description of the ritual
     */
    public String getContent() {
        return mContent;
    }

    /**
     *
     * @return the specific range of the ritual
     */
    public String getRange() {
        return mRange;
    }

    /**
     *
     * @return the casting duration of the ritual
     */
    public String getDuration() {
        return mDuration;
    }

    /**
     *
     * @return whether the ritual allows for saving throws
     */
    public boolean getSave() {
        return mSave;
    }

    /**
     *
     * @return the school of the ritual
     */
    public String getSchool() {
        return mSchool;
    }
}
