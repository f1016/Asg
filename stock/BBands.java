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
public class BBands {
    
    public double[][] getArray(double arrayOfSMA[] , double data[][], int D, int M, int lineCount )
    {
        try
        {
            double[][] arrayOfBBands = new double[lineCount+1 - M][2]; // create an array for storing the upper band and lower band
            int i,j;

            for(i =0 ; i<lineCount+1 - M; i++ ) 
            {
                for( j=0 ; j<M;j++)
                {
                    arrayOfBBands[i][0] += Math.pow(data[j+i][3] - arrayOfSMA[i], 2); // calculation of the S.D
                }
                
                arrayOfBBands[i][1] = arrayOfSMA[i] + Math.sqrt(arrayOfBBands[i][0]/M)*D;   // get the upper bound
                arrayOfBBands[i][0] = arrayOfSMA[i] - Math.sqrt(arrayOfBBands[i][0]/M)*D;   // get the lower bound

            }
            return arrayOfBBands; // return the array
        }
        catch(Exception e) // catch 
        {
            System.out.println("File Writing Errors");
            System.exit(0);
        }
        return null;
    }
}
