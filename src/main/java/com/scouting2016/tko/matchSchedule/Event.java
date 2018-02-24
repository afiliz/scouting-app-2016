package com.scouting2016.tko.matchSchedule;

/**
 * Created by rahul on 3/22/16.
 */
public class Event {
    protected String name;
    protected String code;

    public Event(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
