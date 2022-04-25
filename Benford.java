import java.io.*;
import java.util.Scanner;

public class Benford {
	private static int[] digitCounters = new int [9];
	private static double[] digitPercentages = new double [9];
	private static int lineCounters = 0;
	
	/*ATTENTION: The execution of the following methods are sequential. Any reversal to the order in the main method
	 * might cause the program to not work properly. */
	
	public static char sigFig (String input){
		/*FUNCTION: This method checks every character that makes up a line of String until a nonzero digit
		 * is encountered. Once such nonzero digit character is discovered, it is returned to the calling method*/
		int index = 0;
		for (int i = 0; i < input.length(); i++){
			if ((input.charAt(i) == '-') || (input.charAt(i) == '0') || (input.charAt(i) == '.')){
				continue;
			}
			else{
				index = i;
				break;
			}
		}
		return input.charAt(index);
	}
	
	public static void putArray (char input){
		/* FUNCTION: This method puts the first nonzero digit processed by the sigFig method to an element of the 
		 * array digitCounters*/
		switch (input){
		case '1':
			++digitCounters[0];
			break;
		case '2':
			++digitCounters[1];
			break;
		case'3':
			++digitCounters[2];
			break;
		case'4':
			++digitCounters[3];
			break;
		case'5':
			++digitCounters[4];
			break;
		case'6':
			++digitCounters[5];
			break;
		case'7':
			++digitCounters[6];
			break;
		case'8':
			++digitCounters[7];
			break;
		case'9':
			++digitCounters[8];
			break;
		}
	}
	
	public static void calcPercentage (){
		/*FUNCTION: This function calculates the percentage of occurrence of every nonzero digits AFTER the entire
		 * input file is read and processed by Sigfig and putArray methods*/
		for (int i = 0; i < digitCounters.length; i++){
			digitPercentages[i] = digitCounters[i]/(double)lineCounters * 100;
		}
	}
	
	public static String printStars (int index){
		/*FUNCTION: This function prints the number of stars */
		String stars = "";
		long numStars = Math.round(digitPercentages[index]);
		for (int i = 0; i < numStars; i++){
			stars += "*";
		}
		return stars;
	}
	
	public static void makeGraph (){
		/*FUNCTION: This function prints out the final graph of the occurrences of nonzero digits, 
		 * including their percentages and graph representations.*/
		System.out.printf("%-3s %-10s %-30s \n", "#", "Percentage", "Graph");
		for (int i = 0; i < digitCounters.length; i++){
			System.out.printf("%-3d %-10.3f %-30s \n", i+1, digitPercentages[i], printStars(i));
		}
	}
	
	public static void main (String[] args){
		String input;
		try {
			Scanner scan = new Scanner (new File("//Users//ichwan//Documents//repo//csc221//Benford//src//data.txt"));
			while (scan.hasNextLine()){
				lineCounters++;
				input = scan.nextLine();
				putArray (sigFig(input));
			}
			calcPercentage();
			makeGraph();
			scan.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}