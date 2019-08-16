/*
 * CSCI1130 Assignment 4
 * 
 * I declare that the assignment here submitted is original
 * except for source material explicitly acknowledged,
 * and that the same or closely related material has not been
 * previously submitted for another course.
 * I also acknowledge that I am aware of University policy and
 * guidelines and procedures applicable to breaches of such
 * policy and regulations, as contained in the website.
 * 
 * University Guideline on Academic Honesty:
 *  http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelines to Academic Honesty:
 *  https://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 * 
 * Student Name: Cheung Kam Fung
 * Student ID  : 1155110263
 * Date        : 5/11/2018
 */

package assignment4;

/**
*
* @author fung1016
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
// testcase1_e in // testcase1_d out 
//testcase1_ae in  // testcase1_ad out
public class RunLengthDecoder {
    
    public void decode(String inputFileName) throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File(inputFileName + ".txt"));   // get the file name
        StringBuilder outputFileName = new StringBuilder(inputFileName);    // create a new string
        outputFileName.setCharAt(outputFileName.length()-1,'d');            // change the last char of the file name from e to d
        PrintStream fileOut = new PrintStream(outputFileName +".txt");      // print the new file
        
        boolean startPrinting = false;
        int charCount;
        String pattern ;
        
        while(scan.hasNextLine())
        {
            if(startPrinting)fileOut.println();
            startPrinting = true;
            Scanner line  = new Scanner(scan.nextLine()); // check the line with nextInt 
            
            while(line.hasNextInt())
            {
                charCount = line.nextInt(); 
                
                if(charCount < 0)                        // check if the int is <0
                {
                    for(int i = 0 ; i < - charCount ; i++)
                    { 
                        fileOut.printf(" ");            // print space
                    }
                }
                
                if(charCount > 0)                       // check if the int is >0
                {
                    pattern = line.next();
                    for(int i = 0 ; i <  charCount ; i++)
                    {                     
                        fileOut.printf(pattern);        // print the char
                    }
                }   
            }       
        }   
    }
}
