/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;
import java.awt.*;
import javax.swing.*;
import core.Board;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Julian
 */
public class BoggleUi extends JFrame {
    // Menubar items
    private static JMenuBar menuBar;    
    private static JMenu Boggle;
    private static JMenuItem NewGame;	
    private static JMenuItem Exit;	
    
    //Button panel items
    private static JPanel buttonPanel; 
    private static JButton diceB;
    
    //Text area, shake dice button, and timer items in bogglePanel
    private static JPanel bogglePanel;
    private static JScrollPane scrollPane;
    private static JLabel time; 
    private static JButton shakeD;
    private static JTextPane bogText;
    
    //South Panel with buttons and text area
    private static JPanel southPanel;
    private static JLabel currentWord;
    private static JLabel score;
    private static JButton submit; 
    int currentS = 0;
    int lastB=17;
    
    //font
    Font font = new Font("Arial", Font.PLAIN, 56);
      Font font4 = new Font("Arial", Font.PLAIN, 16);
    
    //timer variables
    int minutes;
    int timet;
    int seconds;
    long delay;
    
    //Create board class
    Board board = new Board();
    
    // Create Array List and variables for words and buttons
    private ArrayList bogWorder = new ArrayList();
    private ArrayList bogWords = new ArrayList();
    private ArrayList wordsFound = new ArrayList();
    private ArrayList wordsFoundPc = new ArrayList();
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();
    private String currentWorder = "";
    
    // BoggleUi constructor
   public BoggleUi(Board oldBoard, ArrayList words)  
         {
             board = oldBoard;
             bogWorder = words;
             bogWords = bogWorder;
            initComponents(); 
             
         }
   // initiate components
   void initComponents()
         {
            
             // Create constraints for JFrame
             this.setTitle("Boggle");	
             this.setDefaultCloseOperation(EXIT_ON_CLOSE);
             this.setPreferredSize(new Dimension(700, 700));
             this.setMinimumSize(new Dimension(700, 700));
             
             // Create menubar 
             menuBar = new JMenuBar();
             Boggle = new JMenu("Boggle");
             Boggle.setMnemonic('B');
             menuBar.add(Boggle);
             this.setJMenuBar(menuBar);
             
             // Create menu item new game
             NewGame = new JMenuItem("New Game");
             NewGame.addActionListener(new RestartListener());
             Boggle.add(NewGame);
             
             // create menu item exit
             Exit = new JMenuItem("Exit");
             Exit.addActionListener(new ExitListener());
             Boggle.add(Exit);
             
             // Create button panel
             setupPlayerPanel(); 
             
             // Create options panel
             setupBogglePanel();
             
             // Create bottom panel
             setupSouthPanel();
             // add panels to jframe
             
             timet= 3*60;  
             delay = timet * 1000;
             startTimer();
                         
         }
   
   void setupPlayerPanel()
         {
             // set constrains to button panel
             buttonPanel = new JPanel(new GridLayout(4, 4));
             buttonPanel.setMinimumSize(new Dimension(450, 350));
             buttonPanel.setPreferredSize(new Dimension(450, 350));
             buttonPanel.setBorder(BorderFactory.createTitledBorder("Boggle Board"));
             buttonList.clear();
             
        for(int i=0; i<16; i++)
        {            // add letters to each dice and add dice to GUI
                    diceB = new JButton(board.getLetter(i));
                    diceB.addActionListener(new ButtonListener(i));
                    diceB.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    Font font2 = new Font("Arial", Font.PLAIN, 28);
                    diceB.setFont(font2);
                    buttonList.add(diceB);
                    buttonPanel.add(diceB);             
        }  
            this.add(buttonPanel, BorderLayout.WEST);
            this.pack();
            this.setVisible(true);
      }
     

     void setupBogglePanel()
        {
                // Create constrains for optiosn panel
                bogglePanel = new JPanel();
                bogglePanel.setLayout(new BoxLayout(bogglePanel, BoxLayout.PAGE_AXIS));
                bogglePanel.setBorder(BorderFactory.createTitledBorder("Words Found"));
                
                // Create JText Area
                bogText = new JTextPane();           
                bogText.setEditable(false);
                Font font3 = new Font("Arial", Font.PLAIN, 16);
                bogText.setFont(font3);
                scrollPane = new JScrollPane(bogText);
                scrollPane.setPreferredSize(new Dimension(230, 340));
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                bogglePanel.add(scrollPane, BorderLayout.NORTH ); 
               
                // Create Time Label
    
                    
                JPanel clkPanel = new JPanel();                
                time = new JLabel(String.valueOf(minutes)+":"+String.valueOf(seconds));
                time.setPreferredSize(new Dimension(220, 120));
                time.setFont(font);
                time.setBorder(BorderFactory.createTitledBorder("Time Left"));
                bogglePanel.add(clkPanel);
                clkPanel.add(time);
                // Create shake dice button
                shakeD = new JButton("Shake Dice");
                shakeD.addActionListener(new ShakedListener());
                diceB.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                shakeD.setPreferredSize(new Dimension(100,50));
                JPanel buttonPan = new JPanel();
                bogglePanel.add(buttonPan);
                buttonPan.add(shakeD, BorderLayout.WEST);   
                this.add(bogglePanel, BorderLayout.CENTER);
                this.pack();
                this.setVisible(true);
        }
     
        void setupSouthPanel()
        {
            // create panel
            southPanel = new JPanel();
            southPanel.setPreferredSize(new Dimension(700,100));
            
            // Create Labels
            currentWord = new JLabel(" ");
            currentWord.setBorder(BorderFactory.createTitledBorder("CurrentWord"));
            currentWord.setPreferredSize(new Dimension(200,90));
            score = new JLabel(" ");
            score.setFont(font);
            score.setBorder(BorderFactory.createTitledBorder("Score"));
            score.setPreferredSize(new Dimension(200,90));
            submit = new JButton("Submit");
            submit.addActionListener(new SubmitListener());
            southPanel.add(currentWord, BorderLayout.WEST);
            southPanel.add(submit, BorderLayout.CENTER);
            southPanel.add(score, BorderLayout.EAST);
            
            
            // Create Button
            submit = new JButton(" SUBMIT "); 
              this.add(southPanel, BorderLayout.SOUTH);
              this.pack();
              this.setVisible(true);
              
        }

     
   
// Action Listener to Restart the game
   class RestartListener implements ActionListener
   {
        public void actionPerformed(ActionEvent e)
        { 
            int response = JOptionPane.showConfirmDialog(null, "Confirm to start new game?", 
                    "Start?", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION)
                timet= 3*60;
                delay = timet * 1000;
                minutes = 3;
                seconds = 0;
                buttonPanel.removeAll();
                bogglePanel.removeAll();
                southPanel.removeAll();
                currentWorder = "";
                board.shakeDice();    
                setupPlayerPanel();
                setupBogglePanel();
                shakeD.setEnabled(true);
                setupSouthPanel();
                currentS = 0;
                lastB=17;
                wordsFound.clear();
                bogWords = bogWorder;
               
        }
   }
   
   class ShakedListener implements ActionListener
   {
        public void actionPerformed(ActionEvent e)
        { 
                timet= 3*60;
                delay = timet * 1000;
                minutes = 3;
                seconds = 0;
                buttonPanel.removeAll();
                bogglePanel.removeAll();
                southPanel.removeAll();
                currentWorder = "";
                board.shakeDice();
                setupPlayerPanel();
                setupBogglePanel();
                shakeD.setEnabled(false);
                setupSouthPanel();
                currentS = 0;
                lastB = 17;
                wordsFound.clear();
                bogWords = bogWorder;              
        }
   }
              

// Action Listener for exit game
    class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int response = JOptionPane.showConfirmDialog(null, "Confirm to exit Boggle?", 
                    "Exit?", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION)
                System.exit(0);	
        }	
    }
    
  // Timer for clock
  
    void startTimer(){
         // Convert to seconds
            while (delay!= -1000) 
             {
                 try 
                  {
                 time.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                  minutes = timet / 60;
                  seconds = timet % 60;
                  Thread.sleep(1000);
                  timet = timet - 1;
                  delay = delay - 1000;
                  
             } 
         catch (InterruptedException e) {}
        }
         if (delay == -1000)
             try
             {
            shakeD.setEnabled(false);
            submit.setEnabled(false);
            southPanel.add(submit, BorderLayout.CENTER);
            time.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
            disableButtons();
            time.setText("Comparing words");
            time.setFont(font4);
            Random rand = new Random();
            int number = rand.nextInt(wordsFound.size());
            System.out.print(number);
            int randIndex;
            String wordPc;
            for(int z=0; z<number; z++)
            {
                randIndex = rand.nextInt(z+1);
                wordPc = wordsFound.get(randIndex).toString();
                wordsFound.remove(randIndex);
                wordsFoundPc.add(wordPc);
                bogText.setContentType( "text/html" );
                bogText.setText("<p>Your words:</p>" + wordsFound+ "<p> Computer Words:</p>" + "<strike>" + wordsFoundPc + "</strike>");
                 System.out.print(z);
                
                if (wordPc.length() == 3)
                {                   
                    currentS = currentS - 1;
                    score.setText(String.valueOf(currentS));
                }
                if (wordPc.length() == 4)
                {
                  
                    currentS = currentS - 1;
                    score.setText(String.valueOf(currentS));
        
                }
                if (wordPc.length() == 5)
                {
                    
                    currentS = currentS - 2;
                    score.setText(String.valueOf(currentS));
                    
                }
                if (wordPc.length() == 6)
                {
                  
                    currentS = currentS - 3;
                    score.setText(String.valueOf(currentS));
                    
                }
                if (wordPc.length() == 7)
                {
                   
                    currentS = currentS - 5;
                    score.setText(String.valueOf(currentS));
               
                }
                if (wordPc.length() >= 8)
                {
                   
                    currentS = currentS - 8;
                    score.setText(String.valueOf(currentS));
                    
                }
            }
            Thread.sleep(300000);
            System.exit(0);
            }
         catch (InterruptedException e) {}
    }
    
    
    class ButtonListener implements ActionListener {
        int indexter;
    private ButtonListener(int a)
    {
        indexter = a;
       
    }
        public void actionPerformed(ActionEvent e)
        {
            currentWorder += board.getLetter(indexter);
            currentWord.setText(currentWorder);
            switch(indexter){
                case 0:
                    System.out.print(indexter);
                    disableButtons();
                    System.out.print(indexter);
                    buttonList.get(1).setEnabled(true);
                    buttonList.get(4).setEnabled(true);
                    buttonList.get(5).setEnabled(true);
                    break;
                case 1:
                    System.out.print(indexter);
                    disableButtons();
                    buttonList.get(0).setEnabled(true);
                    buttonList.get(2).setEnabled(true);
                    buttonList.get(5).setEnabled(true);
                    buttonList.get(4).setEnabled(true);
                    buttonList.get(6).setEnabled(true);
                    break;
                case 2:
                    disableButtons();
                    buttonList.get(1).setEnabled(true);
                    buttonList.get(3).setEnabled(true);
                    buttonList.get(6).setEnabled(true);
                    buttonList.get(5).setEnabled(true);
                    buttonList.get(7).setEnabled(true);
                    break;
                case 3:
                    disableButtons();
                    buttonList.get(2).setEnabled(true);
                    buttonList.get(6).setEnabled(true);                    
                    buttonList.get(7).setEnabled(true);
                    break;
                case 4:
                    disableButtons();
                    buttonList.get(0).setEnabled(true);                    
                    buttonList.get(1).setEnabled(true);
                    buttonList.get(5).setEnabled(true);
                    buttonList.get(8).setEnabled(true);
                    buttonList.get(9).setEnabled(true);
                    break;
                case 5:
                    disableButtons();
                    buttonList.get(1).setEnabled(true);                    
                    buttonList.get(2).setEnabled(true);
                    buttonList.get(0).setEnabled(true);
                    buttonList.get(6).setEnabled(true);
                    buttonList.get(10).setEnabled(true);
                    buttonList.get(8).setEnabled(true);
                    buttonList.get(9).setEnabled(true);
                    buttonList.get(4).setEnabled(true);
                    break;
                case 6:
                    disableButtons();
                    buttonList.get(2).setEnabled(true);
                    buttonList.get(1).setEnabled(true);
                    buttonList.get(3).setEnabled(true);
                    buttonList.get(7).setEnabled(true);
                    buttonList.get(10).setEnabled(true);
                    buttonList.get(9).setEnabled(true);
                    buttonList.get(11).setEnabled(true);
                    buttonList.get(5).setEnabled(true);
                    break;
                case 7:
                    disableButtons();
                    buttonList.get(3).setEnabled(true);
                    buttonList.get(2).setEnabled(true);                    
                    buttonList.get(6).setEnabled(true);
                    buttonList.get(11).setEnabled(true);
                    buttonList.get(10).setEnabled(true);
                    break;
                case 8:
                    disableButtons();
                    buttonList.get(4).setEnabled(true);
                    buttonList.get(5).setEnabled(true);
                    buttonList.get(9).setEnabled(true);
                    buttonList.get(12).setEnabled(true);
                    buttonList.get(13).setEnabled(true);
                    break;
                case 9:
                    disableButtons();
                    buttonList.get(5).setEnabled(true);
                    buttonList.get(4).setEnabled(true);
                    buttonList.get(6).setEnabled(true);
                    buttonList.get(10).setEnabled(true);
                    buttonList.get(13).setEnabled(true);
                    buttonList.get(12).setEnabled(true);
                    buttonList.get(14).setEnabled(true);
                    buttonList.get(8).setEnabled(true);
                    break;
                case 10:
                    disableButtons();
                    buttonList.get(6).setEnabled(true);
                    buttonList.get(5).setEnabled(true);
                    buttonList.get(7).setEnabled(true);
                    buttonList.get(11).setEnabled(true);
                    buttonList.get(14).setEnabled(true);
                    buttonList.get(13).setEnabled(true);
                    buttonList.get(15).setEnabled(true);
                    buttonList.get(9).setEnabled(true);
                    break;
                case 11:
                    disableButtons();
                    buttonList.get(7).setEnabled(true);
                    buttonList.get(6).setEnabled(true);
                    buttonList.get(10).setEnabled(true);
                    buttonList.get(14).setEnabled(true);
                    buttonList.get(15).setEnabled(true);
                    break;
                case 12:
                    disableButtons();
                    buttonList.get(8).setEnabled(true);
                    buttonList.get(9).setEnabled(true);
                    buttonList.get(13).setEnabled(true);
                    break;
                case 13:
                    disableButtons();
                    buttonList.get(9).setEnabled(true);
                    buttonList.get(8).setEnabled(true);
                    buttonList.get(10).setEnabled(true);
                    buttonList.get(14).setEnabled(true);
                    buttonList.get(12).setEnabled(true);
                    break;
                case 14:
                    disableButtons();
                    buttonList.get(10).setEnabled(true);
                    buttonList.get(9).setEnabled(true);
                    buttonList.get(11).setEnabled(true);
                    buttonList.get(15).setEnabled(true);
                    buttonList.get(13).setEnabled(true);
                    break;
                case 15:
                    disableButtons();
                    buttonList.get(11).setEnabled(true);
                    buttonList.get(10).setEnabled(true);
                    buttonList.get(14).setEnabled(true);                    
                    break;
            }  
              if(lastB<16)
            {
                buttonList.get(lastB).setEnabled(false);
            }
            lastB = indexter; 
        }	
    }
    
    class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            lastB = 17;
            
            if(bogWords.contains(currentWorder))
            {
                int size = currentWorder.length();
                bogWords.remove(currentWorder);
                if (size<3)
                {
                    currentWorder = "";
                    currentWord.setText(currentWorder);
                }
                if (size == 3)
                {
                    wordsFound.add(currentWorder);
                    bogText.setText("\n Your words: \n" + wordsFound+ "\n");
                    currentS = currentS + 1;
                    score.setText(String.valueOf(currentS));
                    currentWorder = "";
                    currentWord.setText(currentWorder);
                }
                if (size == 4)
                {
                    wordsFound.add(currentWorder);
                    bogText.setText("\n Your words: \n" + wordsFound+ "\n");
                    currentS = currentS + 1;
                    score.setText(String.valueOf(currentS));
                    currentWorder = "";
                    currentWord.setText(currentWorder);
                }
                if (size == 5)
                {
                    wordsFound.add(currentWorder);
                    bogText.setText("\n Your words: \n" + wordsFound+ "\n");
                    currentS = currentS + 2;
                    score.setText(String.valueOf(currentS));
                    currentWorder = "";
                    currentWord.setText(currentWorder);
                }
                if (size == 6)
                {
                    wordsFound.add(currentWorder);
                    bogText.setText("\n Your words: \n" + wordsFound+ "\n");
                    currentS = currentS + 3;
                    score.setText(String.valueOf(currentS));
                    currentWorder = "";
                    currentWord.setText(currentWorder);
                }
                if (size == 7)
                {
                    wordsFound.add(currentWorder);
                    bogText.setText("\n Your words: \n" + wordsFound+ "\n");
                    currentS = currentS + 5;
                    score.setText(String.valueOf(currentS));
                    currentWorder = "";
                    currentWord.setText(currentWorder);
                }
                if (size >= 8)
                {
                    wordsFound.add(currentWorder);
                    bogText.setText("\n Your words: \n" + wordsFound+ "\n");
                    currentS = currentS + 8;
                    score.setText(String.valueOf(currentS));
                    currentWorder = "";
                    currentWord.setText(currentWorder);
                }
                else
                {
                    currentWorder = "";
                    currentWord.setText(currentWorder);
                }
            }
             if(wordsFound.contains(currentWorder))
                {
                    JOptionPane.showMessageDialog(null, "Word already found");
                    currentWorder = "";
                    currentWord.setText(currentWorder);
                }
            else
            {
                currentWorder = "";
                currentWord.setText(currentWorder);
            }
           enableButtons();
        }	
    }
    
    private void enableButtons() {
        for(int cr = 0; cr<16; cr++){
                    buttonList.get(cr).setEnabled(true);
                    }
    }
    private void disableButtons() {
        for(int cr = 0; cr<16; cr++){
                    buttonList.get(cr).setEnabled(false);
                    }
    }
}


    

