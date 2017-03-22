package soh.test

import soh.Checkin
import soh.Student

/**
 * Created by anitaa on 3/18/17.
 */
class StudentTest extends GroovyTestCase {

    private static Calendar aug = new GregorianCalendar(2016, 8, 1, 0, 0, 0)
    private static Calendar sep = new GregorianCalendar(2016, 9, 1, 0, 0, 0)
    private static Calendar dec = new GregorianCalendar(2016, 12, 31, 23, 59, 59)


    void testDoCheckin() {
        Student s = new Student("abc")
        Checkin c1 = new Checkin(aug)
        c1.setAnger(1)
        c1.setJoy(5)
        s.doCheckin(c1, 1)

        Checkin c2 = new Checkin(dec)
        c2.setAnger(1)
        c2.setJoy(5)
        s.doCheckin(c2, 2)
    }

    void testSelectCheckin2_1() {
        Student s = new Student("abc")
        Checkin c1 = new Checkin(dec)
        c1.setAnger(1)
        c1.setJoy(5)
        c1.setAnxiety(6)
        s.doCheckin(c1, 1)

        Checkin c2 = new Checkin(aug)
        c2.setAnger(1)
        c2.setJoy(3)
        s.doCheckin(c2, 1)

        System.out.print(s.selectCheckin(1)) //dec
        System.out.print(s.selectCheckin(1).getDate())
        System.out.print("new code")
    }

    void testSelectCheckin2_2() {
        Student s = new Student("abc")
        Checkin c1 = new Checkin(dec) // May
        c1.setAnger(1)
        c1.setJoy(5)
        c1.setAnxiety(6)
        s.doCheckin(c1, 1)

        Checkin c2 = new Checkin(aug) // Apr
        c2.setAnger(1)
        c2.setJoy(3)
        c2.setHappiness(3)
        s.doCheckin(c2, 1)

        System.out.print(s.selectCheckin(1)) // Apr 2nd
    }

    void testSelectCheckin2_3() {
        Student s = new Student("abc")
        Checkin c2 = new Checkin(aug) // Apr
        c2.setAnger(1)
        c2.setJoy(3)
        c2.setHappiness(3)
        s.doCheckin(c2, 1)

        Checkin c1 = new Checkin(dec) // May
        c1.setAnger(1)
        c1.setJoy(5)
        c1.setAnxiety(6)
        s.doCheckin(c1, 1)

        System.out.print(s.selectCheckin(1)) // Apr
    }

    void testSelectCheckin3() {
        Student s = new Student("abc")
        Checkin c3 = new Checkin(aug) // Apr
        c3.setAnger(1)
        s.doCheckin(c3, 1)

        Checkin c2 = new Checkin(aug) // Apr
        c2.setAnger(1)
        c2.setJoy(3)
        c2.setHappiness(3)
        s.doCheckin(c2, 1)

        Checkin c1 = new Checkin(dec) // May
        c1.setAnger(1)
        c1.setJoy(5)
        c1.setAnxiety(6)
        s.doCheckin(c1, 1)

        System.out.print(s.selectCheckin(1)) // Apr 2nd w/ 3 emotions
    }

    void testSelectCheckin1() {
        Student s = new Student("abc")
        Checkin c1 = new Checkin(dec)
        c1.setAnger(1)
        c1.setJoy(5)
        c1.setAnxiety(6)
        s.doCheckin(c1, 1)

        System.out.print(s.selectCheckin(1)) //May
    }

    void testSelectCheckin0() {
        Student s = new Student("abc")
        System.out.print(s.selectCheckin(1)) //null
    }

    void testEquals() {

    }

    void testCompareTo() {

    }
}
