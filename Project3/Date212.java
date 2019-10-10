
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

    /**
     * All the variables declared. LINKED-LIST TYPE DATE212 class, so the useless portion is commented out for ease.
     */
    
public Date212 (String s){ //initial constructor
        String valDWithoutSpace = s.replaceAll("\\s", "");
            
            if(valDWithoutSpace.length() != 8) throw new Date212Exception("Your input " + s + " is not 8 digits.");
            //So here I accept the string s and I remove the whitespaces; Thrown an exception if the length is not 8 characters.
        full_Date = s; //for purposes of preserving the original string
        int daysInMonth;
        Boolean leapYear = true;
        int tMonth = Integer.parseInt(s.substring(4,6));
        //Pull off month, but in a temp value.

    if(!((tMonth) >0 && (tMonth) < 13)) throw new Date212Exception("Month " + tMonth + " is not an actual month.");

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// sdf.setLenient(false);

            int tYear = Integer.parseInt(s.substring(0,4)); 
            int tDay = Integer.parseInt(s.substring(6,8));

			// Date date = sdf.parse(valD);
			// System.out.println(date);
                if (tMonth == 4 || tMonth == 6 || tMonth == 9 || tMonth == 11) 
                    daysInMonth = 30;
                else if (tMonth == 2) 
                    daysInMonth = (leapYear) ? 29 : 28;
                else if (tMonth == 1 || tMonth == 3 || tMonth == 5 || tMonth == 7 || tMonth == 8|| tMonth == 10 || tMonth == 12) 
                    daysInMonth = 31;
                else throw new Date212Exception("The date you entered " + Day +"for the month " + Month + " does not exist");

             Year = Integer.parseInt(s.substring(0,4)); 
             Month = Integer.parseInt(s.substring(4,6));
             Day = Integer.parseInt(s.substring(6,8));
                //ONCE it passes all the error checks, then we set Y,M, and D equal to whatever.

    }//public date212 
    /*
    The constructor which takes in dates from a file and front the insert menu. 
    The first if statement checks to see if it is 8 digits. 
    The second if statement checks to see if it is between 1 and 12, inclusive.
    Then if all checks out, Year, Month, and Day are are all assigned from the given string.
    The if statements after that check to see what month it is, and based on that it checks to see if the day exists for that month.
    All these if statements throw an customized error if it does not fit the terms.

    *** The commented out SDF method works***
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
        
}//toString method
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
            // System.out.println("Date1 is equal Date2");
            result = 0;
        }
        return result; //Returns what the if blocks made result to be.
    }//compareTo method

}//Date212 class


