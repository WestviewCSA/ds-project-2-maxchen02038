public class Map {
	
	 char[][] maze;
	    int rows, cols;
	    Position start, end;
	    
	    
	    boolean solveWithQueue() {
	        return solve(new LinkedList<>());
	    }
	    
	    boolean solveWithStack() {
	        return solve(new Stack<>());
	    }
	    
	    private boolean solve(Collection<Position> structure) {
	        Set<Position> visited = new HashSet<>();
	        Map<Position, Position> path = new HashMap<>();
	        structure.add(start);
	        visited.add(start);
	        
	        while (!structure.isEmpty()) {
	            Position current = structure instanceof Queue ? ((Queue<Position>) structure).poll() : ((Stack<Position>) structure).pop();
	            if (current.equals(end)) return markPath(path);
	            for (Position neighbor : current.getNeighbors(rows, cols, maze)) {
	                if (!visited.contains(neighbor)) {
	                    visited.add(neighbor);
	                    structure.add(neighbor);
	                    path.put(neighbor, current);
	                }
	            }
	        }
	        return false;
	    }
	    


	class Position {
	    int row, col;
	    
	    Position(int row, int col) {
	        this.row = row;
	        this.col = col;
	    }
	    
   
	
}
