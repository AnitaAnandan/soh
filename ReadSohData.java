package soh;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by anitaa on 3/17/17.
 */
public class ReadSohData {
    public static Set<Student> registeredStudents;

    public static void main(String[] args) {
        registerStudents();
        checkinStudents();
        selectCheckin();
    }

    private static void registerStudents() {
        // for each line in CSV, read edxIds
        String[] edxidsFromFile = {"abc", "cde", "abc", "bcd"};

        //List<Student> students = new ArrayList<Student>();
        registeredStudents = new TreeSet<>();
        for (String edxid: edxidsFromFile) {
            Student s = new Student(edxid);
            registeredStudents.add(s);
        }
        System.out.println("There are " + registeredStudents.size() + " students:");
        System.out.println(registeredStudents);

    }

    private static void checkinStudents() {
        try {
            Emotion e1 = new Emotion(1);
            Emotion e2 = new Emotion(2);
            Emotion e3 = new Emotion(3);
            Emotion e4 = new Emotion(4);
            Emotion e5 = new Emotion(5);
            Emotion e6 = new Emotion(6);
            Emotion[] emotions = new Emotion[6];
            emotions[0] = e1;
            emotions[1] = e2;
            emotions[2] = e3;
            emotions[3] = e4;
            emotions[4] = e5;
            emotions[5] = e6;

            Date date = new Date();
            Student s = new Student("abc");
            if (registeredStudents.contains(s) /* && date */) {
                Checkin c = new Checkin(date, emotions);
                s.checkin(c, 1);
            }
        } catch (Throwable e) {
        }
    }

        private static void selectCheckin() {
        for (Student s:registeredStudents) {
            s.selectCheckin(1);
        }
    }
}
