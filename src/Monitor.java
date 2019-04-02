import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public abstract class Monitor extends User{
	ArrayList<Mode> Modes = new ArrayList<Mode>();
	ArrayList<Child> childSet = new ArrayList<Child>();
	static Scanner scanner = new Scanner(System.in);
	
	public int selectChildIndex(){
		System.out.println("Enter 0 to exit.");
		System.out.println("Please select a child.");
		for(int count = 0; count < this.childSet.size(); count++){
			System.out.println((count+1) + ". " + this.childSet.get(count).name);
		}
		
		int indexOfChild = scanner.nextInt();
		
		if(indexOfChild == 0){
			return -1;
		}else{
			return indexOfChild-1;
		}
	}
	
	public int addChild(Monitor user){
		if(this.Modes.isEmpty()){
			System.out.println("Please create a mode first.");
		}else{
			System.out.println("Enter name: ");
			String name = scanner.next();
			Child child = new Child();
			child.name = name;
			for(int i = 0; i < Modes.size(); i++){
				Mode newMode = new Mode();
				newMode.modeName = Modes.get(i).modeName;
				child.Modes.add(newMode);
			}
			this.childSet.add(child);
			return this.childSet.size()-1;
		}
		
		return -1;
	}
	
	private void setName(int indexOfChild){
		System.out.println("Enter name: ");
		String name = scanner.next();
		this.childSet.get(indexOfChild).name = name;
		System.out.println("Name change complete.");
	}
	
	public void editChild(){
		//change name, change mode
		boolean editChildLoop = true;		
		int indexOfChild = selectChildIndex();
		
		if(indexOfChild != -1){
			while(editChildLoop){
				System.out.println("Enter 0 to exit.");
				System.out.println("Please select an option.");
				System.out.println("1. Set Name\n2. Set Mode\n3. Set Scheduler\n0. Exit");
				int option = scanner.nextInt();
				
				switch (option){
					case 1:
						setName(indexOfChild);
						break;
					case 2:
						setMode(indexOfChild);
						break;
					case 3:
						setScheduler(indexOfChild);
						break;
					case 0:
						editChildLoop = false;
						break;
					default:
						System.out.println("Not a valid option.");
				}
			}
		}
		
	}
	
	public void removeChild(){
		int option = selectChildIndex();
		
		if(option != -1){
			this.childSet.remove(option);
		}
	}
	
	public void viewState(){
		
		if(this.childSet.isEmpty()){
			System.out.println("There are no children.");
		}else{
			System.out.println("Printing list of children:");
			for(int i = 0; i < this.childSet.size(); i++){
				this.childSet.get(i).viewState();
			}
		}
	}
	
	public void viewChild(){
		int indexOfChild = selectChildIndex();
		
		if(indexOfChild != -1){
			System.out.println("Name: " + this.childSet.get(indexOfChild).name);
			System.out.println("Mode: " + this.childSet.get(indexOfChild).Modes.get(0).modeName + "\n");
			for(int i = 0; i < this.childSet.get(indexOfChild).Modes.get(0).tokenList.size(); i++){
				System.out.println("Token " + (i+1) + ":");
				this.childSet.get(indexOfChild).Modes.get(0).tokenList.get(i).viewInfo();
			}
		}
	}
	
	public void addMode(){
		Mode newMode = new Mode();
		System.out.print("Enter name of mode: ");
		newMode.modeName = scanner.next();
		this.Modes.add(newMode);
		
		for(int i = 0; i < this.childSet.size(); i++){
			this.childSet.get(i).Modes.add(newMode);
		}
	}
	
	public void removeMode(){
		System.out.println("This change will affect all children.");
		int indexOfMode = selectModeIndex();
				
		if(indexOfMode != -1){
			if(this.childSet.isEmpty()){
				if(indexOfMode == 0 && this.Modes.size() != 1){
					Collections.swap(this.Modes, 0, 1);
					this.Modes.remove(1);
				}else{
					this.Modes.remove(indexOfMode);
				}
			}else{
				for(int i = 0; i < this.childSet.size(); i++){
					for(int j = 0; j < this.Modes.size(); j++){
						if(this.Modes.get(indexOfMode).modeName.equals(this.childSet.get(i).Modes.get(j).modeName)){
							if(this.Modes.size() == 1){
								System.out.println("Can not remove last mode if children are present.");
							}else if(j == 0){
								Collections.swap(this.childSet.get(i).Modes, 0, 1);
								this.childSet.get(i).Modes.remove(1);
								break;
							}else{
								this.childSet.get(i).Modes.remove(j);
								break;
							}
						}
					}
				}
				if(this.Modes.size() != 1){
					if(indexOfMode == 0 && this.Modes.size() != 1){
						Collections.swap(this.Modes, 0, 1);
						this.Modes.remove(1);
					}else{
						this.Modes.remove(indexOfMode);
					}
				}
			}
		}
	}
	
//	public void setModeName(int indexOfMode){
//		System.out.println("Enter mode name: ");
//		String name = scanner.next();
//		
//		for(int i = 0; i < this.childSet.size(); i++){
//			for(int j = 0; j < this.Modes.size(); j++){
//				if(this.Modes.get(indexOfMode).modeName.equals(this.childSet.get(i).Modes.get(j).modeName)){
//					this.childSet.get(i).Modes.get(j).modeName = name;
//				}
//			}
//		}
//		
//		System.out.println("Mode name change complete."); 
//	}
	
	public int selectModeIndex(){
		System.out.println("Enter 0 to exit.");
		System.out.println("Please select a mode.");
		for(int count = 0; count < this.Modes.size(); count++){
			System.out.println((count+1) + ". " + this.Modes.get(count).modeName);
		}
		
		int indexOfMode = scanner.nextInt();
		
		if(indexOfMode == 0){
			return -1;
		}else{
			return indexOfMode-1;
		}
	}	
	
	public void setScheduler(int indexOfChild){
		int indexOfMode = selectModeIndex();
		if(indexOfMode != -1){
			System.out.println("Please enter time(in seconds): ");
			int seconds = scanner.nextInt();
			
			for(int j = 0; j < this.Modes.size(); j++){
				if(this.Modes.get(indexOfMode).modeName.equals(this.childSet.get(indexOfChild).Modes.get(j).modeName)){
					this.childSet.get(indexOfChild).Modes.get(j).scheduleTask(seconds);
				}
			}
		}
		
	}
	
//	public void editMode(){
//		boolean editModeLoop = true;
//		int indexOfMode = selectModeIndex();
//		
//		if(indexOfMode != -1){
//			while(editModeLoop){
//				System.out.println("Enter 0 to exit.");
//				System.out.println("Please select an option.");
//				System.out.println("1. Set Mode Name\n2. Set Scheduler Timer in hours\n0. Exit");
//				int option = scanner.nextInt();
//				
//				switch (option){
//					case 1:
//						setModeName(indexOfMode);
//						break;
//					case 2:
//						setSchedulerTimer(indexOfMode);
//						break;
//					case 0:
//						editModeLoop = false;
//						break;
//					default:
//						System.out.println("Not a valid option.");
//				}
//			}
//		}
//	}
	
//	public void setMode(int indexOfChild){
//		int modeIndex = 0;
//		System.out.println("Please select a mode.");
//		
//		for(modeIndex = 0; modeIndex < this.childSet.get(indexOfChild).Modes.size(); modeIndex++){
//			System.out.println(modeIndex + 1 + ". " + this.childSet.get(indexOfChild).Modes.get(modeIndex).modeName);
//		}  
//		
//		int option = scanner.nextInt();
//		if(this.childSet.get(indexOfChild).Modes.get(0).modeName.equals(this.childSet.get(indexOfChild).Modes.get(option-1).modeName)){
//			//do nothing
//		}else{
//			Collections.swap(this.childSet.get(indexOfChild).Modes, 0, option-1);
//		}
//	}
	
	public void setMode(int indexOfChild){
		int modeIndex = selectModeIndex();
		
		if(this.childSet.get(indexOfChild).Modes.get(0).modeName.equals(this.Modes.get(modeIndex).modeName)){
			//do nothing
		}else{
			Collections.swap(this.childSet.get(indexOfChild).Modes, 0, modeIndex);
		}
	}

	public void setMode(){
		int indexOfChild = selectChildIndex();
		if(indexOfChild != -1){
			setMode(indexOfChild);
		}
	}
	
	public void addToken(){
		int indexOfChild = selectChildIndex();
		
		if(indexOfChild != -1){
			Token newToken = new Token();
			System.out.println("Enter a note for this token: ");
			String note = scanner.next();
			newToken.note = note;
			this.childSet.get(indexOfChild).Modes.get(0).tokenList.add(newToken);
		}
		
	}
	
	public void removeToken(){
		int indexOfChild = selectChildIndex();
		
		if(indexOfChild != -1){
			System.out.println("Select a token to remove.");
			int indexOfToken;
			for(indexOfToken = 0; indexOfToken < this.childSet.get(indexOfChild).Modes.get(0).tokenList.size(); indexOfToken++){
				System.out.print(indexOfToken + 1 + ". ");
				this.childSet.get(indexOfChild).Modes.get(0).tokenList.get(indexOfToken).viewInfo();
			}
			
			indexOfToken = scanner.nextInt()-1;
			this.childSet.get(indexOfChild).Modes.get(0).tokenList.remove(indexOfToken);
		}
	}
	
	void createIncentive(){
		int indexOfMode = selectModeIndex();
		if(indexOfMode != -1){
			System.out.println("Enter name of incentive.");
			String name = scanner.next();
			scanner.nextLine();
			System.out.println("Enter cost of incentive.");
			int cost = scanner.nextInt();
			Incentive newIncentive = new Incentive();
			newIncentive.incentiveName = name;
			newIncentive.tokenCost = cost;
			
			this.Modes.get(indexOfMode).incentiveSet.add(newIncentive);
			
			for(int i = 0; i < this.childSet.size(); i++){
				for(int j = 0; j < this.Modes.size(); j++){
					if(this.childSet.get(i).Modes.get(j).modeName.equals(this.Modes.get(indexOfMode).modeName)){
						this.childSet.get(i).Modes.get(j).incentiveSet.add(newIncentive);
					}
				}
				
			}
		}
		
	}
	
	public int selectIncentiveIndex(int indexOfMode){
		System.out.println("Enter 0 to exit.");
		System.out.println("Please select an Incentive.");
		for(int count = 0; count < this.Modes.get(indexOfMode).incentiveSet.size(); count++){
			System.out.println((count+1) + ". " + this.Modes.get(indexOfMode).incentiveSet.get(count).incentiveName);
		}
		
		int indexOfIncentive = scanner.nextInt();
		
		if(indexOfIncentive == 0){
			return -1;
		}else{
			return indexOfIncentive-1;
		}
	}
	
	void removeIncentive(){
		int indexOfMode = selectModeIndex();
		if(indexOfMode != -1){
			int indexOfIncentive = selectIncentiveIndex(indexOfMode);
			if(indexOfIncentive != -1){
				for(int i = 0; i < this.childSet.size(); i++){
					for(int j = 0; j < this.Modes.size(); j++){
						 if(this.childSet.get(i).Modes.get(j).modeName.equals(this.Modes.get(indexOfMode).modeName)){
							 for(int k = 0; k < this.Modes.get(indexOfMode).incentiveSet.size(); k++){
								 if(this.childSet.get(i).Modes.get(j).incentiveSet.get(k).incentiveName.equals(this.Modes.get(indexOfMode).incentiveSet.get(indexOfIncentive).incentiveName)){
									 this.childSet.get(i).Modes.get(j).incentiveSet.remove(k);
								 }
							 }
						 }
					}
				}
				this.Modes.get(indexOfMode).incentiveSet.remove(indexOfIncentive);
			}
		}
	}
	
	public void redeemIncentive(){
		int indexOfChild = selectChildIndex();
		if(indexOfChild != -1){
			int indexOfMode = selectModeIndex();
			if(indexOfMode != -1){
				int indexOfIncentive = selectIncentiveIndex(indexOfMode);
				if(indexOfIncentive != -1){				
					for(int i = 0; i < this.Modes.size(); i++){
						if(this.childSet.get(indexOfChild).Modes.get(i).modeName.equals(this.Modes.get(indexOfMode).modeName)){
							if(this.childSet.get(indexOfChild).Modes.get(i).tokenList.size() < this.childSet.get(indexOfChild).Modes.get(i).incentiveSet.get(indexOfIncentive).tokenCost){
								System.out.println("Not enough tokens.");
							}else{
								for(int cost = this.childSet.get(indexOfChild).Modes.get(i).incentiveSet.get(indexOfIncentive).tokenCost-1; cost >= 0; cost--){
									this.childSet.get(indexOfChild).Modes.get(i).tokenList.remove(cost);
								}
								System.out.println("Incentive redeemed.");
							}
						}
					}					
				}
			}
		}
		
	}
	
	public void drawChart(){
		int indexOfChild = selectChildIndex();
		if(indexOfChild != -1){
			this.childSet.get(indexOfChild).drawChart();
		}
	}
}
