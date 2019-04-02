import java.util.Scanner;
import java.io.*;

public class Application {
	static Parent parent = new Parent();
	static Child child = new Child();
	
	static int checkValid(String login){
		int childIndex;
		
		for(childIndex = 0; childIndex < parent.childSet.size(); childIndex++){
			if(parent.childSet.get(childIndex).name.equals(login)){
				return childIndex;
			}
		}
		return -1;
	}
	
	static void save(){
		try {
			FileOutputStream out = new FileOutputStream("behave.txt");
			ObjectOutputStream oout = new ObjectOutputStream(out);
			
			oout.writeObject(parent);
			oout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static void loadSave(){	//loads save file if found
		File in = new File("behave.txt");
		if(in.exists()){
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("behave.txt"));
				
				parent = (Parent) ois.readObject();
				ois.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		boolean run = true;
		Scanner scanner = new Scanner(System.in);
		loadSave();
		while(run){
			
			System.out.println("Enter 'exit' to quit.");
			System.out.print("login: ");
			
	    	String login = scanner.next();
	    	
	    	if(login.equals("parent")){
	    		parent.parentLoop();
	    	}else if(login.equals("exit")){
	    		System.out.println("exiting");
	    		save();
	    		scanner.close();
	    		System.exit(1);
	    	}else{
	    		int childIndex = checkValid(login);
	    		if(childIndex != -1){
	    			child.childLoop(parent.childSet, childIndex);
	    		}else{
		    		System.out.println("Not a valid user.");
	    		}
	    	}
		}
	}

}
