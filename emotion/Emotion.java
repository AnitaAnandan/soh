package soh.emotion;

/**
 * Created by anitaa on 3/18/17.
 */
public abstract class Emotion {
    //private String name;
    protected boolean positive;
    private int score;

    //public static String {JOY, CURIOUS, HAPPY};
    //public static enum negative_emotions = {"ANGER", "ANXIETY", "SADNESS"};
    //public enum PostiveEmotion {JOY, CURIOUS, HAPPY};


    public Emotion(int score) throws Throwable {
        //name = this.name;
        if (score >= 1 && score <= 10) {
            this.score = score;
        } else {
            throw new Throwable("Emotions's score " + score + " is not between 1-10");
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }
}

