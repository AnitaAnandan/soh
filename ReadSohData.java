/*
 * Copyright (c) 2017. No guarantee. Feel free to reuse. Feedback is much appreciated.
 */

package soh;

import util.Utilities;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by anitaa on 3/17/17.
 */
public class ReadSohData {
    public static Set<Student> registeredStudents;
    private static String USERS_TEST = "/Users/anitaa/Documents/happiness/ScienceOfHappiness/data/registeredUsers/test/registeredUsers_2016F_edx_Mar112017.csv";
    private static String USERS_REAL = "/Users/anitaa/Documents/happiness/ScienceOfHappiness/data/registeredUsers/registeredUsers_2016F_edx_Mar112017.csv";
    private static String CHECKIN_DIRECTORY_TEST = "/Users/anitaa/Documents/happiness/ScienceOfHappiness/data/checkin/test/";
    private static String CHECKIN_DIRECTORY_REAL = "/Users/anitaa/Documents/happiness/ScienceOfHappiness/data/checkin/";
    private static String CHECKIN_PREFIX = "checkin_week";
    private static String CHECKIN_POSTFIX = "_qualtrics_Mar112017.csv";
    private static DateFormat dateFormat = new SimpleDateFormat("mm/dd/yy hh:mm"); // 1/4/16 22:35 //TODO: dateFormat.setLenient(false);?
    private static Calendar start = new GregorianCalendar(2016, 8, 1, 0, 0, 0);
    private static Calendar end = new GregorianCalendar(2016, 12, 31, 23, 59, 59);
    private static Calendar defaultDate = new GregorianCalendar(2016, 12, 31, 23, 59, 59);
    private static int MIN_SELECTED_CHECKINS_PER_STUDENT = 1;

    public static void main(String[] args) {
        registerStudents(true);
        checkinStudents(true);
        generateWideformat();
        //generateLongFormat();
    }

    static void registerStudents(boolean forReals) {

        String file;
        if (!forReals) {
            file = USERS_TEST;
        } else {
            file = USERS_REAL;
        }

        BufferedReader br = null;
        String line = "";
        registeredStudents = new HashSet<>(); //TODO: Hashset?

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

    private static void checkinStudents(boolean forReals) {

        String file;
        for (int week = 0; week < Student.NUMBER_OF_WEEKS; week++) {
            if (!forReals) {
                file = CHECKIN_DIRECTORY_TEST;
            } else {
                file = CHECKIN_DIRECTORY_REAL;
            }
            file += CHECKIN_PREFIX + weeknumber(week + 1) + CHECKIN_POSTFIX;
            System.out.println("Week:     " + (week + 1) + "\nFile:     " + file);
            // System.out.println("---------------");

            BufferedReader br = null;
            String line = "";
            String[] checkinRow;
            String delimiter = ",";

            String edxid;

            int anger, anxiety, sadness, joy, friendliness, curiosity;

            Calendar date;
            Date d;
            int year;
            int month;
            int day;
            int hour;
            int min;

            int numberOfCheckinsFromRegisteredStudents = 0;
            int numberOfCheckinsFromUnregisteredStudents = 0;
            int numberOfNoEdxIds = 0;
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

                    if (checkinRow.length < 9) {
                        numberOfNoEdxIds++;
                        continue;
                    }

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

                    // TODO Code Review requested
                    /*
                    EMOTION VARIABLE NAMES
                    71	Joy
                    84	Sad
                    85	Friendly
                    86	Angry
                    88	Fearful
                    90	Interest
                     */
                    anger = anxiety = sadness = joy = friendliness = curiosity = 0;
                    if (checkinRow[1] != null && Utilities.isInteger(checkinRow[1].trim()))
                        joy = Integer.parseInt(checkinRow[1]);
                    if (checkinRow[2] != null && Utilities.isInteger(checkinRow[2].trim()))
                        sadness = Integer.parseInt(checkinRow[2]);
                    if (checkinRow[3] != null && Utilities.isInteger(checkinRow[3].trim()))
                        friendliness = Integer.parseInt(checkinRow[3]);
                    if (checkinRow[4] != null && Utilities.isInteger(checkinRow[4].trim()))
                        anger = Integer.parseInt(checkinRow[4]);
                    if (checkinRow[5] != null && Utilities.isInteger(checkinRow[5].trim()))
                        anxiety = Integer.parseInt(checkinRow[5]);
                    if (checkinRow[6] != null && Utilities.isInteger(checkinRow[6].trim()))
                        curiosity = Integer.parseInt(checkinRow[6]);
                    // System.out.println("Emotions: " + anger + " " + anxiety + " " + sadness + " " + joy + " " + friendliness + " " + curiosity);
                    // checkinRow[7]; TODO: Add field sco

                    edxid = checkinRow[8];
                    //System.out.println("edxid: " + edxid);
                    //System.out.println("----------------");
                    Student s = new Student(edxid);
                    if (registeredStudents.contains(s)) {
                        //System.out.println(edxid + " is registered");
                        numberOfCheckinsFromRegisteredStudents++;

                        Checkin checkin = new Checkin(date);
                        checkin.setAnger(anger);
                        checkin.setAnxiety(anxiety);
                        checkin.setSadness(sadness);
                        checkin.setJoy(joy);
                        checkin.setFriendliness(friendliness);
                        checkin.setCuriosity(curiosity);

                        //TODO Oh the humanity
                        Iterator i = registeredStudents.iterator();
                        while (i.hasNext()) {
                            Student ss = (Student) i.next();
                            if (ss.equals(s)) {
                                ss.doCheckin(week, checkin);
                            }
                        }
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
        Iterator i = registeredStudents.iterator();
        while (i.hasNext())
            ((Student) i.next()).selectCheckinAndCalculateEmoDiversity();
    }

    private static void generateWideformat() {
        String FILE_HEADER = "edxid,week,NE,PE,TE";
        String FILE_NAME = "/Users/anitaa/Documents/Happiness/ScienceOfHappiness/data/emotions.csv";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_NAME);
            fileWriter.append(FILE_HEADER);

            Iterator i = registeredStudents.iterator();
            while (i.hasNext()) {
                Student s = (Student) i.next();
                for (int week = 0; week < Student.NUMBER_OF_WEEKS; week++) {
                    if (s.getNegativeEmodiversity(week) != 0 || s.getPositiveEmodiversity(week) != 0) {
                        fileWriter.append("\n"
                                + s.getEdxid() + ","
                                + (week + 1) + ","
                                + s.getNegativeEmodiversity(week) + ","
                                + s.getPositiveEmodiversity(week) + ","
                                + s.getTotalEmodiversity(week));
                    }
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Throwable e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void generateLongFormat() {
        String FILE_HEADER = "edxid" + ","
                + "w01_anger,w01_anxiety,w01_sadness,w01_joy,w01_friendliness,w01_curiosity" + ","
                + "w02_anger,w02_anxiety,w02_sadness,w02_joy,w02_friendliness,w02_curiosity" + ","
                + "w03_anger,w03_anxiety,w03_sadness,w03_joy,w03_friendliness,w03_curiosity" + ","
                + "w04_anger,w04_anxiety,w04_sadness,w03_joy,w04_friendliness,w04_curiosity" + ","
                + "w05_anger,w05_anxiety,w05_sadness,w03_joy,w05_friendliness,w05_curiosity" + ","
                + "w06_anger,w06_anxiety,w06_sadness,w03_joy,w06_friendliness,w06_curiosity" + ","
                + "w07_anger,w07_anxiety,w07_sadness,w03_joy,w07_friendliness,w07_curiosity" + ","
                + "w08_anger,w08_anxiety,w08_sadness,w03_joy,w08_friendliness,w08_curiosity" + ","
                + "w09_anger,w09_anxiety,w09_sadness,w03_joy,w09_friendliness,w09_curiosity" + ","
                + "w10_anger,w10_anxiety,w10_sadness,w10_joy,w10_friendliness,w10_curiosity";

        String FILE_NAME = "/Users/anitaa/Documents/Happiness/ScienceOfHappiness/data/emotions.csv";
        System.out.println("Output file: " + FILE_NAME);

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_NAME);
            fileWriter.append(FILE_HEADER);

            Checkin[] checkinForStudent;
            int emptyCheckins = 0;
            Iterator i = registeredStudents.iterator();
            while (i.hasNext()) {
                Student s = (Student) i.next();
                checkinForStudent = new Checkin[Student.NUMBER_OF_WEEKS];
                for (int week = 0; week < Student.NUMBER_OF_WEEKS; week++) {
                    checkinForStudent[week] = s.getSelectedCheckin(week);
                }
                    /*
                    if (c == null) {
                        c = new Checkin(defaultDate); //TODO Set this to a true defualt
                        c.setAnger(0);
                        c.setAnxiety(0);
                        c.setSadness(0);
                        c.setJoy(0);
                        c.setfriendliness(0);
                        c.setCuriosity(0);
                    }*/
                emptyCheckins = 0;
                for (int week = 0; week < Student.NUMBER_OF_WEEKS; week++) {
                    if (checkinForStudent[week] == null || checkinForStudent[week].getNumEmotionsCheckedin() == 0)
                        emptyCheckins++;
                }
                //System.out.println(s.getEdxid() + " " + emptyCheckins);
                if (emptyCheckins != 10) { //MIN_SELECTED_CHECKINS_PER_STUDENT
                    fileWriter.append("\n" + s.getEdxid() + ",");
                    for (int week = 0; week < Student.NUMBER_OF_WEEKS; week++) {
                        if (checkinForStudent[week] == null) {
                            checkinForStudent[week] = new Checkin(defaultDate); //TODO Set this to a true defualt)
                            checkinForStudent[week].setAnger(0);
                            checkinForStudent[week].setAnxiety(0);
                            checkinForStudent[week].setSadness(0);
                            checkinForStudent[week].setJoy(0);
                            checkinForStudent[week].setFriendliness(0);
                            checkinForStudent[week].setCuriosity(0);
                        }

                        fileWriter.append(checkinForStudent[week].getAnger() + ",");
                        fileWriter.append(checkinForStudent[week].getAnxiety() + ",");
                        fileWriter.append(checkinForStudent[week].getSadness() + ",");
                        fileWriter.append(checkinForStudent[week].getJoy() + ",");
                        fileWriter.append(checkinForStudent[week].getFriendliness() + ",");
                        fileWriter.append(checkinForStudent[week].getCuriosity() + ",");
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Throwable e2) {
                e2.printStackTrace();
            }
        }
    }

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
