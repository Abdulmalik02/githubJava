import java.util.Scanner;

/* Kosaraju's Algorithm, finds strongly connected components with 2 iterations of Depth First Search. 
* Reads input from STDIN, prints output to STDOUT.
* Input two numbers separated by whitespace, first number is number of vertices and second number is number of edges
* On the following lines all of the edges are listed
* Outputs the number of connected components, then the size of the largest connected component, separated by whitespace
*/ 


public class Kosaraju {
   
    //variables for vertices, adjacency matrix size, number of edges, visited list, and connected components list
    public static int verticesnumber;
    public static int adjMsize;
    public static int edgesnumber;
    public static boolean[][] adjM;
    public static boolean[] visitedL;
    public static int[] compL;
     
    
    public static void main(String[] args) throws Exception {
        
      
     Scanner sc = new Scanner(System.in);
     verticesnumber = sc.nextInt();
     adjMsize = verticesnumber;
     edgesnumber = sc.nextInt();
        
     adjM = new boolean[adjMsize][adjMsize];
     visitedL = new boolean[adjMsize];
     compL = new int[adjMsize];
    
     // sets edges on adjacency matrix so it can later be traversed with DFS
    for(int t = 0; t < edgesnumber; t++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adjM[a - 1][b - 1] = true;
            adjM[b - 1][a - 1] = true;
        }
        
      
    for(int i = 0; i < (verticesnumber); i++) {
            visitedL[i] = false;
        }
        
    for(int i = 0; i < verticesnumber; i++) {
            compL[i] = 0;
        }
        
        new Kosaraju().DFSfirst();
    }
    
    //first DFS part
    public void DFSfirst() {
        int largest = 0;
        int number = 0;
        
        for(int i = 0; i < verticesnumber; i++) {
            if (visitedL[i] == false){
            DFSsecond(i, i, 0);
            }
        }
        
        for(int i = 0; i < verticesnumber; i++) {
            if (compL[i] > largest) {
                largest = compL[i];
            }
        }
        
        for(int i = 0; i < verticesnumber; i++) {
            if (compL[i] > 0) {
                number++;
            }
            
        }
        
        System.out.println(number + " " + largest);
    }
    
    //visits vertices neighbors in the adjacency matrix
    public void DFSsecond(int vertex, int scc, int size) {
        int newS = size + 1;
        visitedL[vertex] = true;
        compL[scc] = newS;
        
        for(int j = 0; j < verticesnumber; j++) {
            if (visitedL[j] == false && adjM[vertex][j]) {
                
                DFSsecond(j, scc, newS);
            }
        }
    }
    
}