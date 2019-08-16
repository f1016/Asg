/*
 * CSCI1130 Assignment 5
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
 * Date        : 19/11/2018
 */
package assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author Fung
 */
public class StockData {

    private int lineCount = 0;
        
    public double[][] getDataArray(String fileName)
    {
       double[][] data = new double[5000][6]; // there are maximum of 5000 rows
       Scanner lineScan,file; //scanner 
       int i =0, j =0 ;
        
       try
       {
            file = new Scanner(new File(fileName + ".csv")); // scan the scv file
            while(file.hasNextLine()) // check whether there are a new line
            {
                lineScan = new Scanner(file.nextLine()).useDelimiter(","); // check the data seperated by ,
                lineScan.next();  // skip storing the date 
                
                    while(lineScan.hasNextDouble()) // check whether there are data
                    {
                        data[i][j] = lineScan.nextDouble(); // get the data one by one
                        j++;
                        if(j==6) // each column has only 5 numerical data
                        {   
                            lineCount++; // next line so line count plus one
                            i++; // change the position of the array
                            j=0; // change the position of the array
                        }
                    }
                    if(j!=0) // check if there are some error in the Stockdata such as non-numerical data ,
                    {
                        System.out.println("File Reading Errors");
                        System.exit(0);// terminate the programme
                    }
            }
       }
       catch(FileNotFoundException e) // catch fileNotFoundException
       {
           System.out.println("File Reading Errors");
           System.exit(0); // terminate the programme
       }
       
        return data; // return the stockdata array
    }
    
    public int getLineCount()
    {        
       return lineCount; // return how many rows of data are there
    }
    
}
