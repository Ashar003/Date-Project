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
    public SortedDateList SDL = new SortedDateList();
    public UnsortedDateList UDL = new UnsortedDateList();
    public static TextArea unsortedList = new TextArea();
    public static TextArea sortedList = new TextArea();
    public static Container myContentPane;
    JFrame dateFrame;
    private static Date212 rDate;
    private static Date212 iDate;

    /*
        SOme variables are declared here, some are initliazed here to provide global support for the methods.
    */
      

   public DateGUI(String title, int height, int width) {
	    setTitle(title);
	    setSize(height,width); //height and width
        setLocation  (400,200);
        setLayout(new GridLayout(1, 2));
	    createDosMenu();
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
   } //DateGUI constructor.
   /**
   The parameters are passed in from the Project3 file. 
    */

   private void createDosMenu() {
      JMenuItem   item;
      JMenuBar    menuBar  = new JMenuBar();
      JMenu       fileMenu = new JMenu("File"); //Name of the menu in the bar
      JMenu       editMenu = new JMenu("Edit"); 
      EditMenuHandler emh  = new EditMenuHandler(this);
      FileMenuHandler fmh  = new FileMenuHandler(this); //The FileMenuHandler which holds the menu bar options amongst more and passes in DateGUI-"this".

        item = new JMenuItem("Open");    //Open is a menu item.
        item.addActionListener( fmh );
        fileMenu.add( item ); //The process of acutally adding the item to the menu!

      fileMenu.addSeparator();           //add a horizontal separator line between "Open" and "Quit".
    
        item = new JMenuItem("Quit");       //Quit is a menu item.
        item.addActionListener( fmh );
        fileMenu.add( item );
        menuBar.add(fileMenu);

         item = new JMenuItem("Insert");    //Insert is a menu item.
        item.addActionListener( emh ); 
        editMenu.add( item ); //The process of acutally adding the item to the menu!
        menuBar.add(editMenu);
      setJMenuBar(menuBar); //Like the "setVisible", it makes the menuBar visible.

    
   } //CreateDosMenu method
   
   /*
   The creation of the the JMenuBar, 2 JMenus, and 3 JMenuItems. 
   It adds the ActionListeners as well below each JMenuItem.
    */
    
    public class EditMenuHandler implements ActionListener {
         String dateInput;
   
    public EditMenuHandler(JFrame jf) { //Works on the JFrame passed in.
        dateFrame = jf;
    }//EditMenuHandler constructor method
    
    public void actionPerformed(ActionEvent event) {

        String menuName = event.getActionCommand();
            if (menuName.equals("Insert")) dateInput = JOptionPane.showInputDialog(null, "Enter a new date in the 8 digit format (yyyyMMdd), as in the file.");

        inputDate(dateInput);
    } //actionPerformed

     public void inputDate(String d){
       myContentPane = dateFrame.getContentPane();

            myContentPane.add(unsortedList);
            myContentPane.add(sortedList);
            //Adding of the textAreas to the contentPane.
            // SDL = new SortedDateList();
            // UDL = new UnsortedDateList();
        //Finish initalizing the instances. 
                    
            try{ 
                iDate= new Date212(d); 
                UDL.add(iDate);  
                SDL.add(iDate);
                    } 

            catch(Date212Exception e){
                System.out.println(e);
                
                 }
                 
        unsortedList.append(UDL.toString()+ "\n"); 
        sortedList.append(SDL.toString()+ "\n");
        //appending to the contentPane the UDL and SDL using the toString methods from the DateList, called because it extends DateList.

        dateFrame.setVisible(true); //to set it visible again.

        }// inputDate method
}//EditMenuHandler method
/*
            This method reads the input using JOptionPane, and calls the method using the dateInput.  
            Using a try catch block, it see if the date can be added, if not then catch the error and output it
    */


   public class FileMenuHandler implements ActionListener { //A action listener to respond to the client's request.

        public FileMenuHandler (JFrame jf) { //The constructor for the class which accepts a JFrame jf, which is set equal to the frame created.
            dateFrame = jf;
        }//FileMenuHandler constructor method

        public void actionPerformed(ActionEvent event){ 
            String  menuName;

            menuName = event.getActionCommand(); //Waits for the action
                if (menuName.equals("Open")) 
                    openFile( );  //Calls the method...
                else if (menuName.equals("Quit")) //If Quit is selected then program is exited.
                     System.exit(0); //Exits the programs
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
   
       myContentPane = dateFrame.getContentPane();

            myContentPane.add(unsortedList);
            myContentPane.add(sortedList);
            //Adding of the textAreas to the contentPane.

        // SDL = new SortedDateList();
        // UDL = new UnsortedDateList();
        //Finish initalizing the instances. 

       
        inFile = new TextFileInput(chosenFileName);
        line = inFile.readLine(); //Starts the process of reading the file.

        
        while (line != null) {//While there is a line.

                myTokens = new StringTokenizer(line, ",");
            
            while (myTokens.hasMoreTokens()) { //While there is more tokens on the line.
                
                String[] hi = new String [myTokens.countTokens()];
                
                for(int i =0; i< hi.length; i++){
                    
                    try{
                            hi[i]= myTokens.nextToken();
                            rDate= new Date212(hi[i]);
                                UDL.add(rDate);  
                                SDL.add(rDate);
                        }
                    catch(Date212Exception e){
                            System.out.println(e);
                        }
                }
            }
            line = inFile.readLine(); //Read next line.
        }
             inFile.close(); // FileInput closed
        
        unsortedList.append(UDL.toString() + "\n"); 
        sortedList.append(SDL.toString() + "\n");
        //appending to the contentPane the UDL and SDL using the toString methods from the DateList, called because it extends DateList.

        dateFrame.setVisible(true); //to set it visible again.

    System.out.println(chosenFileName + "has been read"); 


        }//readSource method
        /*
            This method reads the file using fileInput and String Tokenizer; Appends onto the linked list and onto the contentPanes.
            Using a try catch block, it see if the date can be added, if not then catch the error and output it
    */
    }//FileMenuHandler method
    
}//Class DateGUI 

