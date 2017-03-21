package soh;

import util.Utilities;

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
        registerStudents(USERS_TEST); // 10 users
        checkinStudents();
        //selectCheckin();
    }

    private static void registerStudents(String file) {
        BufferedReader br = null;
        String line = "";
        registeredStudents = new TreeSet<>();

        try {
            br = new BufferedReader(new FileReader(file));
            System.out.println("Registering students from: " + file + ".");

            // TODO: Sucky way to address header
            br.readLine();
        } catch (Throwable e) {
            System.out.println(e);
        }

        try {
            while ((line = br.readLine()) != null) {
                String edxid = line;
                Student s = new Student(edxid);
                //System.out.println(edxid);
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
        System.out.println("Registered " + registeredStudents.size() + " students.");
        System.out.println("---------------");
    }

    private static void checkinStudents() {
        for (int week = 1; week <= Student.NUMBER_OF_WEEKS; week++) {
            CheckinForWeek(week);
        }
    }

    private static void CheckinForWeek(int week) {
        String file = CHECKIN_DIRECTORY_TEST + CHECKIN_PREFIX + weeknumber(week) + CHECKIN_POSTFIX;
        System.out.println("Week:      " + week + "\nFile:     " + file);
        // System.out.println("---------------");

        BufferedReader br = null;
        String line = "";
        String[] checkinRow;
        String delimiter = ",";

        String edxid;

        int anger = 0;
        int anxiety = 0;
        int sadness = 0;
        int joy = 0;
        int happiness = 0;
        int curiosity = 0;

        Calendar date;
        Date d;
        int year;
        int month;
        int day;
        int hour;
        int min;

        int numberOfCheckinsFromRegisteredStudents = 0;
        int numberOfCheckinsFromUnregisteredStudents = 0;
        int row = 0;

        // TODO: Sucky way to address header
        try {
            br = new BufferedReader(new FileReader(file));
            checkinRow = br.readLine().split(delimiter);
            // for (int i = 0; i < checkinRow.length; i++) System.out.print(checkinRow[i] + "|");
            //System.out.println();
            //System.out.println("----------------");
        } catch (Throwable e) {
            System.out.println(e);
        }

        try {
            for (row = 1; (line = br.readLine()) != null; row++) {
                checkinRow = line.split(delimiter);
                //System.out.println("Row: " + row);
                //for (int k = 0; k < checkinRow.length; k++) System.out.print(checkinRow[k] + "|");
                //System.out.println();
                //System.out.println("----------------");

                // TODO: sucks
                //System.out.println(checkinRow[0]);
                d = dateFormat.parse(checkinRow[0]);
                year = d.getYear();
                month = d.getMonth();
                day = d.getDay();
                hour = d.getHours();
                min = d.getMinutes();
                date = new GregorianCalendar(year, month, day, hour, min);
                //System.out.println(checkinRow[0]);
                //System.out.println(year + " " + " " + month + " " + day + " " + hour + " " + min);

                anger = anxiety = sadness = joy = happiness = curiosity = 0;
                if (checkinRow[1] != null && Utilities.isInteger(checkinRow[1].trim()))
                    anger = Integer.parseInt(checkinRow[1]);
                if (checkinRow[2] != null && Utilities.isInteger(checkinRow[2].trim()))
                    anxiety = Integer.parseInt(checkinRow[2]);
                if (checkinRow[3] != null && Utilities.isInteger(checkinRow[3].trim()))
                    sadness = Integer.parseInt(checkinRow[3]);
                if (checkinRow[4] != null && Utilities.isInteger(checkinRow[4].trim()))
                    joy = Integer.parseInt(checkinRow[4]);
                if (checkinRow[5] != null && Utilities.isInteger(checkinRow[5].trim()))
                    happiness = Integer.parseInt(checkinRow[5]);
                if (checkinRow[6] != null && Utilities.isInteger(checkinRow[6].trim()))
                    curiosity = Integer.parseInt(checkinRow[6]);
                // System.out.println("Emotions: " + anger + " " + anxiety + " " + sadness + " " + joy + " " + happiness + " " + curiosity);
                // checkinRow[7]; TODO: sc0

                edxid = checkinRow[8];
                //System.out.println("edxid: " + edxid);
                //System.out.println("----------------");
                Student s = new Student(edxid);
                if (registeredStudents.contains(s)) {
                    //System.out.println(edxid + " is registered");
                    numberOfCheckinsFromRegisteredStudents++;
                } else {
                    //System.out.println(edxid + " is NOT registered");
                    numberOfCheckinsFromUnregisteredStudents++;
                }
                //System.out.println("----------------");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    System.out.println("Checkins: " + (row - 1));
                    System.out.println("   Registered Students:   " + numberOfCheckinsFromRegisteredStudents);
                    System.out.println("   Unregistered Students: " + numberOfCheckinsFromUnregisteredStudents);
                    System.out.println("----------------");
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

    //TODO: Sucks
    private static String weeknumber(int i) {
        switch (i) {
            case 1:
                return "01";
            case 2:
                return "02";
            case 3:
                return "03";
            case 4:
                return "04";
            case 5:
                return "05";
            case 6:
                return "06";
            case 7:
                return "07";
            case 8:
                return "08";
            case 9:
                return "09";
            case 10:
                return "10";
        }
        return "huh?";
    }
}
