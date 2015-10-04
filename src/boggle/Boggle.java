/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boggle;
import java.util.ArrayList;
import InputOutput.*;
import core.*;
import userInterface.*;


/**
 *
 * @author Julian
 */
public class Boggle {
    
    // Initialize all our variables
   private static ArrayList bogD = new ArrayList();
   private static ArrayList words = new ArrayList();
   
   private static final String fileName1 = "BoggleData.txt";
   private static final String fileName2 = "TemporaryDictionary.txt";
   
    public static void main(String[] args) throws InterruptedException {
        
        // 1st populate data using readdatafile class
        ReadDataFile boggLetters  = new ReadDataFile(fileName1);
        ReadDataFile dictioNary = new ReadDataFile(fileName2);
        
        //obtain data from read data files
        bogD = boggLetters.getData();
        words = dictioNary.getData();
        
        // Initialize our board that stores all the data from our arraylist
        Board boardN = new Board(bogD);
        
        //Initialize our UI
        BoggleUi uI = new BoggleUi(boardN, words);
    }
    
}
