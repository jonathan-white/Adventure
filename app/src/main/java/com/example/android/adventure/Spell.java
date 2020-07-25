package com.example.android.adventure;

public class Spell {

    private String mTitle;
    private String mDescription;
    private String mRange;
    private String mDuration;
    private boolean mSave;
    private String mSchool;

    /**
     * Constructor for the Spell class
     * @param title is the name of the spell
     * @param description outlines the scope of the spell
     * @param range is the specific range of the spell
     * @param duration is how long it takes to cast the spell
     * @param save is whether or not the spell allows saving throws
     * @param school is the school the spell belongs to
     */
    public Spell(String title, String description, String range, String duration, boolean save,
                 String school) {
        mTitle = title;
        mDescription = description;
        mRange = range;
        mDuration = duration;
        mSave = save;
        mSchool = school;
    }

    /**
     *
     * @return the title of the spell
     */
    public String getTitle(){
        return mTitle;
    }

    /**
     *
     * @return a general description of the spell
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     *
     * @return the specific range of the spell
     */
    public String getRange() {
        return mRange;
    }

    /**
     *
     * @return the casting duration of the spell
     */
    public String getDuration() {
        return mDuration;
    }

    /**
     *
     * @return whether the spell allows for saving throws
     */
    public boolean getSave() {
        return mSave;
    }

    /**
     *
     * @return the school of the spell
     */
    public String getSchool() {
        return mSchool;
    }
}
