import java.util.*;
import java.io.*;

// GUI coming soon

public class ReversePolishCalculator{
     public static void main(String []args){
        @SuppressWarnings({ "resource" })
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your equation in reverse Polish notation:\n<Separate each element with a space>");
        String input = scanner.nextLine();
        String[] list = input.split(" ");
		Stack<Float> s = new Stack<Float>();
        float o1,o2;
        for (int i=0;i<list.length;i++){
            if (isFloat(list[i]))
                s.push(Float.parseFloat(list[i]));
            else{
                if (s.size()<2){
                	System.out.println("Not enough operands!");
                    throw new EmptyStackException();
                }
                else{
                    o1 = (float) s.pop();
                    o2 = (float) s.pop();
                    if (list[i].equals("/")&&o1==0){
                    	System.out.println("Cannot divide by zero!");
                    	throw new IllegalArgumentException();
                    }
                    else{
                        s.push(apply(o1,o2,list[i]));
                    }
                }
            }
            if (i==list.length-1&&(s.size()>1)){
            	System.out.println("Not enough operators!");
            	throw new IllegalStateException();
            }
        }
        System.out.println(s.pop());
     }
     
     private static boolean isFloat(String n){
         try{
             Float.parseFloat(n);
         }
         catch(NumberFormatException nfe){
            return false;
         }
         return true;
     }
     
     private static float apply(float o1, float o2, String op){
         switch (op){
             case "+": return o2+o1;
             case "-": return o2-o1;
             case "*": return o1*o2;
             case "/": return o2/o1;
         }
         return 0;
         
     }
}
