import java.util.ArrayList;
import java.util.Scanner;

public class Child extends User{
	ArrayList<Mode> Modes = new ArrayList<Mode>();
	static Scanner scanner = new Scanner(System.in);
	
	public void childLoop(ArrayList<Child> childList, int childIndex){
		boolean loop = true;

		while(loop){
			System.out.println("Please select a number:");
			System.out.println("1. View State\n2. View History\n3. Redeem Incentive\n0. Exit");
			int option = scanner.nextInt();
			
			switch(option){
			case 1:
				this.viewChild(childList, childIndex);
				break;
			case 2:
				this.drawChart(childList, childIndex);
				break;
			case 3:
				this.redeemIncentive(childList, childIndex);
				break;
			case 0:
				loop = false;
				break;
			default:
				System.out.println("Not a valid option.");
			}
		}
	}
	
	public void viewState(){
		System.out.println("Name: " + this.name);
		System.out.println("Mode: " + this.Modes.get(0).modeName + "");
		System.out.println("Tokens: " + this.Modes.get(0).tokenList.size() + "\n");
	}
	
	private void viewChild(ArrayList<Child> childList, int childIndex){
		System.out.println("Name: " + childList.get(childIndex).name);
		System.out.println("Mode: " + childList.get(childIndex).Modes.get(0).modeName + "\n");
		for(int j = 0; j < childList.get(childIndex).Modes.get(0).tokenList.size(); j++){
			System.out.println("Token " + (j+1) + ":");
			childList.get(childIndex).Modes.get(0).tokenList.get(j).viewInfo();
		}
	}
	
	public void redeemIncentive(ArrayList<Child> childList, int childIndex){
		System.out.println("Enter 0 to exit.");
		System.out.println("Please select an Incentive.");
		for(int i = 0; i < childList.get(childIndex).Modes.get(0).incentiveSet.size(); i++){
			System.out.println((i+1) + ". " + childList.get(childIndex).Modes.get(0).incentiveSet.get(i).incentiveName);
		}
		
		int option = scanner.nextInt() - 1;
		if(option >= 0){
			if(childList.get(childIndex).Modes.get(0).tokenList.size() < childList.get(childIndex).Modes.get(0).incentiveSet.get(option).tokenCost){
				System.out.println("Not enough tokens.");
			}else{
				for(int cost = 0; cost < childList.get(childIndex).Modes.get(0).incentiveSet.get(option).tokenCost; cost ++){
					childList.get(childIndex).Modes.get(0).tokenList.remove(cost);
				}
				System.out.println("Incentive redeemed.");
			}
		}
	}
	
	public void redeemIncentive(){
		
	}
	
	public void drawChart(ArrayList<Child> childList, int childIndex){
		Chart chart = new Chart(childList.get(childIndex));
		chart.drawChart();
	}
	
	public void drawChart(){
		Chart chart = new Chart(this);
		chart.drawChart();
	}
}
