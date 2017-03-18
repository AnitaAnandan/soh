package soh;

import java.util.Date;

/**
 * Created by anitaa on 3/18/17.
 */
public class Checkin implements Comparable<Checkin>{
    private Emotion[] emotions;
    private Date date;
    private int numberOfEmotions;

    private boolean accepted = false;

    public Checkin(Date date, Emotion[] emotions) {
        this.date = date;

        this.emotions = emotions;
        for (Emotion e:emotions) {
            if (e != null)
                numberOfEmotions++;

        }
    }

    public int getNumberOfEmotions() {
        return numberOfEmotions;
    }

    @Override
    public int compareTo(Checkin c) {
        if (c == null) {
            return 0; //??
        }
        return this.date.compareTo(c.date);
    }
}
