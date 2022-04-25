//******************************************************************************************
// Lab 1                         Authors:  Zhipeng Mei,Vince Garcia, Johnson Mei,
// Class: CS 111C                Date:     2/17/16
// The TestFactors class prompts the user for input of an integer,
// assumed to be greater than or equal to zero.
// This class calls showFactors, the driver program, in order to compute the prime factors
// Input of zero ends the program by triggering the boolean flag, setting it to true.
// Negatives are exceptions and will cause errors as there is no input validation.
//*****************************************************************************************
import java.util.Scanner;
public class TestFactors {
// ---------------------------------------------------------------
// Precondition: testNumber must be greater than or equal to zero.
// Postcondition: Program exit when input is zero
//----------------------------------------------------------------
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        int testNumber;
        ShowFactors testingShowFactors = new ShowFactors();

        //boolean initialized to false. while loop for continious user input
        boolean done = false;
        while (!done) {
            //prompt user for input
            System.out.print("Enter a number, and I will display its prime factors in descending order\n(enter 0 to exist program): ");
            testNumber = in.nextInt();

            //value of zero will end the program
            if(testNumber != 0){
                testingShowFactors.showFactors(testNumber);
            } else {
                System.out.println("Goodbye!");
                done = true;
            }
        }
    }
}
