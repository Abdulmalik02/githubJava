import java.util.Scanner;

public class MultiplyMatrices{

	public static void main(String[] args){
		
		Scanner scan = new Scanner(System.in);
                
                System.out.println("\n ==================================");
                System.out.println("  WELCOME TO THE MATRIX CALCULATOR");
                System.out.println(" ==================================");
                System.out.println("\n<< Let's Multiply Some Matrices >>\n");
		
		//Entering Number Of Rows and Columns In First Matrix
		System.out.print("\nEnter The Number Of Rows in First Matrix   : ");
		
		int r1 = scan.nextInt();
		
		System.out.print("\nEnter The Number Of Columns in First Matrix: ");
		
		int c1 = scan.nextInt();
		
		int[][] firstmatrix = new int[r1][c1];
		
		//Entering Data To The First Matrix
		enterFirstMatrixData(scan, firstmatrix, r1, c1);
		
		//Reminding Matrix Multiplication Rule
		System.out.println("\nRequired: When Multiplying Two Matrices, \n\n\t  Number Of COLUMNS in First Matrix MUST Equal To The Number Of ROWS In Second Matrix\n");
		
		//Entering Number Of Rows and Columns In Second Matrix
		System.out.print("\nEnter The Number Of Rows in Second Matrix   : ");
		
		int r2 = scan.nextInt();
		
		System.out.print("\nEnter The Number Of Columns in Second Matrix: ");
		
		int c2 = scan.nextInt();
		
		int[][] secondmatrix = new int[r2][c2];
		
		//Entering Data To The Second Matrix
		enterSecondMatrixData(scan, secondmatrix, r2, c2);
		
		int[][] answer = new int[r1][c2];
		
		//Multiplication Of The Matrices
		matrixMultiplication(firstmatrix, secondmatrix, answer, r1, c1, c2);
		
		//Printing First Matrix Data
		printFirstMatrix(firstmatrix, r1, c1);
		
		//Printing Second Matrix Data
		printSecondMatrix(secondmatrix, r2, c2);
		
		//Printing Answer Matrix Data
		printAnswerMatrix(answer, r1, c2);
		
		//Repeating The Main Method Again And Again
		System.out.print("\nDo You Want To Try Again (Y/N) : ");
		String var = scan.next();
		if(var.equalsIgnoreCase("Y")){
			main(null);
		}
	}
	
	public static void enterFirstMatrixData(Scanner scan, int[][] firstmatrix, int r1, int c1){
		
		System.out.println("\nEnter First Matrix Data: ");
		
		for(int i = 0; i < r1; i++){
			for(int j = 0; j < c1; j++){
				firstmatrix[i][j] = scan.nextInt();
			}
		}
	}
	
	public static void enterSecondMatrixData(Scanner scan, int[][] secondmatrix, int r2, int c2){
		
		System.out.println("\nEnter Second Matrix Data: ");
		
		for(int i = 0; i < r2; i++){
			for(int j = 0; j < c2; j++){
				secondmatrix[i][j] = scan.nextInt();
			}
		}
	}
	
	public static void matrixMultiplication(int[][] firstmatrix, int[][] secondmatrix, int[][] answer, int r1, int c1, int c2){
		
		for(int i = 0; i < r1; i++){
			for(int j = 0; j < c2; j++){
				for(int k = 0; k < c1; k++){
					answer[i][j] += firstmatrix[i][k]*secondmatrix[k][j];
				}
			}
		}
	}
	
	public static void printFirstMatrix(int[][] firstmatrix, int r1, int c1){
		
		System.out.println("\nYour First Matrix Is: \n");
		
		for(int i = 0; i < r1; i++){
			for(int j = 0; j < c1; j++){
				System.out.print(firstmatrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void printSecondMatrix(int[][] secondmatrix, int r2, int c2){
		
		System.out.println("\nYour Second Matrix Is: \n");
		
		for(int i = 0; i < r2; i++){
			for(int j = 0; j < c2; j++){
				System.out.print(secondmatrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void printAnswerMatrix(int[][] answer, int r1, int c2){
		
		System.out.println("\nYour Multiplied Answer Is: \n");
		
		for(int i = 0; i < r1; i++){
			for(int j = 0; j < c2; j++){
				System.out.print(answer[i][j] + "\t");
			}
			System.out.println();
		}
	}
}