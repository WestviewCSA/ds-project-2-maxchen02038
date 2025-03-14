import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Maze {
    private Tile[][] grid;
    private int numRows, numCols;
    private int playerRow, playerCol;

    public Maze(String fileName) {
        loadMaze(fileName);
    }

    private void loadMaze(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            numRows = scanner.nextInt();
            numCols = scanner.nextInt();
            scanner.nextLine(); // Move to the next line

            grid = new Tile[numRows][numCols];

            for (int row = 0; row < numRows; row++) {
                String line = scanner.nextLine();
                for (int col = 0; col < numCols; col++) {
                    char type = line.charAt(col);
                    grid[row][col] = new Tile(row, col, type);

                    if (type == 'P') { // Set player's initial position
                        playerRow = row;
                        playerCol = col;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        }
    }

    public void displayMaze() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (row == playerRow && col == playerCol) {
                    System.out.print('P'); // Player position
                } else {
                    System.out.print(grid[row][col].getType());
                }
            }
            System.out.println();
        }
    }

    public boolean movePlayer(char direction) {
        int newRow = playerRow;
        int newCol = playerCol;

        switch (direction) {
            case 'W': newRow--; break;
            case 'S': newRow++; break;
            case 'A': newCol--; break;
            case 'D': newCol++; break;
            default: return false;
        }

        if (isValidMove(newRow, newCol)) {
            playerRow = newRow;
            playerCol = newCol;
            return true;
        }
        return false;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && grid[row][col].getType() != '#';
    }

    public boolean hasReachedGoal() {
        return grid[playerRow][playerCol].getType() == 'G';
    }
}