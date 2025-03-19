import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    private char[][] maze;
    private int rows, cols;
    private int startX, startY;
    private static final char WALL = '@';
    private static final char OPEN = '.';
    private static final char PATH = '+';
    private static final char START = 'W';
    private static final char END = '$';

    public Maze(char[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
        findStart();
    }

    private void findStart() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == START) {
                    startX = i;
                    startY = j;
                    return;
                }
            }
        }
    }

    public void findPath() {
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        int[][][] parent = new int[rows][cols][2]; 

        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            if (maze[x][y] == END) {
                markPath(parent, x, y);
                return;
            }

            for (int[] dir : directions) {
                int newX = x + dir[0], newY = y + dir[1];
                if (isValidMove(newX, newY, visited)) {
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                    parent[newX][newY] = new int[]{x, y}; // Store parent
                }
            }
        }
    }

    private void markPath(int[][][] parent, int endX, int endY) {
        int x = endX, y = endY;
        while (maze[x][y] != START) {
            int px = parent[x][y][0];
            int py = parent[x][y][1];

            if (maze[x][y] != END) {
                maze[x][y] = PATH;
            }

            x = px;
            y = py;
        }
    }

    private boolean isValidMove(int x, int y, boolean[][] visited) {
        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] != WALL && !visited[x][y];
    }

    public void printMaze() {
        for (char[] row : maze) {
            System.out.println(new String(row));
        }
    }
}


