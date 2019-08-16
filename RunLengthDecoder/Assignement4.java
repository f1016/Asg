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


import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignement4 {


    public static void main(String[] args) throws FileNotFoundException {
        System.out.printf("The original ASCII art picture file: ");
        Scanner scan = new Scanner(System.in); // ask for the file name without .txt
        String inputFileName = scan.next();
        
        RunLengthEncoder enCoder = new RunLengthEncoder(); // create the obj
        enCoder.encode(inputFileName); // read inputFIleName.txt output inputFileName_e.txt
        
        RunLengthDecoder deCoder = new RunLengthDecoder(); // create the obj
        deCoder.decode(inputFileName + "_e" );// read inputFIleName_e.txt output inputFileName_d.txt
        
        RunLengthEncoderAdvanced enCoderA = new RunLengthEncoderAdvanced(); // create the obj
        enCoderA.encode(inputFileName); //read inputFIleName_d.txt output inputFileName_ae.txt
        
        deCoder.decode(inputFileName + "_ae");// read inputFIleName_ae.txt output inputFileName_ad.txt
        
    }
    
}
