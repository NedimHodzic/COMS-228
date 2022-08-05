package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Nedim Hodzic
 *
 * Class that does a jUnit test for the ISPBusiness class.
 */
public class ISPBusinessTest {

    //Creates two town objects.
    Town town = new Town(4, 4);

    //Tests if updatePlain() works properly.
    @Test
    public void testUpdatePlain() {
        town.randomInit(10);
        Town town2 = new Town(town.getLength(), town.getWidth());
        for(int i = 0; i < town.getWidth(); i++) {
            for(int j = 0; j < town.getLength(); j++) {
                town2.grid[i][j] = town.grid[i][j].next(town2);
            }
        }
        assertEquals(ISPBusiness.updatePlain(town).toString(), town2.toString());
    }
}