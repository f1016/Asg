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

/**
 *
 * @author Fung
 */
public class SMA {
    
    public double[] getArray(double[][] data, int M, int lineCount)
    {
        try
        {
            double[] arrayOfSMA = new double[lineCount + 1 - M]; //create an array for storing the SMA
            int i,j;

            for( i=0 ; i < lineCount + 1 - M; i++) // loop to change the i
            {  
                for( j=0 ; j < M; j++) // loop to change the j
                {
                    arrayOfSMA[i] += data[j+i][3]; // we only need to use the close price, which is in the col 3
                }

                arrayOfSMA[i] /= M;    // divide the day
            }
            return arrayOfSMA; // return the SMA
        }
        catch(Exception e)
        {
            System.out.println("File Writing Errors");
            System.exit(0);
        }
        return null;
    }
  
    
}
