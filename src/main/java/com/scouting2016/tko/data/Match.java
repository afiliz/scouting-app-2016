package com.scouting2016.tko.data;

/**
 * Created by rahul on 3/23/16.
 */
public class Match {
    private String startTime, matchLevel;
    private int matchNum, red1, red2, red3, blue1, blue2, blue3;

    public Match(String startTime, String matchLevel, int matchNum, int red1, int red2, int red3, int blue1, int blue2, int blue3) {
        this.startTime = startTime;
        this.matchLevel = matchLevel;
        this.matchNum = matchNum;
        this.red1 = red1;
        this.red2 = red2;
        this.red3 = red3;
        this.blue1 = blue1;
        this.blue2 = blue2;
        this.blue3 = blue3;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public int getRed1() {
        return red1;
    }

    public int getRed2() {
        return red2;
    }

    public int getRed3() {
        return red3;
    }

    public int getBlue1() {
        return blue1;
    }

    public int getBlue2() {
        return blue2;
    }

    public int getBlue3() {
        return blue3;
    }
}
