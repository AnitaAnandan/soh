package soh;

import soh.emotion.*;

import java.util.Calendar;

/**
 * Created by anitaa on 3/18/17.
 */
public class Checkin implements Comparable<Checkin>{
    private Calendar date;

    private Joy         joy;
    private Happiness   happiness;
    private Curiosity   curiosity;
    private Anger       anger;
    private Anxiety     anxiety;
    private Sadness     sadness;

    public Checkin(Calendar date) {
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

    //TODO: Returning 0 if null. Is this correct?
    public int getJoy() {
        if(joy == null)
            return 0;
        else
            return joy.getScore();
    }

    public void setJoy(int score) throws Throwable {
        this.joy = new Joy(score);
    }

    public int getHappiness() {
        if(happiness == null)
            return 0;
        else
            return happiness.getScore();
    }

    public void setHappiness(int score) throws Throwable {
        this.happiness = new Happiness(score);
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
                "date=" + date +
                ", joy=" + joy +
                ", happiness=" + happiness +
                ", curiosity=" + curiosity +
                ", anger=" + anger +
                ", anxiety=" + anxiety +
                ", sadness=" + sadness +
                '}';
    }
}
