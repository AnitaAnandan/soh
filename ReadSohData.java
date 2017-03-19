package soh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        //checkinStudents();
        //selectCheckin();
    }

    private static void registerStudents() {
        String registeredUsersFile = "/Users/anitaa/Documents/Happiness/ScienceOfHappiness/data/registeredUsers/registeredUsers_2016F_edx_Mar112017.csv";
        BufferedReader br = null;
        String line = "";
        String delimiter = ",";
        registeredStudents = new TreeSet<>();

        try {
            br = new BufferedReader(new FileReader(registeredUsersFile));
            //TODO: Skip header
            while ((line = br.readLine()) != null) {
                String edxid = line;
            Student s = new Student(edxid);
            registeredStudents.add(s);
        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("There are " + registeredStudents.size() + " students:");
    }

    private static void checkinStudents() {
        try {
            /*
            Anger       a  = new Anger(1);
            Sadness     s  = new Sadness(2);
            Happiness   h  = new Happiness(3);
            Curiosity   c  = new Curiosity(4);
            Joy         j  = new Joy(5);
            Anxiety     ax = new Anxiety(6);
            Emotion[] emotions = new Emotion[6];
            emotions[0] = e1;
            emotions[1] = e2;
            emotions[2] = e3;
            emotions[3] = e4;
            emotions[4] = e5;
            emotions[5] = e6;*/

            Date date = new Date();
            Student student = new Student("abc");
            if (registeredStudents.contains(student) /* && date */) {
                Checkin checkin = new Checkin(date);
                student.doCheckin(checkin, 1);
                //checkin.setAnger(a);
                checkin.setAnxiety(1);
                checkin.setSadness(2);
                //checkin.setJoy(5);
                checkin.setHappiness(3);
                checkin.setCuriosity(4);
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
