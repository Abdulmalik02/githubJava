
public class BasicMethods {

		//GENERATING MATRIS 3x1
	public double[][] generateMatris31 (double[][] anyMatris){
		for(int a=0;a<3;a++){
			for(int b=0;b<1;b++){
				anyMatris[a][b] = (int)(Math.random()*9);
				}
			}
		
		return anyMatris;
	}
	
	
	//3x3 MATRIS GENERATOR
	public double [][] generateMatris33(double [][] anyMatris){
		
		for ( int i=0; i<3;i++){
			for(int j=0;j<3;j++){
			int number =(int)(Math.random()*9);
			anyMatris[i][j] = number;
			number =(int)(Math.random()*9);
			anyMatris[i][j] = number;
			number =(int)(Math.random()*9);
			anyMatris[i][j] = number;
			}
		}
		return anyMatris;
	}
	
	
	//Showing Matris in console
	
	public void  showMatris(double [][] anyMatris ){
		for( int i=0;i<anyMatris.length;i++){
			 for( int j=0;j<anyMatris[0].length;j++){
				 
				 
				 System.out.print(anyMatris[i][j]+" ");
				 
			 }
			 System.out.println(" ");
		 }
		 System.out.println(" ");
		

	}
	
		//3x3 CALCULATE DETERMINANT
	
	
	 
 

}