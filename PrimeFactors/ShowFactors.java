//************************************************************************************
// Lab 1                         Authors:  Zhipeng Mei, Vince Garcia, Johnson Mei
// Class: CS 111C                Date:     2/17/16
// The ShowFactors class utilizes recursion and an overloaded method to display 
// the prime factors of a positive integer in  descending order.
// The input from TestFactors is passed & analyzed through the methods in this class.
// The recursive call will continue until the base case, number = 1, is reached.
// If a prime factor is not found the method iterates the factor variable by 1.
//************************************************************************************
public class ShowFactors {  
// Precondition:  testNumber must be greater than or equal to zero.
// Postcondition: when number = 1, method returns the prime factors of number in descending order
    public static void showFactors(int number) {  // Overloaded method that takes an arguement from TestFactors
        
        System.out.print("The factors for " + number + ": ");     
        showFactors(number, 2);               // 2 is the initial value of factor
        System.out.println(" \n");
    }
    
    
    private static void showFactors(int number, int factor) {  // Computes and outputs the prime factors of the input   
 
        if (number != 1) {
            if (number % factor == 0) {       // a factor is found when remainder is zero
                number = number / factor;
                showFactors(number, factor);
                System.out.print(factor + " ");

            } // close inner if
            else {             
                showFactors(number, factor + 1); // recursive method iterates factor 
            }     
        }     // end outer if         
    }
  
}
