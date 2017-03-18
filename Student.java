package soh;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by anitaa on 3/18/17.
 */
public class Student implements Comparable<Student>{
    private static int NUMBER_OF_WEEKS = 10;

    private String      edxid;
    private int[]       maxNumberOfEmotions = new int[NUMBER_OF_WEEKS];
    private Checkin[]   selectedCheckin = new Checkin[NUMBER_OF_WEEKS];

    public Student(String edxid) {
        this.edxid = edxid;
        //checkins2 = new ArrayList<TreeSet<Checkin>>();
    }

    public void checkin(Checkin checkin, int week) {
        if(checkin != null) {
            getCheckinVariableName(week).add(checkin);
            if(checkin.getNumberOfEmotions() > maxNumberOfEmotions[week]) {
                maxNumberOfEmotions[week] = checkin.getNumberOfEmotions();
            }
        }
    }

    public Checkin selectCheckin(int week) {
        for (Checkin c:getCheckinVariableName(week)) {
            if (selectedCheckin[week].getNumberOfEmotions() == maxNumberOfEmotions[week]) {
                selectedCheckin[week] = c;
            }
        }
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


    // TODO: Create arraylist of set of checkins etc.
    //private List<Set<Checkin>> checkins2 = new ArrayList<Set<Checkin>>();
    private Set<Checkin> checkinsW01 = new TreeSet<>();
    private Set<Checkin> checkinsW02 = new TreeSet<>();
    private Set<Checkin> checkinsW03 = new TreeSet<>();
    private Set<Checkin> checkinsW04 = new TreeSet<>();
    private Set<Checkin> checkinsW05 = new TreeSet<>();
    private Set<Checkin> checkinsW06 = new TreeSet<>();
    private Set<Checkin> checkinsW07 = new TreeSet<>();
    private Set<Checkin> checkinsW08 = new TreeSet<>();
    private Set<Checkin> checkinsW09 = new TreeSet<>();
    private Set<Checkin> checkinsW10 = new TreeSet<>();
    private Set<Checkin> getCheckinVariableName(int week) {
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
