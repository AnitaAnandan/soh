package soh.test

import soh.Checkin

/**
 * Created by anitaa on 3/18/17.
 */
class CheckinTest extends GroovyTestCase {
    void setUp() {
        super.setUp()


    }

    void tearDown() {

    }

    void testGetNumEmotionsCheckedin() {
        Checkin c = new Checkin(new Date(2017, 3, 1))
        c.setAnger(1)
        c.setJoy(5)
        System.out.println(c.getNumEmotionsCheckedin())//2
        c.setAnger(3)
        System.out.println(c.getNumEmotionsCheckedin())//2
    }

    boolean testCompareTo() {
        Checkin c1 = new Checkin(new Date(2017, 3, 1))
        Checkin c2 = new Checkin(new Date(2017, 3, 10))
        if(c1.compareTo(c2) == -1) return true else return false
    }
}
