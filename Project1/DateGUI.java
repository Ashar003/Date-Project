import javax.swing.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import javax.swing.JFrame;
import java.awt.*;
import java.util.StringTokenizer;

public class DateGUI{
    public static StringTokenizer myTokens;
    public static StringTokenizer numbDates;
    public static String line;
    public static TextFileInput inFile;
    public static TextFileInput preProgram;
    public static TextArea Raw = new TextArea();
    public static TextArea Dates = new TextArea();
    public String fileName = "dates.txt";
    public static JFrame myDateGUI;
    public static Container cPane;
    private static Date212 date;
    private Date212[] array;
    private Date212 temp;
    private int countDates;

    /**
     * All the variables that are being used.
     */

    public void main(String args[]){
        //nothing here :)
     }//main



public void initialize() {

    myDateGUI= new JFrame();
    myDateGUI.setSize(400,200);
    myDateGUI.setLocation(100,100);
    myDateGUI.setTitle("Dates"); 
    myDateGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myDateGUI.setLayout(new GridLayout(1, 2));
    myDateGUI.setVisible(true);
        cPane = myDateGUI.getContentPane();
        cPane.add(Raw);
        cPane.add(Dates);
        readNumbersFromFile(fileName);
        selectionSort();
}//intialize

/**
 * When Project2 kicks off "initilaize, this is what is run, initialize!"
 */

    private void selectionSort() {
        // System.out.println(array.length +"jfjfjfj");
    
      for ( int i = 0; i < array.length - 1; i++ ) { 
         int indexLowest = i;  //Holds the current i value.
         for ( int j = i + 1; j < array.length; j++ ) 
            if ( array[j].compareTo(array[indexLowest]) == -1) //if the value after index i's value(j) is less than set IndexLowest to the value of j; "-1" means it is less!
            //compareTo method from Date212.
               indexLowest = j;
        //  if ( array[indexLowest] != array[i] ) { //if the index that holds smallest is not equal to the current value of i then... using the array compare!
            if(!(array[indexLowest]).equals(array[i])){ //if the index that holds smallest is not equal to the current value of i then... USING '.equals' method on DATE212
            temp = array[indexLowest]; //Date212 temp holds the smallest value.
            array[indexLowest] = array[i];  //values shifted.
            array[i] = temp; //current i holds lowest value. 
         }  
      } 
      //For loop to append to the GUI.
        for (int k = 0; k < array.length ; k++) {
            // System.out.println(array.length +"jdj" + array[k] + "jshfjh");
            Dates.append(String.valueOf(array[k])); //TO get the the string reperesnetation of the dates!
            Dates.append("\n"); //new line after each index.
        }   
    } // method selectionSort 

    /**
     * Using the 'compareTo' method of Date212, the selectionSort is run. '
     */

     
    // public int countNumberDates(String fileName){
    //     inFile = new TextFileInput(fileName);
    //     line = inFile.readLine(); 

    //     while(line != null){ 
    //         numbDates = new StringTokenizer(line, ",");
        
    //         while(numbDates.hasMoreTokens()){
    //             countDates++;
    //             numbDates.nextToken();
    //         }
    //         line = inFile.readLine();
    //     }
    //     inFile.close();
    //     return countDates;
    // }
    /**
     * The method above, countNumberDates, counts the dates in the file before the actual reading, but I decided to count the number of dates within the actual reading dates method.
     */


    public void readNumbersFromFile(String fileName) {

        // System.out.println(countNumberDates(fileName));// Counts the number of dates within the file.
        inFile = new TextFileInput(fileName);
        line = inFile.readLine(); //Starts the process of reading the file.
        // array = new Date212[6]; //New Date212 array with capacity of 6 for the 6 dates.
        // System.out.println(line);

        
        while (line != null) {//While there is a line.
            

            // System.out.println("The input line is " + line);

            myTokens = new StringTokenizer(line, ",");
            // System.out.println("There are " + myTokens.countTokens() + " tokens in the line.");
           
            
            while (myTokens.hasMoreTokens()) { //While there is more tokens on the line.
                
                String[] hi = new String [myTokens.countTokens()];
                
                for(int i =0; i< hi.length; i++){
                    countDates++; //Everytime the index increases this keeps count!
                    // System.out.println(countDates);
                    hi[i]= myTokens.nextToken(); //hi array stores the value.
                    // System.out.println(hi[i] + "alla");
                    date= new Date212(hi[i]); //Calls the date method on the current array index.
                    // System.out.println(date + "kjfkj");
                
                    if(i>0){ //If there is more than one token, then read and append new line.
                        Raw.append("\n"); //Append new line
                        Raw.append(hi[i]); //Append the current index.
                       
                    }
                    else if(i == 0){ //If there is only one token.
                    Raw.append(hi[i]);
                    }
                }

            }
            Raw.append("\n"); //New line after every array index finishes.

            line = inFile.readLine(); //Read next line.
           
        }
           
      
             System.out.println(countDates+ " Dates were passed in!"); //Ignore
            array = date.getNumbDates(countDates); // "array" points to the array provided by the method getNumbDates with a parameter of countDates.
            //countDates holds the number of dates passed in.
            inFile.close(); // FileInput closed
        

        myDateGUI.setVisible(true); //to set it visible again.
        
    }//readNumbersFromFile 


}//DateGUI


/**
 * Ignore all the system.out.printlns'!
 */