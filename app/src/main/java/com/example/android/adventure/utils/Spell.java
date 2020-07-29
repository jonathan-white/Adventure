package com.example.android.adventure.utils;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Spell extends RealmObject {

    @PrimaryKey
    ObjectId _id = new ObjectId();
    String _partitionKey = "osa";

    private String title;
    private String description;
    private String range;
    private String duration;
    private boolean save;
    private String school;

    public Spell() {

    }

    /**
     * Constructor for the Spell class
     * @param cTitle is the name of the spell
     * @param cDescription outlines the scope of the spell
     * @param cRange is the specific range of the spell
     * @param cDuration is how the spell will last
     * @param cSave is whether or not the spell allows saving throws
     * @param cSchool is the school the spell belongs to
     */
    public Spell(String cTitle, String cDescription, String cRange, String cDuration, boolean cSave,
                 String cSchool) {
        title = cTitle;
        description = cDescription;
        range = cRange;
        duration = cDuration;
        save = cSave;
        school = cSchool;
    }

    /**
     *
     * @return the title of the spell
     */
    public String getTitle(){
        return title;
    }

    /**
     *
     * @return a general description of the spell
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return the specific range of the spell
     */
    public String getRange() {
        return range;
    }

    /**
     *
     * @return the casting duration of the spell
     */
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @return whether the spell allows for saving throws
     */
    public boolean getSave() {
        return save;
    }

    /**
     *
     * @return the school of the spell
     */
    public String getSchool() {
        return school;
    }
}
