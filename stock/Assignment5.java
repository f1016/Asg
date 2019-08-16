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

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Fung
 */
public class Assignment5 {
    
    public static void main(String[] args) {            
        String fileName;
        int M,D;
        double[][] data ,arrayOfBBands;  // the array of stockdata , BBands 
        double[] arrayOfSMA; // array of SMA
        Scanner scan = new Scanner(System.in); // scan the input by user
       
        System.out.printf("The prices of a stock in CSV format: ");
        fileName = scan.next(); // get file name
        
        StockData stockData = new StockData(); // construct an obj
        data = stockData.getDataArray(fileName); // use the obj to get data 
        
        System.out.printf("The value of M : ");
        M = scan.nextInt(); // get the day
        System.out.printf("The value of D: ");
        D = scan.nextInt();   // get the SD
        
        SMA getSMA = new SMA(); //construct an obj
        arrayOfSMA = getSMA.getArray(data , M , stockData.getLineCount()); // use the obj to get SMA

        BBands getBBands = new BBands(); // construct an obj
        arrayOfBBands = getBBands.getArray(arrayOfSMA, data, D, M, stockData.getLineCount()); // use the obj to get BBands
        
        try
        {
            if(stockData.getLineCount() < M) // check if days the user needed is larger than the input data  
            {
                System.out.println("File Writing Errors");
                System.exit(0);
            }
            
            PrintStream fileOut = new PrintStream(fileName +"_ta.csv"); // printer
            fileOut.printf("%d-day SMA,LowerBand(-%dS.D.),UpperBand(+%dS.D.)\n", M, D, D); // print the first line
            
            for(int i=0;i<arrayOfSMA.length ;i++) // loop till finish printing all the data
            {
                fileOut.printf("%.5f,%.5f,%.5f\n",arrayOfSMA[i],arrayOfBBands[i][0],arrayOfBBands[i][1]); // print SMA,loweer band , upper band
            }
        } 
        catch (Exception e) 
        {
            System.out.println("File Writing Errors");
            System.exit(0);
        }
    }
    
}
