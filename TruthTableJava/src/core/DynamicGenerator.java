package core;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DynamicGenerator {
	public static void main(String[] args) throws IOException {
		int numOutputs;
		int numInputs;
		String equation;
		String[] eq;
		int length;
		
		// Read equations from input file
			BufferedReader reader = new BufferedReader(new FileReader(args[0]));
			
			// Get number of inputs and outputs
			numOutputs = Integer.getInteger(reader.readLine());
			numInputs = Integer.getInteger(reader.readLine());
			
			// For each line, print method that calculates output
			for (int i = 0; i < numOutputs; i++) {
				equation = reader.readLine();
				eq = equation.split(" ");
				length = eq.length;
				System.out.println("private int output" + i + "{");
				System.out.print("int output = ");
				for (int j = 0; j < length; j++) {
					if (j % 2 == 0) {
						int strLen = eq[j].length();
						if (eq[j].charAt(strLen) == '\'') {
							System.out.print("!" + eq[j].substring(0, strLen));
						}
						else System.out.print(eq[j]);
					}
					else {
						System.out.print(eq[j]);
					}
				}
				System.out.println(";");
				
				System.out.println("}");
			}
		
		// Print loop that generates truth table and calls methods
			System.out.println("public static void main(String[] args) {");
			for (int i = 0; i < numInputs; i++) {
				System.out.println("for int " + i + "= 0; " + i + " < 2; "
						+ i + "++) {");
			}
			
			for (int i = 0; i < numOutputs; i++) {
				System.out.print("System.out.print(out" + i + "(");
				for (int j = 0; j < numInputs - 1; j++) {
					System.out.print("in" + j + ",");
				}
				System.out.print("in" + numInputs + ");");
				if (i != numOutputs - 1) {
					System.out.print(",");
				}
				System.out.println();
			}
			
			for (int i = 0; i < numInputs; i++) {
				System.out.println("}");
			}
	}
}
