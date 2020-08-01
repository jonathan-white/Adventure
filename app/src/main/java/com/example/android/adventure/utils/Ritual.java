package com.example.android.adventure.utils;

public class Ritual {
    private String title;
    private String description;
    private String range;
    private String duration;
    private boolean save;
    private String school;

    /**
     * Mandatory empty constructor for the viewModel to instantiate the class
     * (e.g. upon screen orientation changes).
     */
    public Ritual() {
        super();
    }

    /**
     * Constructor for the Ritual class
     * @param mTitle is the name of the ritual
     * @param mDescription outlines the scope of the ritual
     * @param mRange is the specific range of the ritual
     * @param mDuration is how the ritual will last
     * @param mSave is whether or not the ritual allows saving throws
     */
    public Ritual(String mTitle, String mDescription, String mRange, String mDuration, boolean mSave) {
        title = mTitle;
        description = mDescription;
        range = mRange;
        duration = mDuration;
        save = mSave;
    }

    /**
     * Constructor for the Ritual class
     * @param mTitle is the name of the ritual
     * @param mDescription outlines the scope of the ritual
     * @param mRange is the specific range of the ritual
     * @param mDuration is how the ritual will last
     * @param mSave is whether or not the ritual allows saving throws
     * @param mSchool is the school the ritual belongs to
     */
    public Ritual(String mTitle, String mDescription, String mRange, String mDuration, boolean mSave,
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
     * @return the title of the ritual
     */
    public String getTitle(){
        return title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

        /**
     *
     * @return a general description of the ritual
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        description = mDescription;
    }

    /**
     *
     * @return the specific range of the ritual
     */
    public String getRange() {
        return range;
    }

    public void setRange(String mRange) {
        range = mRange;
    }

    /**
     *
     * @return the casting duration of the ritual
     */
    public String getDuration() {
        return duration;
    }

    public void setDuration(String mDuration) {
        duration = mDuration;
    }

    /**
     *
     * @return whether the ritual allows for saving throws
     */
    public boolean getSave() {
        return save;
    }

    public void setSave(boolean mSave) {
        save = mSave;
    }

    /**
     *
     * @return the school of the ritual
     */
    public String getSchool() {
        return school;
    }

    public void setSchool(String mSchool) {
        school = mSchool;
    }
}
