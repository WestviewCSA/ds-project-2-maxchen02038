import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Maze {
    private List<Tile> tiles;
    private int numRows, numCols;
    private int playerRow, playerCol;

    public Maze(String fileName) {
        tiles = new ArrayList<>();
        loadMaze(fileName);
    }

    private void loadMaze(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            numRows = scanner.nextInt();
            numCols = scanner.nextInt();
            scanner.nextLine(); // Move to the next line

            for (int row = 0; row < numRows; row++) {
                String line = scanner.nextLine();
                for (int col = 0; col < numCols; col++) {
                    char type = line.charAt(col);
                    tiles.add(new Tile(row, col, type));

                    if (type == 'P') { // Player's starting position
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
                    System.out.print('P'); // Show player position
                } else {
                    System.out.print(getTile(row, col).getType());
                }
            }
            System.out.println();
        }
    }

    public boolean movePlayer(char direction) {
        int newRow = playerRow;
        int newCol = playerCol;

        switch (direction) {
            case 'W': newRow--; break; // Move up
            case 'S': newRow++; break; // Move down
            case 'A': newCol--; break; // Move left
            case 'D': newCol++; break; // Move right
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
        Tile tile = getTile(row, col);
        return tile != null && tile.getType() != '#';
    }

    private Tile getTile(int row, int col) {
        for (Tile tile : tiles) {
            if (tile.getRow() == row && tile.getCol() == col) {
                return tile;
            }
        }
        return null;
    }

    public boolean hasReachedGoal() {
        Tile tile = getTile(playerRow, playerCol);
        return tile != null && tile.getType() == 'G';
    }
}