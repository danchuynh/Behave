import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

//scheduling system to automize token distribution

public class Scheduler {
	Toolkit toolkit;
	Timer timer;

	private ArrayList<Token> refArray;
	
	public void setArray(ArrayList<Token> refArray){	//passes array for access
		this.refArray = refArray;
	}
	
	public void setTimer(int seconds){		//timer was set to seconds for class testing purposes
		toolkit = Toolkit.getDefaultToolkit();
		timer = new Timer();
		timer.scheduleAtFixedRate(new RemindTask(), seconds * 1000, seconds * 1000);	//can change seconds
	}
	
	class RemindTask extends TimerTask {	//this runs the tasks at timer interval. creates and adds new token to array
		public void run() {
			Token newToken = new Token();
			newToken.cal = Calendar.getInstance();
			newToken.note = "Scheduled Token";
			refArray.add(newToken);
		}
	}
	
	public void start(int seconds){			//function that is called to initiate scheduling
		System.out.println("Scheduling task.");
		setTimer(seconds);
		System.out.println("Token timer scheduled.");
	}
}
