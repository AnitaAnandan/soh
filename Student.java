/*
 * Copyright (c) 2017. No guarantee. Feel free to reuse. Feedback is much appreciated.
 */

package soh;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by anitaa on 3/18/17.
 */
public class Student implements Comparable<Student>{
    public static int NUMBER_OF_WEEKS = 10;

    private String    edxid;

    private double[] positiveEmodiversity = new double[NUMBER_OF_WEEKS];
    private double[] negativeEmodiversity = new double[NUMBER_OF_WEEKS];
    private double[] totalEmodiversity = new double[NUMBER_OF_WEEKS];

    private int[]     maxNumEmotionsCheckedInThisWeek = new int[NUMBER_OF_WEEKS];
    private Checkin[] selectedCheckin = new Checkin[NUMBER_OF_WEEKS];
    // TODO: Create arraylist of set of checkins etc.
    //private List<Set<Checkin>> checkins2 = new ArrayList<Set<Checkin>>();
    private TreeSet<Checkin> checkinsW01 = new TreeSet<>();
    private TreeSet<Checkin> checkinsW02 = new TreeSet<>();
    private TreeSet<Checkin> checkinsW03 = new TreeSet<>();
    private TreeSet<Checkin> checkinsW04 = new TreeSet<>();
    private TreeSet<Checkin> checkinsW05 = new TreeSet<>();
    private TreeSet<Checkin> checkinsW06 = new TreeSet<>();
    private TreeSet<Checkin> checkinsW07 = new TreeSet<>();
    private TreeSet<Checkin> checkinsW08 = new TreeSet<>();
    private TreeSet<Checkin> checkinsW09 = new TreeSet<>();
    private TreeSet<Checkin> checkinsW10 = new TreeSet<>();

    public Student(String edxid) {
        this.edxid = edxid;
        //checkins2 = new ArrayList<TreeSet<Checkin>>();
    }

    public String getEdxid() {
        return edxid;
    }

    public void doCheckin(int week, Checkin checkin) {
        if(checkin != null) {
            // If two checkins have the same timestamp (which shouldn't happen),
            // and different number of emotions checkedin
            //TreeSet<Checkin> checkins = getCheckinForThisWeek(week);
            if (getCheckinForThisWeek(week).contains(checkin)) {
                if (getCheckinForThisWeek(week).floor(checkin).getNumEmotionsCheckedin() < checkin.getNumEmotionsCheckedin()) {
                    getCheckinForThisWeek(week).remove(getCheckinForThisWeek(week).floor(checkin));
                    getCheckinForThisWeek(week).add(checkin);
                }
            }

            getCheckinForThisWeek(week).add(checkin);
            if(checkin.getNumEmotionsCheckedin() > maxNumEmotionsCheckedInThisWeek[week]) {
                maxNumEmotionsCheckedInThisWeek[week] = checkin.getNumEmotionsCheckedin();
            }
        }
    }

    public void selectCheckinAndCalculateEmoDiversity() {
        for (int i = 0; i < NUMBER_OF_WEEKS; i++)
            selectCheckinAndCalculateEmoDiversity(i);
    }

    private void selectCheckinAndCalculateEmoDiversity(int week) {
        Set<Checkin> checkins = getCheckinForThisWeek(week);

        if (checkins == null || checkins.isEmpty()) return;
        if (selectedCheckin != null && selectedCheckin[week] != null)
            return;

        for (Checkin c : getCheckinForThisWeek(week)) {
            if (c.getNumEmotionsCheckedin() == maxNumEmotionsCheckedInThisWeek[week]) {
                selectedCheckin[week] = c;
                calculateEmoDiversity(week);

                return;
            }
        }
        return;//TODO: This line should never be executed. Fix.
    }

    // TODO Enforce: Should only be called when selection is completed
    void calculateEmoDiversity(int week) {
        if (selectedCheckin[week] == null) return;

        int nScore = 0, pScore = 0, tScore = 0;
        nScore += selectedCheckin[week].getEAnger()
                + selectedCheckin[week].getEAnxiety()
                + selectedCheckin[week].getESadness();
        pScore += selectedCheckin[week].getEJoy()
                + selectedCheckin[week].getEFriendliness()
                + selectedCheckin[week].getECuriosity();
        tScore += pScore + nScore;

        double negativeEmodiversity;
        double propAnger = getPropEmotion(selectedCheckin[week].getEAnger(), nScore);
        double propAnxiety = getPropEmotion(selectedCheckin[week].getEAnxiety(), nScore);
        double propSadness = getPropEmotion(selectedCheckin[week].getESadness(), nScore);
        negativeEmodiversity = propAnger * java.lang.Math.log(propAnger)
                + propAnxiety * java.lang.Math.log(propAnxiety)
                + propSadness * java.lang.Math.log(propSadness);

        double positiveEmodiversity;
        double propJoy = getPropEmotion(selectedCheckin[week].getEJoy(), pScore);
        double propFriendliness = getPropEmotion(selectedCheckin[week].getEFriendliness(), pScore);
        double propCuriosity = getPropEmotion(selectedCheckin[week].getECuriosity(), pScore);
        positiveEmodiversity = propJoy * java.lang.Math.log(propJoy)
                + propFriendliness * java.lang.Math.log(propFriendliness)
                + propCuriosity * java.lang.Math.log(propCuriosity);

        double totalEmodiversity;
        propAnger = getPropEmotion(selectedCheckin[week].getEAnger(), tScore);
        propAnxiety = getPropEmotion(selectedCheckin[week].getEAnxiety(), tScore);
        propSadness = getPropEmotion(selectedCheckin[week].getESadness(), tScore);
        propJoy = getPropEmotion(selectedCheckin[week].getEJoy(), tScore);
        propFriendliness = getPropEmotion(selectedCheckin[week].getEFriendliness(), tScore);
        propCuriosity = getPropEmotion(selectedCheckin[week].getECuriosity(), tScore);
        totalEmodiversity = propAnger * java.lang.Math.log(propAnger)
                + propAnxiety * java.lang.Math.log(propAnxiety)
                + propSadness * java.lang.Math.log(propSadness)
                + propJoy * java.lang.Math.log(propJoy)
                + propFriendliness * java.lang.Math.log(propFriendliness)
                + propCuriosity * java.lang.Math.log(propCuriosity);

        this.negativeEmodiversity[week] = negativeEmodiversity;
        this.positiveEmodiversity[week] = positiveEmodiversity;
        this.totalEmodiversity[week] = totalEmodiversity;

    }

    private long getPropEmotion(int emotion, int total) {
        if (total == 0) // avoid divide by 0
            return 0;
        else
            return emotion / total;
    }

    public double getPositiveEmodiversity(int week) {
        return positiveEmodiversity[week];
    }

    public double getNegativeEmodiversity(int week) {
        return negativeEmodiversity[week];
    }

    public double getTotalEmodiversity(int week) {
        return totalEmodiversity[week];
    }

    public Checkin getSelectedCheckin(int week) {
        return selectedCheckin[week];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return edxid.equals(student.edxid);
    }

    @Override
    public int hashCode() {
        return edxid.hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "edxid='" + edxid + '\'' +
                '}';
    }

    public int compareTo(Student s) {
        if (s == null) {
            return 0; //??
        }
        return this.edxid.compareTo(s.edxid);
    }

    private TreeSet<Checkin> getCheckinForThisWeek(int week) {
        switch (week + 1) {
            case  1: return checkinsW01;
            case  2: return checkinsW02;
            case  3: return checkinsW03;
            case  4: return checkinsW04;
            case  5: return checkinsW05;
            case  6: return checkinsW06;
            case  7: return checkinsW07;
            case  8: return checkinsW08;
            case  9: return checkinsW09;
            case 10: return checkinsW10;
            default: return null;
        }
    }
}
