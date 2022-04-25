import java.util.Arrays;
/**
*	Stooge sort is a recursive sorting algorithm with a time complexity of 
*	O(n^(log 3 / log 1.5) ) = O(n^(2.7095...)). The running time of the 
*	algorithm is thus slower compared to efficient sorting algorithms, such as 
*	Merge sort, and is even slower than Bubble sort, a canonical example of a 
*	fairly inefficient and simple sort.
*
*	The algorithm is defined as follows:
*	
*	- If the value at the end is smaller than the value at the start, swap them.
*	- If there are 3 or more elements in the list, then:
*		1. Stooge sort the initial 2/3 of the list
*		2. Stooge sort the final 2/3 of the list
*		3. Stooge sort the initial 2/3 of the list again
*	- else: exit the procedure
*	It is important to get the integer sort size used in the recursive calls by 
*	rounding the 2/3 upwards, e.g. rounding 2/3 of 5 should give 4 rather than 
*	3, as otherwise the sort can fail on certain data. However, if the code is 
*	written to end on a base case of size 1, rather than terminating on either 
*	size 1 or size 2, rounding the 2/3 of 2 upwards gives an infinite number of 
*	calls.
*	
*	Implementation based on: https://en.wikipedia.org/wiki/Stooge_sort
*	License: https://creativecommons.org/licenses/by-sa/3.0/
*/

public final class StoogeSort {
	public static void sort(int[] array) {
		sort(array, 0, array.length - 1);
	}
	
	private static void sort(int[] array, int i, int j) {
		if(array[i] > array[j]) {
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp; 
		}
		if(j - i + 1 > 2) {
			int t = (int) Math.floor((j - i + 1) / 3);
			sort(array, i, j - t);
			sort(array, i + t, j);
			sort(array, i, j - t);
		}
	}
	
	public static void main(String[] args) {
		int[] array = {9, 8, 7, 6, 5, 4, 3, 1, 2, 0};
		System.out.println(Arrays.toString(array));
		sort(array);
		System.out.println(Arrays.toString(array));
	}
}