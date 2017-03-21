package soh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by anitaa on 3/17/17.
 */
public class ReadSohData {
    public static Set<Student> registeredStudents;
    private static String USERS_TEST = "/Users/anitaa/Documents/Happiness/ScienceOfHappiness/data/registeredUsers/test/registeredUsers_2016F_edx_Mar112017.csv";
    private static String USERS_REAL = "/Users/anitaa/Documents/Happiness/ScienceOfHappiness/data/registeredUsers/registeredUsers_2016F_edx_Mar112017.csv";
    private static String CHECKIN_DIRECTORY_TEST = "/Users/anitaa/Documents/Happiness/ScienceOfHappiness/data/checkin/test/";
    private static String CHECKIN_DIRECTORY_REAL = "/Users/anitaa/Documents/Happiness/ScienceOfHappiness/data/checkin/";
    private static String CHECKIN_PREFIX = "checkin_week";
    private static String CHECKIN_POSTFIX = "_qualtrics_Mar112017.csv";
    private static DateFormat dateFormat = new SimpleDateFormat("mm/dd/yy hh:mm"); // 1/4/16 22:35 //TODO: dateFormat.setLenient(false);?
    private static Calendar start = new GregorianCalendar(2016, 8, 1, 0, 0, 0);
    private static Calendar end = new GregorianCalendar(2016, 12, 31, 23, 59, 59);

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        //registerStudents(USERS_TEST); // 10 users
        checkinStudents();
        //selectCheckin();
    }

    private static void registerStudents(String registeredUsersFile) {
        BufferedReader br = null;
        String line = "";
        registeredStudents = new TreeSet<>();

        try {
            br = new BufferedReader(new FileReader(registeredUsersFile));
            if ((line = br.readLine()).equals("Anonymized User ID")) {
                //TODO: Better way to skip header
            }
            while ((line = br.readLine()) != null) {
                String edxid = line;
                Student s = new Student(edxid);
                System.out.println(edxid);
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

        String file = CHECKIN_DIRECTORY_TEST + CHECKIN_PREFIX + "01" + CHECKIN_POSTFIX;
        System.out.println("Reading " + file);
        BufferedReader br = null;
        String line = "";
        String delimiter = ",";
        try {

            br = new BufferedReader(new FileReader(file));
            String[] checkinRow = br.readLine().split(delimiter); //TODO: Skip header

            int edxid;
            int anger;
            int anxiety;
            int sadness;
            int joy;
            int happiness;
            int curiosity;

            Calendar date;
            Date d;
            int year;
            int month;
            int day;
            int hour;
            int min;
            for (int i = 1; (line = br.readLine()) != null; i++) {
                System.out.println("Row: " + i);
                checkinRow = line.split(delimiter);

                // TODO: This sucks
                System.out.println(checkinRow[0]);
                d = dateFormat.parse(checkinRow[0]);
                year = d.getYear();
                month = d.getMonth();
                day = d.getDay();
                hour = d.getHours();
                min = d.getMinutes();
                date = new GregorianCalendar(year, month, day, hour, min);
                System.out.println(checkinRow[0]);
                //System.out.println(date);

                if (checkinRow[1] != "") anger = Integer.parseInt(checkinRow[1]);
                if (checkinRow[2] != "") anxiety = Integer.parseInt(checkinRow[2]);
                if (checkinRow[3] != "") sadness = Integer.parseInt(checkinRow[3]);
                if (checkinRow[4] != "") joy = Integer.parseInt(checkinRow[4]);
                if (checkinRow[5] != "") happiness = Integer.parseInt(checkinRow[5]);
                if (checkinRow[6] != "") curiosity = Integer.parseInt(checkinRow[6]);
                // checkinRow[7]; TODO: sc0
                edxid = Integer.parseInt(checkinRow[8]);

                /*
                System.out.println(checkinRow[0] + " , ");
                System.out.println(checkinRow[1] + " , ");
                System.out.println(checkinRow[2] + " , ");
                System.out.println(checkinRow[3] + " , ");
                System.out.println(checkinRow[4] + " , ");
                System.out.println(checkinRow[5] + " , ");
                System.out.println(checkinRow[6] + " , ");
                System.out.println(checkinRow[7] + " , ");
                System.out.println(checkinRow[8] + " , ");
                */
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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

    }
    /*
        // Skip 1st line
        try {


            int unregisteredStudentsWhoCheckedIn = 0;
            int outsideDateRange = 0;

            Student student = new Student("abc");
            if (!registeredStudents.contains(student)) {
                unregisteredStudentsWhoCheckedIn++;
                // TODO: break?
            }
            if (date.before(start) || date.after(end)) {
                outsideDateRange++;
            }
            try {
                Checkin checkin = new Checkin(date);
                checkin.setAnger(anger);
                checkin.setAnxiety(anxiety);
                checkin.setSadness(sadness);
                checkin.setJoy(joy);
                checkin.setHappiness(happiness);
                checkin.setCuriosity(curiosity);
                student.doCheckin(checkin, 1);
            } catch (Throwable e) {
            } finally {
                // TODO
            }
        } finally {
            // TODO
        }
    }


        private static void selectCheckin() {
        for (Student s:registeredStudents) {
            s.selectCheckin(1);
        }
    }*/
}
