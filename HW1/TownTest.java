package edu.iastate.cs228.hw1;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.FileNotFoundException;

/**
 * @author Nedim Hodzic
 *
 * Class that does a jUnit test for the Town class.
 */
public class TownTest {

    //Creates a town object and a string with the grid in the given file.
    String s = "O R O R \nE E C O \nE S O S \nE O R R \n";
    Town town = new Town(4, 4);

    //Tests the toString() method works properly.
    @Test
    public void testToString() {
        town.randomInit(10);
        assertEquals(town.toString(), s);
    }

}