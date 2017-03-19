package soh.emotion;

/**
 * Created by anitaa on 3/18/17.
 */
public abstract class PositiveEmotion extends Emotion {

    public PositiveEmotion(int score) throws Throwable{
        super(score);
        positive = true;
    }
}
