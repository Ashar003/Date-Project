
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.DateFormat;
import java.util.Calendar;


public class Date212 {
    private int Month;
    private int Year;
    private int Day;
    private String full_Date; //the String s is stored in full_Date;
    public static int count = 0; //To keep track of how many Dates are made.
    private static Date212 myTempDates[] = new Date212[100]; //Used to store in 100 dates
    private static Date212 myDates[]; //The dynamic array which uses the number of dates passed in.
    public static int i = 0; //Used for AddArray method.

    /**
     * All the variables declared. 
     */
    
public Date212 (String s){ //initial constructor
        full_Date = s;
        // System.out.println(full_Date  + "jdjdj");
        if (!isValidDate(full_Date)) throw new IllegalArgumentException("Invalid Date Format!"); 
        //If it is not true that the date is valid then throw a error.
        /*  
            The substring methods to pull out the Year, Month, and Day.
        */
        Year = Integer.parseInt(full_Date.substring(0,3)); 
        Month = Integer.parseInt(full_Date.substring(4,5));
        Day = Integer.parseInt(full_Date.substring(6,7));
        count++; 
        addArray(this); //Calling the function "addArray" with 'this isntance of date212'
        //  System.out.println(myDates[i]);

    }
    public void addArray(Date212 object){
        myTempDates[i++] = object; //Add the object passed in to the array using the 'i'.
    }

    /**
     * The "addArray" method takes in the Date212 object which is called from the constructor.
     */

    public Date212[] getNumbDates(int a){ //a is the number that signfies the dates passed in.
        // System.out.println(a+ "a is");
        Date212 myDates[] = new Date212[a]; //initalize it from the a.

        for(int d = 0; d< myTempDates.length; d++){ //that "myTempDates" array which holds the dates is used to copy it into the specified array.
            if(myTempDates[d] != null){ //if next index is not null then copy.
                myDates[d] = myTempDates[d];
            }
        }
        return myDates; //return the array to method calling it!
    }

    /**
     * Returns the array and requires the numberofDates Param; DateGUI calls it.
     */

public void setString (String s) { //set method
    full_Date = s;
    if(!isValidDate(full_Date)) throw new IllegalArgumentException("Invalid Date Format!");

        Year = Integer.parseInt(full_Date.substring(0,3));
        Month = Integer.parseInt(full_Date.substring(4,5));
        Day = Integer.parseInt(full_Date.substring(6,7));

    
}

public String getDate() { //getMethod
     return full_Date;
    }
//Returns the full_Date (raw).





public boolean equals(Date212 other) { //equals
    return( other != null
    && getClass() == other.getClass()
    && Year ==((Date212) other).Year
    && Day ==((Date212) other).Day
    && Month ==((Date212) other).Month);
}

/**
 * This "equals" method, checks the class, year, day, and month. 
 */

public boolean isValidDate(String valD) { //Checks if date is in proper format
        String valDWithoutSpace = valD.replaceAll("\\s", "");
    return valDWithoutSpace.length() == 8;
}
/**
 * Basically, the string passed in is checked for whitespaces and is removed. Then it is checked by length.
 */


 
public String toString() { //toString method
    String startDateString2= ""; //Initialize
    try{
        Date date1 = new SimpleDateFormat("yyyyMMdd").parse(full_Date);
            DateFormat df2 = new SimpleDateFormat("MMM dd, yyyy");
            startDateString2 = df2.format(date1);
           
        return startDateString2;
    }catch(ParseException e){
        e.printStackTrace();
    }
            return startDateString2;
        
}
/**
 * The "try-catch block is needed for dates."
 * The "full_Date" is parsed in the format it is in, then given a new format using "SImpleDateFormat"
 * 
 */


public int compareTo(Date212 other) { //compareTo method
    int result = 00; //intialize int return value.
    Date date1 = new Date(), date2 = new Date(); //initalize

SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            date1 = sdf.parse(full_Date);  //The object calling this method.
            date2 = sdf.parse(other.full_Date); //Other date212 object being passed in.

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Try-catch block for parsing & formatting dates.
       
        // System.out.println("date1 : " + sdf.format(date1));
        // System.out.println("date2 : " + sdf.format(date2));

        Calendar cal1 = Calendar.getInstance(); 
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        /*
        Use of the Calendar library and methods to compare the dates.
        */


        //cal1 is the object calling this method
        //cal2 is the object passed in.
        if (cal1.after(cal2)) { 
            // System.out.println("Date1 is after Date2");
            result = 1;
        }

        if (cal1.before(cal2)) {
            // System.out.println("Date1 is before Date2");
            result = -1;
        }

        if (cal1.equals(cal2)) {
            System.out.println("Date1 is equal Date2");
            result = 0;
        }
        return result; //Returns what the if blocks made result to be.
    }


}


