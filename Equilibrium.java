class Equilibrium {
  
	//O(n) space, O(n) time
	public static int equi ( int[] A ) {

		if(A != null){

			long[] leftCumSum = new long[A.length];
			long[] rightCumSum = new long[A.length];

			long leftSum = 0;
			for(int i = 0; i < A.length; i++){
				leftSum += A[i];
				leftCumSum[i] = leftSum;
			}

			long rightSum = 0;
			for(int i = A.length-1; i >= 0; i--){
				rightSum += A[i];
				rightCumSum[i] = rightSum;
			}

			for(int i = 0; i < A.length; i++){
				if(i > 0 && i < A.length-1 && leftCumSum[i-1] == rightCumSum[i+1]){
					return i;
				}
				if(leftCumSum[i] == rightCumSum[i]){
					return i;
				}
			}
		}
		return -1;
	}

	public static void main(String Args[]){
		
		//Test cases
		int[] test0 = {};
		System.out.println(equi(test0));
		
		int[] test1 = {-100000};
		System.out.println(equi(test1));
		
		int[] test2 = {0,0,0,0,0,-100000};
		System.out.println(equi(test2));

		int[] test3 = {-100000,0,0,0,0,0};
		System.out.println(equi(test3));
		
		int[] test4 = {100,-20,0,-20,100};
		System.out.println(equi(test4));
	}

}