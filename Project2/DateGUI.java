import javax.swing.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import javax.swing.JFrame;
import java.awt.*;
import java.util.StringTokenizer;
import java.awt.event.*;
import java.io.*;

public class DateGUI extends JFrame {
    private static Date212 date;
    public static StringTokenizer myTokens;
    public static String line;
    public static TextFileInput inFile;
    // private int countDates;
    public SortedDateList SDL;
    public UnsortedDateList UDL;
    public static TextArea unsortedList;
    public static TextArea sortedList;
    public static Container myContentPane;
      
    //All the declared variables...

   public DateGUI(String title, int height, int width) {
	    setTitle(title);
	    setSize(height,width); //height and width
        setLocation  (400,200);
        setLayout(new GridLayout(1, 2));
	    createFileMenu();
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
   } //DateGUI constructor.
   /**
   The parameters are passed in from the Project2 file. 
    */


   private void createFileMenu() {
      JMenuItem   item;
      JMenuBar    menuBar  = new JMenuBar();
      JMenu       fileMenu = new JMenu("File"); //Name of the menu in the bar
      FileMenuHandler fmh  = new FileMenuHandler(this); //The FileMenuHandler which holds the menu bar options amongst more and passes in DateGUI-"this".

        item = new JMenuItem("Open");    //Open is a menu item.
        item.addActionListener( fmh );
        fileMenu.add( item ); //The process of acutally adding the item to the menu!

      fileMenu.addSeparator();           //add a horizontal separator line between "Open" and "Quit".
    
        item = new JMenuItem("Quit");       //Quit is a menu item.
        item.addActionListener( fmh );
        fileMenu.add( item );

      setJMenuBar(menuBar); //Like the "setVisible", it makes the menuBar visible.
      menuBar.add(fileMenu); //Adds the overall fileMenu object to the bar.
    
   } //createFileMenu method

   /**
   The method that creates the FileMenu in the GUI.
    */

   public class FileMenuHandler implements ActionListener { //A action listener to respond to the client's request.
     JFrame dateFrame; //new dateFrame

        public FileMenuHandler (JFrame jf) { //The constructor for the class which accepts a JFrame jf, which is set equal to the frame created.
            dateFrame = jf;
        }//FileMenuHandler

        public void actionPerformed(ActionEvent event) { 
            String  menuName;
            menuName = event.getActionCommand(); //Waits for the action
            if (menuName.equals("Open")) 
                openFile( );  //Calls the method...
            else if (menuName.equals("Quit")) //If Quit is selected then program is exited.
                System.exit(0);
        } //actionPerformed method

   /**
   This method listens for the event and includes the "openFile" and "readSource" methods.
    */

    private void openFile() {
       JFileChooser chooser;
       int status; 

        chooser = new JFileChooser(); //GUI for choosing a file.
        status = chooser.showOpenDialog(null); 
      
            if (status == JFileChooser.APPROVE_OPTION) //If file successfully is selected then call "readSource method" with the file chosen.
                readSource(chooser.getSelectedFile()); 
            else 
                JOptionPane.showMessageDialog(null, "Open File dialog canceled");
    } //openFile method

     /**
   This method takes care of the opening the file.
    */

    private void readSource(File chosenFile) {  
       String chosenFileName = chosenFile.getAbsolutePath(); //Store the fileName as a string. **"getAbsolutePath" to get the full location of the inputted file.
    //    System.out.println(chosenFileName);
       unsortedList = new TextArea();
       sortedList = new TextArea();
       myContentPane = dateFrame.getContentPane();

            myContentPane.add(unsortedList);
            myContentPane.add(sortedList);
            //Adding of the textAreas to the contentPane.
    
        SDL = new SortedDateList();
        UDL = new UnsortedDateList();
        //Finish initalizing the instances. 

        // System.out.println(countNumberDates(fileName));// Counts the number of dates within the file.
        inFile = new TextFileInput(chosenFileName);
        line = inFile.readLine(); //Starts the process of reading the file.
       
        // System.out.println(line);

        
        while (line != null) {//While there is a line.

            // System.out.println("The input line is " + line);

            myTokens = new StringTokenizer(line, ",");
            // System.out.println("There are " + myTokens.countTokens() + " tokens in the line.");
           
            
            while (myTokens.hasMoreTokens()) { //While there is more tokens on the line.
                
                String[] hi = new String [myTokens.countTokens()];
                
                for(int i =0; i< hi.length; i++){
                    // countDates++; //Everytime the index increases this keeps count!
                    // System.out.println(countDates);
                    hi[i]= myTokens.nextToken(); //hi array stores the value.
                    // System.out.println(hi[i] + "alla");
                    date= new Date212(hi[i]); //Calls the date method on the current array index.
                    //  System.out.println("THE DATA" + date);
                     UDL.add(date);  
                     SDL.add(date);
                     //Calls the "add" method on their respective classes
                   
                    //  System.out.println(UDL + "HERE IS THE UDL");
                }
            }

            line = inFile.readLine(); //Read next line.
        }

            //  System.out.println(countDates+ " Dates were passed in!"); //Ignore
             inFile.close(); // FileInput closed
        
        unsortedList.append(UDL.toString() + "\n"); 
        sortedList.append(SDL.toString() + "\n");
        //appending to the contentPane the UDL and SDL using the toString methods from the DateList, called because it extends DateList.

        dateFrame.setVisible(true); //to set it visible again.
      
      
    System.out.println(chosenFileName + "has been read"); 


        }//readSource method
        /**
   This method reads the file using fileInput and String Tokenizer; Appends onto the linked list and onto the contentPanes.
    */

   
    }//FileMenuHandler method

}//Class DateGUI 
