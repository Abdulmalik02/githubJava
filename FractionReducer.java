/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraction.reducer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FractionReducer {

    // Method to print out an array
    public static void print(ArrayList<Integer> array) {

        // Runs loop to print out items in the array
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + " ");

        }
        System.out.println();
    }

    public static int euclideanOutput(ArrayList<Integer> array) {

        int gcd = 0;

        gcd = euclideanProcess(array.get(0), array.get(1));

        for (int i = 2; i < array.size(); i++) {

            gcd = euclideanProcess(gcd, array.get(i));
        }

        return gcd;
    }

    public static int euclideanProcess(int largeNumber, int smallNumber) {

        if (smallNumber == 0) {
            return largeNumber;
        } else {
            int smallNumber2 = largeNumber % smallNumber;

            int largeNumber2 = smallNumber;

            return euclideanProcess(largeNumber2, smallNumber2);
        }
    }

    public static ArrayList<Integer> reduction(ArrayList<Integer> array, ArrayList<Integer> finalArray, int gcd) {

        for (int i = 0; i < array.size(); i++) {

            int originalNumber = array.get(i);
            int newNumber = originalNumber / gcd;
            finalArray.add(newNumber);

        }

        return finalArray;
    }
    public static void main(String[] args) {
try{
        Scanner kb = new Scanner(System.in);

        System.out.println("Welcome to Joshua's Fraction Reducer!!");
        System.out.println("Here you can reduce any number of numbers by a common factor");

        ArrayList<Integer> array = new ArrayList<Integer>();
        ArrayList<Integer> finalArray = new ArrayList<Integer>();

        System.out.println("\nPlease enter the numbers");
        System.out.println("Your last number should be 0");
        
        while (true) {
            int temp = kb.nextInt();
            if (temp != 0) {
                array.add(temp);
            } else {
                break;
            }
        }
        
        System.out.println("\nThe greatest common factor is: " + euclideanOutput(array));
        int gcd = euclideanOutput(array);
        reduction(array, finalArray, gcd);
        System.out.println("");
        print(finalArray);
        
    }
        catch(InputMismatchException e){
            System.out.println("Please enter a number");
        }
}}

