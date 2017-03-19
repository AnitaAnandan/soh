package soh.test

import soh.Checkin
import soh.Student

/**
 * Created by anitaa on 3/18/17.
 */
class StudentTest extends GroovyTestCase {
    void testDoCheckin() {
        Student s = new Student("abc")
        Checkin c1 = new Checkin(new Date(2017, 3, 1))
        c1.setAnger(1)
        c1.setJoy(5)
        s.doCheckin(c1, 1)

        Checkin c2 = new Checkin(new Date(2017, 3, 2))
        c2.setAnger(1)
        c2.setJoy(5)
        s.doCheckin(c2, 2)
    }

    void testSelectCheckin2_1() {
        Student s = new Student("abc")
        Checkin c1 = new Checkin(new Date(2017, 4, 1))
        c1.setAnger(1)
        c1.setJoy(5)
        c1.setAnxiety(6)
        s.doCheckin(c1, 1)

        Checkin c2 = new Checkin(new Date(2017, 3, 2))
        c2.setAnger(1)
        c2.setJoy(3)
        s.doCheckin(c2, 1)

        System.out.print(s.selectCheckin(1)) //May
    }

    void testSelectCheckin2_2() {
        Student s = new Student("abc")
        Checkin c1 = new Checkin(new Date(2017, 4, 1)) // May
        c1.setAnger(1)
        c1.setJoy(5)
        c1.setAnxiety(6)
        s.doCheckin(c1, 1)

        Checkin c2 = new Checkin(new Date(2017, 3, 2)) // Apr
        c2.setAnger(1)
        c2.setJoy(3)
        c2.setHappiness(3)
        s.doCheckin(c2, 1)

        System.out.print(s.selectCheckin(1)) // Apr 2nd
    }

    void testSelectCheckin2_3() {
        Student s = new Student("abc")
        Checkin c2 = new Checkin(new Date(2017, 3, 2)) // Apr
        c2.setAnger(1)
        c2.setJoy(3)
        c2.setHappiness(3)
        s.doCheckin(c2, 1)

        Checkin c1 = new Checkin(new Date(2017, 4, 1)) // May
        c1.setAnger(1)
        c1.setJoy(5)
        c1.setAnxiety(6)
        s.doCheckin(c1, 1)

        System.out.print(s.selectCheckin(1)) // Apr
    }

    void testSelectCheckin3() {
        Student s = new Student("abc")
        Checkin c3 = new Checkin(new Date(2017, 3, 2)) // Apr
        c3.setAnger(1)
        s.doCheckin(c3, 1)

        Checkin c2 = new Checkin(new Date(2017, 3, 2)) // Apr
        c2.setAnger(1)
        c2.setJoy(3)
        c2.setHappiness(3)
        s.doCheckin(c2, 1)

        Checkin c1 = new Checkin(new Date(2017, 4, 1)) // May
        c1.setAnger(1)
        c1.setJoy(5)
        c1.setAnxiety(6)
        s.doCheckin(c1, 1)

        System.out.print(s.selectCheckin(1)) // Apr 2nd w/ 3 emotions
    }

    void testSelectCheckin1() {
        Student s = new Student("abc")
        Checkin c1 = new Checkin(new Date(2017, 4, 1))
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
