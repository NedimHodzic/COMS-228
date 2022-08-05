package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Nedim Hodzic
 *
 * Class that does a jUnit test for the Streamer class.
 */
public class StreamerTest {

    //Creates a town object and a Streamer object.
    Town town = new Town(4, 4);
    Streamer streamer = new Streamer(town, 3, 2);

    //Tests if the Streamer resident changes to the proper resident.
    @Test
    public void testNext() {
        town.randomInit(10);
        assertEquals(streamer.next(town).who(), State.OUTAGE);
    }
}