package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Nedim Hodzic
 *
 * Class that does a jUnit test for the Outage class.
 */
public class OutageTest {

    //Creates a town object and an Outage object.
    Town town = new Town(4, 4);
    Outage outage = new Outage(town, 0, 0);

    //Tests if the Outage resident changes to the proper resident.
    @Test
    public void testNext() {
        assertEquals(outage.who(), State.EMPTY);
    }
}