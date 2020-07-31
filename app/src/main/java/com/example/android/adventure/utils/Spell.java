package com.example.android.adventure.utils;

import android.content.Context;

public class Spell {

    private String title;
    private String description;
    private String range;
    private String duration;
    private boolean save;
    private String school;

    public Spell() {
        super();
    }

    /**
     * Constructor for the Spell class
     * @param mTitle is the name of the spell
     * @param mDescription outlines the scope of the spell
     * @param mRange is the specific range of the spell
     * @param mDuration is how the spell will last
     * @param mSave is whether or not the spell allows saving throws
     * @param mSchool is the school the spell belongs to
     */
    public Spell(String mTitle, String mDescription, String mRange, String mDuration, boolean mSave,
                 String mSchool) {
        title = mTitle;
        description = mDescription;
        range = mRange;
        duration = mDuration;
        save = mSave;
        school = mSchool;
    }

    /**
     *
     * @return the title of the spell
     */
    public String getTitle(){
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    /**
     *
     * @return a general description of the spell
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        description = mDescription;
    }

    /**
     *
     * @return the specific range of the spell
     */
    public String getRange() {
        return range;
    }

    public void setRange(String mRange) {
        range = mRange;
    }

    /**
     *
     * @return the casting duration of the spell
     */
    public String getDuration() {
        return duration;
    }

    public void setDuration(String mDuration) {
        duration = mDuration;
    }

    /**
     *
     * @return whether the spell allows for saving throws
     */
    public boolean getSave() {
        return save;
    }

    public void setSave(boolean mSave) {
        save = mSave;
    }

    /**
     *
     * @return the school of the spell
     */
    public String getSchool() {
        return school;
    }


    /**
     *
     * @param mSchool sets the school attribute
     */
    public void setSchool(String mSchool) {
        school = mSchool;
    }
}
