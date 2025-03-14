import java.io.*;
import java.util.*;

class Maze {
    private char[][] grid;
    private int startX, startY;
    private int goalX, goalY;

    public Maze(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        grid = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'W') {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == '$') {
                    goalX = i;
                    goalY = j;
                }
            }
        }
    }
    
    public char[][] getGrid() { return grid; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public int getGoalX() { return goalX; }
    public int getGoalY() { return goalY; }
}

class Solver {
    protected Maze maze;
    protected boolean[][] visited;
    protected int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public Solver(Maze maze) {
        this.maze = maze;
        this.visited = new boolean[maze.getGrid().length][maze.getGrid()[0].length];
    }
}

class BFSSolver extends Solver {
    public BFSSolver(Maze maze) { super(maze); }

    public List<int[]> solve() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{maze.getStartX(), maze.getStartY()});
        visited[maze.getStartX()][maze.getStartY()] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];
            if (x == maze.getGoalX() && y == maze.getGoalY()) return reconstructPath();
            for (int[] d : directions) {
                int nx = x + d[0], ny = y + d[1];
                if (isValid(nx, ny)) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return null;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < maze.getGrid().length && y >= 0 && y < maze.getGrid()[0].length &&
               maze.getGrid()[x][y] != '@' && !visited[x][y];
    }

    private List<int[]> reconstructPath() {
        // Path reconstruction logic
        return new ArrayList<>();
    }
}


