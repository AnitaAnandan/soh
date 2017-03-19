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
        s.doCheckin(c, 1)

        Checkin c2 = new Checkin(new Date(2017, 3, 2))
        c2.setAnger(1)
        c2.setJoy(5)
        s.doCheckin(c2, 2)

    }

    void testSelectCheckin() {

    }

    void testEquals() {

    }

    void testCompareTo() {

    }
}
