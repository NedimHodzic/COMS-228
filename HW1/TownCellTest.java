package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;

/**
 *  @author Nedim Hodzic
 *
 */
public class TownCellTest {

    //Creates a town object and a census to compare to.
    Town town = new Town(4, 4);
    int[] nCensus = {0, 2, 1, 2, 0};
    Casual casual = new Casual(town, 0, 1);

    //Tests if census() works properly.
    @Test
    public void testCensus() {
        int[] nCensus2 = new int[5];
        town.randomInit(10);
        casual.census(nCensus2);
        assertEquals(Arrays.toString(nCensus2), Arrays.toString(this.nCensus));
    }

}