import java.util.ArrayList;

public class Mode implements java.io.Serializable{
	String modeName;
	int seconds = 0;
	ArrayList<Incentive> incentiveSet = new ArrayList<Incentive>();
	ArrayList<Token> tokenList = new ArrayList<Token>();
	
	public void scheduleTask(int seconds){
		this.seconds = seconds;
		Scheduler schedule = new Scheduler();
		schedule.setArray(this.tokenList);
		schedule.start(seconds);
	}
}
