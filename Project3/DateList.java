public abstract class DateList { //Abstract so the class does not have to be instansiated and classes can be used.

	//First node in linked list is a dummy DateNode,hence the "null"
	private DateNode first = new DateNode(null);

	//Last node in linked list is the first when intialized
	private DateNode last = first; 

	/** Number of data items in the list. */
	private int length = 0; 

	public int getLength() { //To obtain the length
		return length;
	}
	
	public void append(Date212 d) { //adds to the end

		DateNode n = new DateNode(d); //Create a new DateNode "n" using the parameter d.
		last.next = n; //the old last.next now points to the new list node "n". An object on the variable itself.
		last = n; //last is set equal to the listnode n, so it LAST NOW!
		length++; //increment length by 1 
	} // method append(Date212)

	public void sort(Date212 n){ //for the SortedDateList
		DateNode temp = new DateNode(n);//A new DateNode called temfirstto hold in the incoming Date212 objects
        DateNode first= this.first; //This refers to the instance of temp
        
            while (first.next != null && first.next.data.compareTo(temp.data)<0){ //While there is something after the first node and the date within the first node when compared to the temp comes before it
                  first= first.next; //First is now whatever first.next points to
            }
            //if it is not the case of the while loop 
				temp.next = first.next; //temp.next(next because temp is null when instansiated ) holds the value of first.next
				first.next = temp; // first.next holds the value of the temp
            
			if(temp.next == null){ //if there is nothing after the temp value...
                this.last=temp; //make the last point to the temp
            }
	}//sort methods

	public String toString() { //For the GUI!
		DateNode p = first.next;
		String returnString = "";
		while (p != null) {
			returnString += p.data + " " + "\n"; 
			// ** "\n" ,This is why when appended in the GUI, the dates appended on different lines.
			p = p.next;
		}
		return returnString;
	}//toString method

} // class DateList

