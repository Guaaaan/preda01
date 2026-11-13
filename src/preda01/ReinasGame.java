package preda01;
import java.util.Map;
import java.util.HashMap;


public class ReinasGame {
	
	private boolean show_trace;
	private boolean full_output;
	private String[] positions;
	private HashMap <String, Boolean> visited;

	public ReinasGame(boolean show_trace, boolean full_output) {

		// Output params
		this.show_trace = show_trace;
		this.full_output = full_output; 
		
	}
	
	public void solve(int row, int col) {
		//// Init ////
		// Init visited
		visited = new HashMap<>();
		// Result
		this.positions = new String[8];
		// Store Horse
		this.addPosition(row, col, 0);
		this.horseVisit(row, col);
		
		
		
	}
	
	private void horseVisit(int row, int col) {

		this.setVisited(row, col);
		// Store horse's affected squares as visited
		if(!this.isOut(row + 2, col + 1))
			this.setVisited(row + 2, col + 1);

		if(!this.isOut(row + 1, col + 2))
			this.setVisited(row + 1, col + 2);

		if(!this.isOut(row - 2, col - 1))
			this.setVisited(row - 2, col - 1);

		if(!this.isOut(row - 1, col - 2))
			this.setVisited(row - 1, col - 2);

		if(!this.isOut(row + 2, col - 1))
			this.setVisited(row + 2, col - 1);

		if(!this.isOut(row - 1, col + 2))
			this.setVisited(row - 1, col + 2);

		if(!this.isOut(row - 2, col + 1))
			this.setVisited(row - 2, col + 1);

		if(!this.isOut(row + 1, col - 2))
			this.setVisited(row + 1, col - 2);
	}
	
	
	private String indexOf(int row, int col)
	{
		return String.valueOf(row) + String.valueOf(col);
	}
		
	private void addPosition(int row, int col, int current) {
		this.positions[current] = String.valueOf(row + 1) + "abcdefgh".charAt(col);
	}
	
	private boolean isOut(int row, int col)
	{
		return ((row < 0) || (row > 7) || (col < 0) || (col > 7));
	}
	
	private void setVisited(int row, int col) {
		this.visited.put(String.valueOf(row) + String.valueOf(col), null);
	}
	
	
}
