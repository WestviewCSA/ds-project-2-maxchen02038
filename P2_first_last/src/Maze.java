import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {

	private Tile[][] maze;
	private String checkType;
	public void check() {
			   
		  Scanner myObj = new Scanner(System.in); 
		  System.out.println("Is this a Coordinate Y/N");
		    if(myObj.nextLine().equals("Y")) {
	  	    	 checkType = "you suck";
		    }
		  System.out.println( checkType);  
	}
		
		
		
	public char translate() {
		return 0;
	
	}
		
	  
	  
		
	
}
