/*
 * Copyright (c) 2017. No guarantee. Feel free to reuse. Feedback is much appreciated.
 */

package soh;

import soh.emotion.*;

import java.util.Calendar;

/**
 * Created by anitaa on 3/18/17.
 */
public class Checkin implements Comparable<Checkin>{
    private Calendar date;

    private Joy         joy;
    private Friendliness friendliness;
    private Curiosity   curiosity;
    private Anger       anger;
    private Anxiety     anxiety;
    private Sadness     sadness;

    public Checkin(Calendar date) {
        this.date = date;
    }

    public int getNumEmotionsCheckedin() {
        int i = 0;
        if (joy != null && joy.getScore() != 0) i++;
        if (friendliness != null && friendliness.getScore() != 0) i++;
        if (curiosity != null && curiosity.getScore() != 0) i++;
        if (anger != null && anger.getScore() != 0) i++;
        if (anxiety != null && anxiety.getScore() != 0) i++;
        if (sadness != null && sadness.getScore() != 0) i++;
        return i;
    }

    int getEAnger() {
        if (getAnger() == 0) return 0;
        else return getAnger() - 1;
    }

    int getEAnxiety() {
        if (getAnxiety() == 0) return 0;
        else return getAnxiety() - 1;
    }

    int getESadness() {
        if (getSadness() == 0) return 0;
        else return getSadness() - 1;
    }

    int getEJoy() {
        if (getJoy() == 0) return 0;
        else return getJoy() - 1;
    }

    int getEFriendliness() {
        if (getFriendliness() == 0) return 0;
        else return getFriendliness() - 1;
    }

    int getECuriosity() {
        if (getCuriosity() == 0) return 0;
        else return getCuriosity() - 1;
    }

    public int getJoy() {
        if (joy == null)
            return 0;
        else
            return joy.getScore();
    }

    public void setJoy(int score) throws Throwable {
        this.joy = new Joy(score);
    }

    public int getFriendliness() {
        if (friendliness == null)
            return 0;
        else
            return friendliness.getScore();
    }

    public void setFriendliness(int score) throws Throwable {
        this.friendliness = new Friendliness(score);
    }

    public int getCuriosity() {
        if(curiosity == null)
            return 0;
        else
            return curiosity.getScore();
    }

    public void setCuriosity(int score) throws Throwable {
        this.curiosity = new Curiosity(score);
    }

    public int getAnger() {
        if(anger == null)
            return 0;
        else
            return anger.getScore();
    }

    public void setAnger(int score) throws Throwable {
        this.anger = new Anger(score);
    }

    public int getAnxiety() {
        if(anxiety == null)
            return 0;
        else
            return anxiety.getScore();
    }

    public void setAnxiety(int score) throws Throwable {
        this.anxiety = new Anxiety(score);
    }

    public int getSadness() {
        if(sadness == null)
            return 0;
        else
            return sadness.getScore();
    }

    public void setSadness(int score) throws Throwable {
        this.sadness = new Sadness(score);
    }

    public Calendar getDate() {
        return date;
    } //TODO: Return a nicely formatted date

    @Override
    public int compareTo(Checkin c) {
        if (c == null) {
            return 0; //??
        }
        return (this.date.compareTo(c.date));
    }

    @Override
    public String toString() {
        return "Checkin{" +
                "date=" + date + "\n" +
                ", joy=" + joy +
                ", friendliness=" + friendliness +
                ", curiosity=" + curiosity +
                ", anger=" + anger +
                ", anxiety=" + anxiety +
                ", sadness=" + sadness +
                '}';
    }
}
