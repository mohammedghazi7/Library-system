package displayoutput;
import java.util.Scanner;
public class displayoutput {
 

    public static void main(String[]args)
    {
        Scanner input = new Scanner (System.in);
        System.out.println("Start of the prograam");
        drawline();
            System.out.println("Welcome to the fist programm");
            drawline();
         
    }

    public static void drawline() {
        for(int i =0;i<10;i++){
            System.out.print("*");
        }
        System.out.println();

    }
    
}
