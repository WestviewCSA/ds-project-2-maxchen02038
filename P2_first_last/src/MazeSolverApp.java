import java.util.Scanner;

public class MazeSolverApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Maze maze = new Maze("maze.txt"); // Ensure this file exists

        while (true) {
            maze.displayMaze();
            System.out.print("Move (WASD): ");
            char move = scanner.next().toUpperCase().charAt(0);

            if (maze.movePlayer(move)) {
                if (maze.hasReachedGoal()) {
                    System.out.println("🎉 Congrats! You reached the goal!");
                    break;
                }
            } else {
                System.out.println("❌ Invalid move!");
            }
        }
        scanner.close();
    }
}