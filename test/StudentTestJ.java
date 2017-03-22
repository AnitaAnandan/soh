package soh.test;

import soh.Checkin;
import soh.Student;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by anitaa on 3/22/17.
 */
public class StudentTestJ {

    private static Calendar aug = new GregorianCalendar(2016, 8, 1, 0, 0, 0);
    private static Calendar sep = new GregorianCalendar(2016, 9, 1, 0, 0, 0);
    private static Calendar dec = new GregorianCalendar(2016, 12, 31, 23, 59, 59);

    public static void main(String[] args) throws Throwable {
        test1();

    }

    private static void test1() throws Throwable {
        Student s = new Student("abc");
        Checkin c1 = new Checkin(dec);
        c1.setAnger(1);
        c1.setJoy(5);
        c1.setAnxiety(6);
        s.doCheckin(c1, 1);

        Checkin c2 = new Checkin(aug);
        c2.setAnger(1);
        c2.setJoy(3);
        s.doCheckin(c2, 1);

        System.out.println(s.selectCheckin(1)); //dec
    }
}
