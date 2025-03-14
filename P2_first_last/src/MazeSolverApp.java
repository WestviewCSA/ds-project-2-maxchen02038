import java.io.IOException;
import java.util.List;

public class MazeSolverApp {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MazeSolverApp <maze_file> --BFS");
            return;
        }
        try {
            Maze maze = new Maze(args[0]);
            Solver solver;
            if (args[1].equals("--BFS")) {
                solver = new BFSSolver(maze);
                List<int[]> path = ((BFSSolver) solver).solve();
                if (path != null) {
                    System.out.println("Path found!");
                } else {
                    System.out.println("No path found.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading maze file.");
        }
    }
}