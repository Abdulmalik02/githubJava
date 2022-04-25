import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Write a function that takes two parameters:
(1) a String representing the contents of a text document
(2) an integer providing the number of items to return
- Implement the function such that it returns a list of Strings ordered by word frequency, the most frequently occurring word first. 
- Use your best judgment to decide how words are separated.
- Implement this function as you would for a production / commercial system
- You may use any standard data structures.
- Please use Java for the solution.
Performance Constraints:
- Your solution should run in O(n) time where n is the number of characters in the document. 
- Please provide reasoning on how the solution obeys the O(n) constraint. 
 */
public class CodingChallenge {
	/*
	 * Function: wordFrequency
	 * Parameters: (1) document: a String representing the contents of a text document
	 * (2) items: an integer providing the number of items to return
	 * Returns: ArrayList of type string: ordered by word frequency, the most frequently occurring word first.
	 */
	public static ArrayList<String> wordFrequency(String document, int items){
		
		/*ArrayList used to store result list of strings*/
		ArrayList<String> result = new ArrayList<String>(items);
		
		/*Check if the text document is empty or does not have any content*/
		if(document.isEmpty())
			return result;
		
		/*In order to get case insensitive word frequency*/
		String doc = document.toLowerCase();
		
		/*Do not consider punctuation marks as a word */
		String str = doc.replaceAll("[(!?:;,.)]", " ");
		
		/*Assuming words are separated by whitespace*/
		String[] words = str.trim().split("\\s+");
		
		/*HashMap used to maintain frequency of each unique word in the document*/
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		/*Variable to store the maximum frequency of a word in the document*/
		int max_count =0;
		
		/* Part 1: Storing and counting the words: 
		 * Iterate through each word in the document and maintain its count in HashMap*/
		for(String word: words){
			if(map.containsKey(word)){
				int value = map.get(word);
				map.put(word, value+1);
				if((value + 1) > max_count)
					max_count = value + 1;
			}
			else {
				map.put(word, 1);
			    if(max_count == 0)
			    	max_count = 1;
			}
		}

		/* Take an array of size, max_count, the max frequency 
		 * of a word in the document.*/
	    String[] sorted_array = new String[max_count];
		
	    /* Part 2: Sorting: Store each word in the array at the position corresponding 
	     * to its frequency obtained from HashMap entry. 
	     * In case of words having same frequency, store multiple words 
	     * at the same index separated by a semicolon';'
	     * For example: if words "john" and "name" both have frequency 3 then array index 2 will have entry "john;name"*/
		for(Map.Entry<String, Integer> entry: map.entrySet()){
			int index = entry.getValue() - 1;
			String wordStr = entry.getKey();
			if(sorted_array[index] == null){
				sorted_array[index] = wordStr;
			}	
			else{
				String pos = sorted_array[index];
				pos = pos + ";" + wordStr;
				sorted_array[index] = pos;
			}	
		}
		/*Maintain a count of the number of items already inserted in the list*/
		int number=0;
		
		/* Add the words to the result ArrayList reading from the array in the reverse order 
		 * so the word with maximum frequency will be entered first
		 * For example: if the array is of size 7 which means that the max frequency of a word in the document is 7 
		 * then on reading the array in reverse order will read the last index first and add it into the ArrayList 
		 * at first position, followed by less frequent elements at the end of the list to be returned.
		 * In case of an entry having a semicolon, split the string to get multiple words with same frequency. 
		 * Break and return once you get the number of items required to be returned. */
		for(int j = max_count - 1; j >=0; j--){
			if(number == items){
				break;
			}
			String item = sorted_array[j];
			if(item == null)
				continue;
			if(item.indexOf(';')!=-1){
				for(String word: item.split(";")){
					result.add(word);
					number = number+1;
					if(number == items){
						return result;
					}
				}
		    } 
			else{
				result.add(item);
				number = number+1;
			}
		}
		return result;
	}
	public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	public static void main(String[] args) throws IOException{
		String file = readFile("C:\\Users\\saror_000\\Desktop\\myfile.txt");
		ArrayList<String> list = wordFrequency(file,7);
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}	
}