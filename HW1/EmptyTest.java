package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Nedim Hodzic
 *
 * Class that does a jUnit test for the Empty class.
 */
public class EmptyTest {

    //Creates a town object and an Empty object.
    Town town = new Town(4, 4);
    Empty empty = new Empty(town, 0, 1);

    //Tests if the Empty resident changes to the proper resident.
    @Test
    public void testNext() {
        town.randomInit(10);
        assertEquals(empty.next(town).who(), State.CASUAL);
    }
}