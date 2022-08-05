package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Nedim Hodzic
 *
 * Class that does a jUnit test for the Reseller class.
 */
public class ResellerTest {

    //Creates a town object and a Reseller object.
    Town town = new Town(4, 4);
    Reseller reseller = new Reseller(town, 1, 0);

    //Tests if the Reseller resident changes to the proper resident.
    @Test
    public void testNext() {
        town.randomInit(10);
        assertEquals(reseller.next(town).who(), State.EMPTY);
    }
}