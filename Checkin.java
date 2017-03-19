package soh;

import soh.emotion.*;

import java.util.Date;

/**
 * Created by anitaa on 3/18/17.
 */
public class Checkin implements Comparable<Checkin>{
    private Date date;//TODO: (1) Date deprecated. Use Calendar (2) Need timestamp

    private Joy         joy;
    private Happiness   happiness;
    private Curiosity   curiosity;
    private Anger       anger;
    private Anxiety     anxiety;
    private Sadness     sadness;

    public Checkin(Date date) {
        this.date = date;
    }

    public int getNumEmotionsCheckedin() {
        int i = 0;
        if (joy       != null) i++;
        if (happiness != null) i++;
        if (curiosity != null) i++;
        if (anger     != null) i++;
        if (anxiety   != null) i++;
        if (sadness   != null) i++;
        return i;
    }

    public Joy getJoy() {
        return joy;
    }

    public void setJoy(int score) throws Throwable {
        this.joy = new Joy(score);
    }

    public Happiness getHappiness() {
        return happiness;
    }

    public void setHappiness(Happiness happiness) {
        this.happiness = happiness;
    }

    public Curiosity getCuriosity() {
        return curiosity;
    }

    public void setCuriosity(Curiosity curiosity) {
        this.curiosity = curiosity;
    }

    public Anger getAnger() {
        return anger;
    }

    public void setAnger(int score) throws Throwable {
        this.anger = new Anger(score);
    }

    public Anxiety getAnxiety() {
        return anxiety;
    }

    public void setAnxiety(Anxiety anxiety) {
        this.anxiety = anxiety;
    }

    public Sadness getSadness() {
        return sadness;
    }

    public void setSadness(Sadness sadness) {
        this.sadness = sadness;
    }

    @Override
    public int compareTo(Checkin c) {
        if (c == null) {
            return 0; //??
        }
        return this.date.compareTo(c.date);
    }
}
