package edu.iastate.cs228.hw4;

/**
 * @Author Nedim Hodzic
 */

/**
 * This class takes in a file name from the user and then uses it to perform various tasks. First
 * it will seperate out the file into endocing lines and a code line, then it will make a tree with
 * the encoding line, and finnaly it will use that plus the code line to decode the message.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MsgDecoder {
    /**
     * Static variable location used to keep track of what bit the decoder is on.
     */
    public static int location = 0;

    /**
     * Static variable numChars used to keep track of how many characters there are
     * in the decoded message.
     */
    public static int numChars = 0;

    /**
     * Main method that conducts the testing.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter the name of the file you want to decode: ");
        String fileName = scnr.next();

        ArrayList<String> fileLines = fileReader(fileName);

        String encodingString = "";
        for (int i = 0; i < fileLines.size() - 2; i++) {
            encodingString += fileLines.get(i) + '\n';
        }
        encodingString += fileLines.get(fileLines.size() - 2);
        String codeString = fileLines.get(fileLines.size() - 1);

        MsgTree encodingTree = new MsgTree(encodingString);

        System.out.println("character code\n-------------------------");
        encodingTree.printCodes(encodingTree, "");
        System.out.println("-------------------------\nMESSAGE:");

        while (location < codeString.length()) {
            decode(encodingTree, codeString);
        }

        double numBits = 0;
        for(int i = 0; i < encodingTree.bitsInCode.size(); i++) {
            numBits += encodingTree.bitsInCode.get(i);
        }
        double avg = numBits / (double)encodingTree.bitsInCode.size();
        double savings = (1 - (avg / 16)) * 100;

        System.out.println("\n\nSTATISTICS:");
        System.out.printf("Avg bits/char:           %.1f\n", avg);
        System.out.println("Total Characters:        " + numChars);
        System.out.printf("Space savings:           %.1f%%\n", savings);
    }

    /**
     * Reads lines from the given file and adds them to an array list.
     * @param fileName
     * @return
     */
    public static ArrayList fileReader(String fileName) {
        ArrayList<String> fileLines = new ArrayList<String>();

        try {
            File file = new File(fileName);
            Scanner scnr = new Scanner(file);

            while (scnr.hasNextLine()) {
                fileLines.add(scnr.nextLine());
            }
            scnr.close();
        } catch (Exception e) {
            System.out.println("ERROR: File not found. Enter path name correctly.");
        }

        return fileLines;
    }

    /**
     * Takes in the character tree as well as the line of codes and decodes them based on the tree. It then
     * outputs the decoded message.
     * @param codes
     * @param msg
     */
    public static void decode(MsgTree codes, String msg) {
        //Loops until a leaf node is reached.
        while (codes.left != null && codes.right != null) {
            if (msg.charAt(location) == '0') {
                codes = codes.left;
            } else {
                codes = codes.right;
            }
            location++;
        }
        System.out.print(codes.payloadChar);
        numChars++;
    }
}