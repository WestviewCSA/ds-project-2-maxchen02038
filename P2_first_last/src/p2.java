import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {

	  public static void main(String[] args) {
	        System.out.println("p2");
	        readMap("Test01");
	    }

	    public static void readMap(String fileName) {
	        try {
	            File file = new File(fileName);
	            Scanner scanner = new Scanner(file);
	            
	           
	            int numRows = scanner.nextInt();
	            int numCols = scanner.nextInt();
	            int numRooms = scanner.nextInt();
	            scanner.nextLine(); 
	            
	           
	            int rowIndex = 0;
	            while (scanner.hasNextLine()) {
	                String row = scanner.nextLine();
	                
	                if (rowIndex < numRows && row.length() >= numCols) { 
	                    for (int i = 0; i < numCols; i++) {
	                        System.out.print(row.charAt(i));
	                    }
	                    System.out.println();
	                    rowIndex++;
	                }
	            }
	            
	            scanner.close();
	        } catch (FileNotFoundException e) {
	            System.out.println("File not found: " + e.getMessage());
	        }
	    }


	
}


