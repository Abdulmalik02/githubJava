import java.util.*;
import java.io.*;
/**
 *   Gaussian Elimination Algorithm with Partial Pivoting and Elimination
 *   Separated from Solving. Designed to take input from a user and display
 *   resulting matrix.
 *    
 *   Author: Renato John Recio
 *   Date: 12/18/2013
 *    
 */

public class GaussianElimination
{
    //Global Matrix Variables
    static double matrix[][];
    static double b[];
    static double solution[];
    //Main driver
    public static void main(String[] args)
    {
        int rows, cols;
        int row_cols[] = new int[2];
        initialize_matrix(row_cols);
        forward_elimination(row_cols[0], row_cols[1]);
        back_substitution(row_cols[0], row_cols[1]);
        System.out.println("\nThe final matrix is:\n");
        print_matrix(row_cols[0], row_cols[1]);
    }

    
    //Swap the pivot row with the base row (k)
    public static void swap_pivot_row(int k, int pivot_row){
        double[] swap;
        double multiplier;
        double swap_b;
        swap = matrix[k];
        matrix[k] = matrix[pivot_row];
        matrix[pivot_row] = swap;
        swap_b = b[k];
        b[k] = b[pivot_row];
        b[pivot_row] = swap_b;
    }
   
    //Find the max pivot row of the matrix
    public static int max_pivot_row(int k, int rows){
        double max_value = matrix[k][k];
        int max_row = k;
        for (int r = k; r < rows; r++){
            if (Math.abs(matrix[r][k]) > max_value){
            max_value = matrix[r][k];
            max_row = r;
            }
        }
        return max_row;
    }
    
    //Solve Left-hand Side using Partial Pivoting
    public static void forward_elimination(int rows, int cols){
        int pivot_row;
        double multiplier;
        for (int k = 0; k < rows; k++){
            pivot_row = max_pivot_row(k, rows);
            swap_pivot_row(k, pivot_row);
            for (int i = k+1; i < rows; i++){
                multiplier = matrix[i][k] / matrix[k][k];
                b[i] -= (multiplier * b[k]);
                for (int j = k; j < rows; j++){
                    matrix[i][j] -= (multiplier * matrix[k][j]);
                }
            }
        }
    }
    
    //Perform back substitution to solve the system
    public static void back_substitution(int rows, int cols){
        double total;
        solution = new double[rows];
        for (int i = rows - 1; i >= 0; i--){
            total = 0;
            for (int j = i + 1; j < rows; j++){
                total+= matrix[i][j] * solution[j];
            }
                solution[i] = (b[i] - total) / matrix[i][i];
            }
        }
    
    //Initialize matrix by the user
    public static void initialize_matrix(int row_cols[]){
        Scanner in = new Scanner(System.in);
        Scanner line_in;
        String line;
        int rows, cols;
        System.out.print("Now you will enter the data for the Matrix.");
        System.out.print("\nBe sure to Augment A and b into one matrix when entering data.");
        System.out.print("\nPlease enter the number of rows: ");
        rows = in.nextInt();
        System.out.print("Please enter the number of columns: ");
        cols = in.nextInt();

        if (rows <= 0 || cols <= 0){
            System.out.println("This matrix is invalid, program will now exit.");
            System.exit(0);
        }

        row_cols[0] = rows;
        row_cols[1] = cols;
        matrix = new double[rows][cols];
        b = new double[rows];
        System.out.println("We now have a " + rows + "x" + cols +" augmented matrix.\n");

        for (int r = 0; r < rows; r++){
            if (r != 0)   System.out.print("\nNow enter the data for row #" + (r + 1) + ": ");
            else {System.out.print("Note: When entering data for the matrix, Use spaces as"
                + " delimiters (ex. 2.53 5.33 9.25) \nInclude both left and right"
                + " hand sides of the matrix.\n"
                + "\nNow enter the data for row #" + (r + 1) + ": ");
                }
            if (r == 0) line = in.nextLine();
            line = in.nextLine();
            line_in = new Scanner(line);
            for (int c = 0; c < cols; c++){
                if (c == (cols-1) && line_in.hasNext())
                b[r] = Double.parseDouble(line_in.next());
                else if (line_in.hasNext())
                matrix[r][c] = Double.parseDouble(line_in.next());
                else {
                System.out.println("This matrix is invalid, program will now exit.");
                System.exit(0);
            }
            }
        }      
        
        System.out.println("\nHere is the initial matrix:");
        print_matrix(rows, cols);
        System.out.print("\n\nIf this is correct, press 1. Otherwise, press any key to try again: ");
        String repeat = in.next();
        if (Integer.parseInt(repeat) != 1) initialize_matrix(row_cols);
    }

    //Print the matrix
    public static void print_matrix(int rows, int cols){
        double round;
        for (int r = 0; r < rows; r++){
            System.out.println();
            for (int c = 0 ; c < cols; c++){
                if (c != (cols - 1)){
                round = Math.round(matrix[r][c]*100)/100;
                System.out.print(round + " ");
                }
                else{
                    round = Math.round(b[r]*100)/100;
                    System.out.print(round);
                }
            }
        }
    }
}