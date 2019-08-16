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
import java.io.*;
import java.util.*;
// testcase1 in // testcase1_e out 
public class RunLengthEncoderAdvanced {
    
    public void encode(String inputFileName) throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File(inputFileName + ".txt")); //
        PrintStream fileOut = new PrintStream(inputFileName + "_ae.txt");
        
        int charCount  , stringCount=1;    // charCount is used to count the number of char , stringCount is used to count the number of repeated pair of char 
        String temp = "" , temp2 = "" ,stringChar =""; // all these string is used to check the repeated pair of char
        boolean multiChar , space, newLine = false ;     //multiChar is used to check if we need to print 1 for the consecutive char
                                                        //space is used to check if we need to print space AFTER printing consecutive char 
        while (scan.hasNextLine())                       
        {
            if(newLine) fileOut.println();         // print the new line except first time printing
            newLine = true;                        // we need to print the new line 
            
            String line = scan.nextLine();                  // scan the line
            
            charCount =1;
            multiChar = true; space = false;                //space implies whether we need to print a space before printing anything 
                                                            // multiChar implies whether we need to print '1' before a sequence of character after print other pattern
            
            for(int i =0 ; i< line.length()-1;i++)
            {
                if(line.charAt(i) == line.charAt(i+1))      // counting duplicate character
                {
                    charCount++;                            
                    
                    if(i+1 == line.length()-1 )              // check the last two or more duplicate character is space or not
                    {
                        if(space) fileOut.printf(" ");
                        if( line.charAt(i+1) == ' ')fileOut.printf("%d " , -charCount);     // the last two or more character is a spcae then print -digit
                        if(line.charAt(i+1) != ' ')fileOut.printf("%d %c " , charCount,line.charAt(i));  // the last two or more character is not a space then print digit char
                    }

                }
                
                if(line.charAt(i) != line.charAt(i+1) && line.charAt(i) != ' ' && charCount == 1)  // print the consecuctive different character without space
                {
                    //extended part START
                    while(i < line.length() - 3                      // avoid out of range
                            && line.charAt(i) != line.charAt(i+1) && // check the two consecutive char is different
                            line.charAt(i) !=  ' ' && line.charAt(i+1) != ' ')  // check the character is space or not 
                    {
                        temp += line.charAt(i);    // add the char1 and 
                        temp += line.charAt(i+1);  // the char2  in a string
                        temp2 +=  line.charAt(i+2); // add the char3
                        temp2 += line.charAt(i+3); // and the char4 in a string
                        stringChar = temp; // store the string

                        if(temp.equals(temp2)) // if the two string is equal then
                        {
                            i += 2;         // increase i by 2 to check the next pair of char
                            stringCount++;  // increase the counting
                            temp = "";      // delete the previous string
                            temp2 = "";     // same as above
                        }
                        else                // if the two char is not equal
                        {
                            temp = "";      // clear the string
                            temp2 = "";     // clear the string
                            break;          // then break
                        }
                    }
                    
                    if(stringCount > 1)     // if there are 2 char are repeated then print
                    {
                        if(space) fileOut.printf(" ");                   // check if we need to print the space
                        fileOut.printf("%d %s ",stringCount ,stringChar); // print the amount of the 2 char 
                        stringCount = 1 ;                                // clear the used data
                        stringChar = "";                                 // clear the data
                        space = false;                                  //reset the space detector
                        multiChar = true;                               // also reset the multiChar detector
                        i++;                                            // increase i by 1 
                        continue;                                       // countinue to check whether the next two char is repeated or not 
                    }
                    //extended part END
                    
                    if(multiChar) fileOut.printf("1 ");  // when its true, that means we need to print a 1 BEFORE this printing 
                    fileOut.printf("%c",line.charAt(i)); 
                    
                    space = true;                           // when space is true ,that means we need to print a space AFTER printing all consecutive char
                    multiChar = false;                      // that means we dont need to print 1 again
                    
                    if(i+1 == line.length()-1)                            // check the last character 
                    {
                        if(line.charAt(i+1) == ' ') 
                        {
                            if(space) fileOut.printf(" ");
                            fileOut.printf("-1"); // the last character is a space                   
                        }
                        else fileOut.printf("%c " ,line.charAt(i+1));     // the last character is also a character 
                    }
                    
                    space = true;                           // when space is true ,that means we need to print a space AFTER this printing
                    multiChar = false;                      // that means we dont need to print 1 again
                }
                
                if(line.charAt(i) != line.charAt(i+1) && line.charAt(i) == ' ') // print the space 
                {
                    if(space) fileOut.printf(" ");     // check if it is AFTER the consecutive different char , if yes print a space
                    fileOut.printf("%d ", -charCount);  // print the amount of space
                    
                    if(i+1 == line.length()-1 && line.charAt(i+1) != ' ')   //Check the LAST char whether it is not a space; if not then print char
                    {
                        fileOut.printf("1 %c " ,line.charAt(i+1));     // print the char
                    }
                    
                    charCount = 1;                    // change the charCount to 1 
                    space = false;                    // no need to print the space again                    
                    multiChar = true;                 // change it to default in order to print 1 if we need to print consecutive char
                }
                
                if(line.charAt(i) != line.charAt(i+1) && line.charAt(i) != ' ' && charCount > 1) // print the duplicate character 
                {
                    if(space) fileOut.printf(" ");      // check if it is AFTER the consecutive different char , if yes print a space
                    fileOut.printf("%d %c ",charCount , line.charAt(i)); // print the amount of the char and the char
                    
                    if(i+1 == line.length()-1) // check the LAST char
                    {
                        if(line.charAt(i+1) != ' ') fileOut.printf("1 %c ",line.charAt(i+1)); // if the LAST char is not space , print the character
                        else fileOut.printf("-1"); // if it is not a char then print -digit (space)
                    }

                    charCount = 1;
                    space = false;
                    multiChar = true;
                }
            }
        }
    }
}