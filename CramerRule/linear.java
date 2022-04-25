public class linear
{
  
	//TAKING WHOLE OF MATRIX 3X3
	public static double calculateDet(double det00, double det01, double det02, 
										double det10, double det11, double det12,
										double det20, double det21, double det22 ){  	
		
		
		//AND return the RESULT OF determinant
		return( det00*det11*det22 + det01*det12*det20 + det02*det10*det21 -
		det02*det11*det20 - det01*det10*det22 - det00*det12*det21 );
	}

	
	
	
	
public static void main(String[] args) {
	int i,j;
	
	BasicMethods bMethod = new BasicMethods(); // Getting Method from BM class
	double[][] matris1 = new double[3][3];
	double[][] matris2 = new double[3][3];
	double[][] matris3 = new double[3][3];
	double[][] resultMatris = new double[3][1];
	
	
	
	
	
	bMethod.generateMatris31(resultMatris);
	bMethod.generateMatris33(matris1);
	bMethod.generateMatris33(matris2);
	bMethod.generateMatris33(matris3);
	
	bMethod.showMatris(matris1);
	bMethod.showMatris(matris2);
	bMethod.showMatris(matris3);
	bMethod.showMatris(resultMatris);
	

	System.out.println(" ");
    double[] unknown = new double[3];
    double determinant =
    		
    		//Main Det
    calculateDet( 	matris1[0][0], matris1[0][1], matris1[0][2],
    				matris1[1][0], matris1[1][1], matris1[1][2],
    				matris1[2][0], matris1[2][1], matris1[2][2] );
    
    		//Det for X value
    unknown[0] = calculateDet( 	resultMatris[0][0], matris1[0][1], matris1[0][2], 
    							resultMatris[0][0], matris1[1][1], matris1[1][2],
    							resultMatris[0][0], matris1[2][1], matris1[2][2] );
    		//Det for Y value
    unknown[1] = calculateDet( 	matris2[0][0], resultMatris[1][0], matris2[0][2], 
    							matris2[1][0], resultMatris[1][0], matris2[1][2],
    							matris2[2][0], resultMatris[1][0], matris2[2][2] );
    		
    		//Det for Z value
    unknown[2] = calculateDet(	matris3[0][0], matris3[0][1], resultMatris[2][0],
    							matris3[1][0], matris3[1][1], resultMatris[2][0],
    							matris3[2][0], matris3[2][1], resultMatris[2][0] );
    
    
    
    System.out.println( "x1 = "  + unknown[0]/determinant );  //  DetX / MainDet =  x
    System.out.println( "x2 = "  + unknown[1]/determinant );  //  DetY / MainDet =  y	
    System.out.println( "x3 = "  + unknown[2]/determinant);	  //  DetZ / MainDet =  z
    
	}
}

 
