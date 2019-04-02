import java.util.Scanner;

//subclass of user/monitor
//menu for parent user

public class Parent extends Monitor{
	static Scanner scanner = new Scanner(System.in);
	
	void parentLoop(){
		boolean loop = true;
		
		while(loop){
			System.out.println("Please select a number:");
			System.out.println("1. Add Child\n2. Edit Child\n3. Remove Child\n4. Add Token\n5. Remove Token\n"
					+ "6. View Cildren\n7. View Child\n8. View History\n9. Create Incentive\n10. Remove Incentive\n"
					+ "11. Redeem Incentive\n12. Add Mode\n13. Remove Mode\n0. Exit");
			int option = scanner.nextInt();
			
			switch(option){
				case 1:
					int indexOfChild = this.addChild(this);
					if(indexOfChild != -1){
						this.setMode(indexOfChild);
					}
					break;
				case 2:
					this.editChild();
					break;
				case 3:
					this.removeChild(); 
					break;
				case 4:
					this.addToken();
					break;
				case 5:
					this.removeToken();
					break;
				case 6:
					this.viewState();
					break;
				case 7:
					this.viewChild();
					break;
				case 8:
					this.drawChart();
					break;
				case 9:
					this.createIncentive();
					break;
				case 10:
					this.removeIncentive();
					break;
				case 11:
					this.redeemIncentive();
					break;
				case 12:
					this.addMode();
					break;
				case 13:
					this.removeMode();
					break;
				case 0:
					loop = false;
					break;
				default:
					System.out.println("Not a valid option.");
			}
		}
	}
}
