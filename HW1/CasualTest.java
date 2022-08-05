package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Nedim Hodzic
 *
 * Class that does a jUnit test for the Casual class.
 */
public class CasualTest{

    //Creates a town object and a Casual object.
    Town town = new Town(4, 4);
    Casual casual = new Casual(town, 2, 1);

    //Tests if the Casual resident changes to the proper resident.
    @Test
    public void testNext() {
        town.randomInit(10);
        assertEquals(casual.next(town).who(), State.OUTAGE);
    }
}