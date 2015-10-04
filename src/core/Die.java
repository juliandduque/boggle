/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Julian
 */
public class Die {
    // Declare variables, make final variables that don't change.
   private final int NUMBER_OF_SIDES = 6;
   private final ArrayList sData = new ArrayList();
   private String letters;
   
   // randomletter creates an intance of class random, and creates an int 
   // that will be the index to the letter inside sData arraylist
   void randomLetter()
   {
       Random rand = new Random();
       int number = rand.nextInt(NUMBER_OF_SIDES);
       letters = (String) sData.get(number);
   }
   
   // returns the letter at which the index of the die is pointing to
   String getLetter()
   {
       randomLetter();
       return letters;
   }
   
   String getLetters()
   {
       return letters;
   }
   // Adds a letter to the face of each die
   void addLetter(String dieLetter)
   {
       sData.add(dieLetter);
   }
   
   // displays all 6 letters of the die
   void displayAllLetters()
   {
       int j;
       for(j=0;j<NUMBER_OF_SIDES;j++)
       {           
           System.out.print(sData.get(j)); 
       }
   }
}

