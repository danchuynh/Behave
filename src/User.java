//super class for all users
//abstract functions that all subclasses will utilize in their own way

public abstract class User implements java.io.Serializable{
	String name;
	public abstract void viewState();
	public abstract void redeemIncentive();
	public abstract void drawChart();
}
