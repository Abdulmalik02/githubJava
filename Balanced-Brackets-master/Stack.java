import java.util.*;

public class Stack
{
    int size;
    int top;
    String [] array;
    
    public Stack(int size)
    {
       array = new String [size];
       top = 0;
    }
    
    public void push(String s)
    {
        array[top] = s;
        top++;
    }
    
    public String pop()
    {
    	String temp = array[top-1];
        array[top-1] = null;
        top--;
        return temp;
    }
    
    public String peek()
    {
        return array[top-1];
    }
    
    public boolean isEmpty()
    {
        if(top==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
