import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;;

//token is the point system of the program
//children can get tokens added or subtracted

public class Token implements java.io.Serializable{
	Calendar cal = Calendar.getInstance();
	String note;	//each token gets a note as to why a token was given
	
	public void viewInfo(){	//prints information about token
		System.out.println("Date: " + cal.getTime());
		System.out.println("Note: " + note);
		System.out.println();
	}
}
