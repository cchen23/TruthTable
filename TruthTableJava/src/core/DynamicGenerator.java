/*****************************************************************************/
/* DynamicGenerator.java                                                     */
/*																			 */
/* Purpose: Given a text file of boolean equations, prints a program that    */
/* 		    will produce a csv format truth table based on the boolean       */
/*          equations.                                                       */
/*                                                                           */
/* Compile: javac DynamicGenerator.java                                      */
/* Run: java DynamicGenerator                                                */
/* Author: Cathy Chen                                                        */
/*****************************************************************************/


package core;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DynamicGenerator {
	public static void main(String[] args) throws IOException {
		int numOutputs;
		int numInputs;
		String equation;
		
		System.out.println("public class TruthTable {");
		// Read equations from input file
		//BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		BufferedReader reader = new BufferedReader(new FileReader("src/test.txt"));
		// Get number of inputs and outputs
		numOutputs = Integer.parseInt(reader.readLine());
		numInputs = Integer.parseInt(reader.readLine());
			
			// For each line, print method that calculates output
			for (int i = 0; i < numOutputs; i++) {
				equation = reader.readLine();
				printEquation(equation, i, numInputs);
			}
		
		// Print loop that generates truth table and calls methods
			indent(1);
			System.out.println("public static void main(String[] args) {");
			indent(2);;
			printLoopHeader(numInputs);
			
			for (int i = 0; i < numOutputs; i++) {
				indent(3);
				printOutput(numInputs, i);
				if (i != numOutputs - 1) {
					System.out.print("System.out.print\",\"");
				}
				else {
					indent(3);
					System.out.println("System.out.println();");
				}
			}
			
			for (int i = 0; i < numInputs; i++) {
				indent(numInputs - i + 1);
				System.out.println("}");
			}
			
			indent(1);
			System.out.println("}");
			
			reader.close();
			System.out.println("}");
	}
	
	// Prints private methods that will generate values of truth table outputs
	private static void printEquation(String equation, int eqNum, int numInputs) {
		String[] eq = equation.split(" ");
		int length = eq.length;
		indent(1);
		System.out.print("private int output" + eqNum + "(");
		for (int i = 0; i < numInputs; i++) {
			System.out.print("in" + i);
			if (i != numInputs - 1) {
				System.out.print(", ");
			}
			else {
				System.out.println(") {");
			}
		}
		
		indent(2);
		System.out.print("int output = ");
		for (int j = 2; j < length; j++) {
			if (j % 2 == 0) {
				int strLen = eq[j].length();
				if (eq[j].charAt(strLen - 1) == '\'') {
					System.out.print("!" + eq[j].substring(0, strLen));
				}
				else System.out.print(eq[j]);
			}
			else {
				System.out.print(eq[j]);
			}
		}
		System.out.println(";");
		
		indent(2);
		System.out.println("if (output == 0) return 0;");
		indent(2);
		System.out.println("else return 1;");
		indent(1);
		System.out.println("}");
	}
	
	// Prints the loop that will run through all possible input combinations
	private static void printLoopHeader(int numInputs) {
		for (int i = 0; i < numInputs; i++) {
			System.out.println("for (int in" + i + " = 0; in" + i + " < 2; in"
					+ i + "++) {");
		}
	}
	
	// Prints print statement that will print outputs in csv format
	private static void printOutput(int numInputs, int outputNum) {
		System.out.print("System.out.print(out" + outputNum + "(");
		for (int j = 0; j < numInputs - 1; j++) {
			System.out.print("in" + j + ",");
		}
		System.out.print("in" + numInputs + ")");
		System.out.println(");");
	}
	
	// Print an indent
	private static void indent(int level) {
		for (int i = 0; i < level; i++) {
			System.out.print("    ");
		}
	}
}
