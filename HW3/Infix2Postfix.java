
/**
 * @author Nedim Hodzic
 */

/**
 * This class takes input infix lines from an input file and then converts them to the proper postfix version of it
 * and outputs it to a separate output file. If the expression contains an error than the output will instead be what
 * kind of error it is and what caused the error. This class has the main method which does all the converting and
 * outputing and another prec method which determines the precedence of an operator.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Infix2Postfix {
    /**
     * Take infix lines from a file and convert them to the proper postfix version and output it to an output file.
     * If the expression contains errors then print out the error and what caused the error.
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("input.txt");
        Scanner scnr = null;
        Scanner scnr2 = null;

        try {
            scnr = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        String expr = "";
        String outputExpr = "";

        //While statement for the overall file, this keeps going as long as there are more lines in the file.
        while (scnr.hasNextLine()) {
            String postfixExpr = "";
            String subExpr = "";
            Stack<String> stack = new Stack<>();

            expr = scnr.nextLine() + " ";
            scnr2 = new Scanner(expr);

            int numParenthesis = 0;
            int rank = 0;
            int numInSub = 0;

            //While statement for a line in the file.
            while (scnr2.hasNext()) {
                //Detects if the string has anymore ints and then adds that int to the postfix expression.
                if (scnr2.hasNextInt()) {
                    int op = scnr2.nextInt();
                    postfixExpr += op + " ";
                    subExpr += op + " ";
                    rank += 1;
                    numInSub += 1;

                    //Checks if rank ever goes past one meaning there are too many operands.
                    if (rank > 1) {
                        postfixExpr = "Error: too many operands (" + op + ")";
                        stack.clear();
                        break;
                    }
                }
                //This goes through only if the next part of the string is an operator.
                else {
                    String op = scnr2.next();
                    //If statement for when the next operator is an opening parenthesis.
                    if (op.equals("(")) {
                        numParenthesis += 1;
                        rank = 0;
                        numInSub = 0;
                        subExpr = "";
                        stack.push(op);
                    }
                    //If statement for when the next operator is a closing parenthesis.
                    else if (op.equals(")")) {
                        numParenthesis -= 1;
                        //Checks if the number of parentheses goes below zero meaning there's a missing opening parenthesis.
                        if (numParenthesis < 0) {
                            postfixExpr = "Error: no opening parenthesis detected";
                            stack.clear();
                            break;
                        }
                        //While loop that pops the operators in the subexpression.
                        while (!stack.isEmpty() && !(stack.peek().equals("("))) {
                            postfixExpr += stack.pop() + " ";
                        }
                        stack.pop();
                        //Checks that the number of things in the sub expression is zero meaning that it is empty.
                        if (numInSub == 0) {
                            postfixExpr = "Error: no subexpression detected ()";
                            stack.clear();
                            break;
                        }
                        //Checks if the number of things in the sub expression is one meaning it is malformed.
                        if (numInSub == 1) {
                            subExpr = subExpr.trim();
                            postfixExpr = "Error: too many operands (" + subExpr + ")";
                            stack.clear();
                            break;
                        }
                    }
                    //Else statement that occurs when the operator is not any form of parenthesis.
                    else {
                        //While loop that pops the stack if the current operator's precedenc is less than or equal
                        //to the operator's precedence at the top of the stack
                        while (!stack.isEmpty() && prec(op, true) <= prec(stack.peek(), false)) {
                            postfixExpr += stack.pop() + " ";
                        }

                        stack.push(op);
                        rank -= 1;

                        //If statement that checks if the current rank is not 0 meaning there is an extra operator.
                        //It also checks if there are no more parts to the expression meaning that the last operator
                        //is an extra one.
                        if (rank != 0 || (rank == 0 && !(scnr2.hasNext()))) {
                            postfixExpr = "Error: too many operators (" + op + ")";
                            stack.clear();
                            numParenthesis = 0;
                            break;
                        }
                    }
                }
            }

            //While loop that pops the rest of the operators in the stack.
            while (!stack.empty()) {
                postfixExpr += stack.pop() + " ";
            }
            //Checks if the number of parentheses is greater than zero meaning there's a missing closing parenthesis.
            if (numParenthesis > 0) {
                postfixExpr = "Error: no closing parenthesis detected";
            }

            //Removes and spaces at the end and beginning of the postfix expression then adds it to the output expression.
            postfixExpr = postfixExpr.trim();
            outputExpr += postfixExpr + "\n";
        }

        FileWriter write = null;

        //Writes the output expression to the output file.
        try {
            write = new FileWriter("output.txt");
            write.write(outputExpr);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scnr.close();
        scnr2.close();

    }

    /**
     * Takes in 2 variables, a string operator varaible, and a boolean that checks whether the operator is from the
     * expression or if its from the stack. This class then determines the precendence of the operator depending on
     * if it is from the expression or not. It then returns the level of precedence.
     * @param op
     * @param isInput
     * @return
     */
    public static int prec(String op, boolean isInput) {
        //If statements for when the operator is from the expression.
        if (isInput) {
            if (op.equals("+") || op.equals("-")) {
                return 1;
            } else if (op.equals("*") || op.equals("/") || op.equals("%")) {
                return 2;
            } else if (op.equals("^")) {
                return 4;
            }
        }
        //If statements for when the operator is from the stack.
        else {
            if (op.equals("+") || op.equals("-")) {
                return 1;
            } else if (op.equals("*") || op.equals("/") || op.equals("%")) {
                return 2;
            } else if (op.equals("^")) {
                return 3;
            }
        }
        return -1;
    }
}
