import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Maze: ");
        String fileName = userInput.nextLine();

        char[][] maze = readMap(fileName);

        if (maze != null) {
            Maze mazeSolver = new Maze(maze);
            mazeSolver.findPathStack();
            mazeSolver.findPathQueue();

            mazeSolver.printMaze();
        }

        userInput.close();
    }

    public static char[][] readMap(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            int numRooms = scanner.nextInt();
            scanner.nextLine();

            char[][] maze = new char[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                String row = scanner.nextLine();
                for (int j = 0; j < numCols; j++) {
                    maze[i][j] = row.charAt(j);
                }
            }

            scanner.close();
            return maze;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return null;
    }
}


