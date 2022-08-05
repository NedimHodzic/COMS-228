package edu.iastate.cs228.hw4;

/**
 * @Author Nedim Hodzic
 */

import java.util.ArrayList;

/**
 * This is the class that creats the MsgTree. It has a constructor for building the tree
 * from a string and one for when there is a single node with no children. There is also
 * a method that will print out the codes from the tree.
 */
public class MsgTree {
    public char payloadChar;
    public MsgTree left;
    public MsgTree right;

    /**
     * Public static ArrayList that keeps track of how many bits are in each character.
     * This is public so that MsgDecoder can have access to it when computing the average.
     */
    public static ArrayList<Integer> bitsInCode = new ArrayList<>();

    private static int staticCharIdx = 0;

    /**
     * This is the constructor that builds a edu.iastate.cs228.hw4.MsgTree with a given encoding string.
     *
     * @param encodingString
     */
    public MsgTree(String encodingString) {
        payloadChar = encodingString.charAt(staticCharIdx);

        //Creates left parts of the tree
        staticCharIdx += 1;
        left = new MsgTree(encodingString.charAt(staticCharIdx));
        if (left.payloadChar == '^') {
            left = new MsgTree(encodingString);
        }

        //Creates right parts of the tree
        staticCharIdx += 1;
        right = new MsgTree(encodingString.charAt(staticCharIdx));
        if (right.payloadChar == '^') {
            right = new MsgTree(encodingString);
        }
    }

    /**
     * This is the constructor for a single node with no children.
     *
     * @param payloadChar
     */
    public MsgTree(char payloadChar) {
        this.payloadChar = payloadChar;
    }

    /**
     * Uses a given encoding Tree and a string that stores the code. This works recursivly until the
     * has been searched. It prints out a character and its corresponding code.
     *
     * @param root
     * @param code
     */
    public static void printCodes(MsgTree root, String code) {
        if (root == null) {
            return;
        }

        //Will print the node if it is a character and not an inner node.
        if (root.payloadChar != '^') {
            if (root.payloadChar == '\n') {
                System.out.println("\\n" + "         " + code);
                bitsInCode.add(code.length());
            } else {
                System.out.println(root.payloadChar + "          " + code);
                bitsInCode.add(code.length());
            }
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }
}