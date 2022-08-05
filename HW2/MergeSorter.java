package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

/**
 * @author Nedim Hodzic
 */

/**
 *
 * This class implements the mergesort algorithm.
 *
 */
public class MergeSorter extends AbstractSorter {
    // Other private instance variables if needed

    /**
     * Constructor takes an array of points.  It invokes the superclass constructor, and also
     * set the instance variables algorithm in the superclass.
     *
     * @param pts   input array of integers
     */
    public MergeSorter(Point[] pts) {
        super(pts);
        algorithm = "mergesort";
    }

    /**
     * Perform mergesort on the array points[] of the parent class AbstractSorter.
     *
     */
    @Override
    public void sort() {
        mergeSortRec(points);
    }

    /**
     * This is a recursive method that carries out mergesort on an array pts[] of points. One
     * way is to make copies of the two halves of pts[], recursively call mergeSort on them,
     * and merge the two sorted subarrays into pts[].
     *
     * @param pts    point array
     */
    private void mergeSortRec(Point[] pts) {
        if (pts.length <= 1) {
            return;
        }
        int mid = pts.length / 2;
        Point[] ptsLeft = new Point[mid];
        Point[] ptsRight = new Point[pts.length - mid];
        int i = 0;
        for (int j = 0; j < ptsLeft.length; j++) {
            ptsLeft[j] = pts[i];
            i++;
        }
        for (int j = 0; j < ptsRight.length; j++) {
            ptsRight[j] = pts[i];
            i++;
        }

        mergeSortRec(ptsLeft);
        mergeSortRec(ptsRight);

        merge(pts, ptsLeft, ptsRight);
    }

    // Other private methods if needed ...

    /**
     * Merges 2 given arrays into 1.
     *
     * @param pts Array to be filled and sorted with the 2 given arrays.
     * @param ptsLeft Left part of the original array.
     * @param ptsRight Right part of the original array.
     */
    private void merge(Point[] pts, Point[] ptsLeft, Point[] ptsRight) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < ptsLeft.length && j < ptsRight.length) {
            if (ptsLeft[i].compareTo(ptsRight[j]) < 0) {
                pts[k] = ptsLeft[i];
                i++;
                k++;
            } else {
                pts[k] = ptsRight[j];
                j++;
                k++;
            }
        }
        while (j < ptsRight.length) {
            pts[k] = ptsRight[j];
            j++;
            k++;
        }
        while (i < ptsLeft.length) {
            pts[k] = ptsLeft[i];
            i++;
            k++;
        }
    }
}
