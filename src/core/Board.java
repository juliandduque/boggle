/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Julian
 */
public class Board {
   private final int NUMBER_OF_DICE = 16;
   private final int NUMBER_OF_SIDES = 6;
   private final int GRID = 4;
   
   private ArrayList dData = new ArrayList();
   private ArrayList<Die> dList = new ArrayList<Die>();
      public Board()
         {        
         }
      // save all the data obtained by readdatafile passed from main
      public Board(ArrayList bData)
         {
             dData = bData;   
             shakeDice();
         }
      // populate dice loops through each dice, creates an instance of die
      // calls function addLetter from die class with the data from arraylist
      // passed on as a parameter. Then each die is saved onto List of type die
      public void populateDice()
         {
             int i, j, index=0;
             for(i=0;i<NUMBER_OF_DICE;i++)
             {
                Die die = new Die();
                // System.out.print("\n Die" + i +":");
                for(j=0;j<NUMBER_OF_SIDES;j++)
                {
                    die.addLetter(dData.get(index).toString()); 
                    index++;
                }
                // die.displayAllLetters();
                dList.add(die);
                
             }
              Collections.shuffle(dList);
         }
      // ShakeDice prints all data into a grid, returns the die list, and sets
      // a random letter for each die in the list
      public ArrayList shakeDice()
         {
           populateDice(); 
           int i, c=0;
           // System.out.print("\n\nBoggle Board: \n");
           for(i=0;i<NUMBER_OF_DICE;i++)
           {
               String letter = dList.get(i).getLetter();
               
               /*
               if(c==GRID)
               {
                   System.out.println(" ");
                   c = 0;
               }
               
               System.out.print(letter + " "); 
               
               c++;*/
           }
           
          // System.out.println("\n  hello");
         
           return dList;
         }
      
      public String getLetter(int i)
      {
          String letter = dList.get(i).getLetters();
          return letter;
      }
}
