import java.util.*;

public class balancedBrackets {

	public static void main (String [] args)
    {
        Scanner scan = new Scanner(System.in);
        
        Stack myStack = new Stack(6);
        String str = scan.nextLine();
        
        for(int i = 0; i<str.length(); i++)
        {
        	String input = Character.toString(str.charAt(i));
        	
        	if(input.equals("(") | input.equals("{") | input. equals("[")) //If the char is an opening bracket,
            {
                myStack.push(input); //then push the opening bracket onto the stack
            }
            else
            {
                if(input.equals(")") && (myStack.peek()).equals("(")) //If the char is a closing bracket and the char at the top of the stack is its matching opening,
                {
                    myStack.pop(); //then pop its matching opening bracket from the stack
                }
                else if(input.equals("}") && (myStack.peek()).equals("{"))
                {
                    myStack.pop();
                }
                else if(input.equals("]") && (myStack.peek()).equals("["))
                {
                    myStack.pop();
                }
            }
        }
        
        if(myStack.isEmpty()) //If the stack is empty
        {
        	System.out.println("true"); //All brackets in the String is balanced
        }
        else
        {
        	System.out.println("false");
        }
        
    }
}
