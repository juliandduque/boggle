/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputOutput;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;




/**
 *
 * @author Julian
 */
public class ReadDataFile {
//Initialize scanner, string that stores filename, and data list.
    private Scanner input;
    private String fileN;
    private ArrayList dataList = new ArrayList();
    
// constructor makes instance that saves input as filename
    public ReadDataFile(String fileName)
    {
        fileN = fileName;
        populateData();
    }
    
    // populateData sets URL class equal to the resource class of the file
    // then it is set as an address to the file that is passed on to the scanner
    // data is saved temporarely in variable input and later stored in dataList
    public void populateData()
    {
        System.out.println("Processing file: " + fileN);
             
        try
        {
            URL url = getClass().getResource(fileN);
            File file = new File(url.toURI());
            input = new Scanner(file);
            
            while(input.hasNext( ))
            {
                dataList.add(input.next().toUpperCase());
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        finally
        {
            input.close();
        }
    }    
    
    // ArrayList returns the dataList created with all the populated data
    public ArrayList getData()
    {
      return dataList;
    }
    
}
    
        

