package soh;

/**
 * Created by anitaa on 3/18/17.
 */
public class Emotion {
    private String name;
    private int score;

    public Emotion(int score) throws Throwable {
        if (score >= 1 && score <= 10) {
            this.score = score;
        } else {
            throw new Throwable ("Emotions's score " + score + " is not between 1-10");
        }
    }
}
