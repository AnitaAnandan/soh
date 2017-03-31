/*
 * Copyright (c) 2017. No guarantee. Feel free to reuse. Feedback is much appreciated.
 */

package soh.emotion;

/**
 * Created by anitaa on 3/18/17.
 */
public abstract class NegativeEmotion extends Emotion {

    public NegativeEmotion(int score) throws Throwable{
        super(score);
        positive = false;
    }
}
