package soh;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by anitaa on 3/18/17.
 */
public class Student implements Comparable<Student>{
    public static int NUMBER_OF_WEEKS = 10;

    private String    edxid;
    private int[]     maxNumEmotionsCheckedInThisWeek = new int[NUMBER_OF_WEEKS];
    private Checkin[] selectedCheckinThisWeek         = new Checkin[NUMBER_OF_WEEKS];
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

    public void doCheckin(Checkin checkin, int week) {
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
            }/*
            if (selectedCheckinThisWeek[week] == null) {
                selectedCheckinThisWeek[week] = checkin;
            } else {
                selectCheckin(week);
            }*/
        }
    }

    public Checkin selectCheckin(int week) {
        Set<Checkin> checkins = getCheckinForThisWeek(week);

        if(checkins == null || checkins.isEmpty()) return null;
        for (Checkin c : getCheckinForThisWeek(week)) {
            //if (selectedCheckinThisWeek[week])
            /*
            if (selectedCheckinThisWeek[week].getNumEmotionsCheckedin() == maxNumEmotionsCheckedInThisWeek[week]) {
                selectedCheckinThisWeek[week] = c;
            }*/
            if (c.getNumEmotionsCheckedin() == maxNumEmotionsCheckedInThisWeek[week]) {
                return selectedCheckinThisWeek[week] = c;
            }
        }
        return null;//TODO: This line should never be executed. Fix.
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
        switch(week) {
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
