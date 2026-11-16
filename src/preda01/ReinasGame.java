package preda01;

import java.util.Map;
import java.util.HashMap;

public class ReinasGame {
	
	//STATIC CONSTANTS
	private static int BOARD_SIZE = 8;
	private static int NUM_QUEENS = 7;
	private static int EMPTY = -1;
	private static char HORSE = 'H';
	private static char QUEEN = 'Q';
	private static char REACHABLE = 'R'; 

	private boolean show_trace;
	private boolean full_output;
	private Integer[] row_positions;
	private Integer[] col_positions;
	private HashMap<String, Character> visited;


	public ReinasGame(boolean show_trace, boolean full_output) {

		// Output params
		this.show_trace = show_trace;
		this.full_output = full_output;

	}

	public void solve(int row, int col) {
		//// Init ////
		// Init visited
		visited = new HashMap<>();

		// Init results as -1 (empty unreachable position)
		this.row_positions = new Integer[ReinasGame.BOARD_SIZE];
		this.col_positions = new Integer[ReinasGame.BOARD_SIZE];
		for(int i=0; i<ReinasGame.BOARD_SIZE; i++) {
			this.row_positions[i] = ReinasGame.EMPTY;
			this.col_positions[i] = ReinasGame.EMPTY;
		}

		// Store Horse
		this.setHorse(row, col);
		
		// Start recursion with index 1 (horse is on zero)
		if (this.recursion(1))
			if (this.full_output)
				this.printFullSolution();
			else
				this.printSolution();
		else
			System.out.println("No valid solution was found :(");
		
	}
	
	private void printSolution() {
		for (int i=0; i<ReinasGame.BOARD_SIZE; i++){
			System.out.print(this.indexToChessPos(
				this.row_positions[i],
				this.col_positions[i]
				) + " ");
		}
	}
	
	private void printFullSolution() {
		
	}
	
	private String indexToChessPos(int row, int col) {
		return String.valueOf(row + 1) + "abcdefgh".charAt(col);
	}
	
	private boolean recursion(int current_queen) {
		//Edge case: max num of queens is reached
		if (current_queen >= ReinasGame.NUM_QUEENS){
			return true;
		}
		
		// Iterate for the next free position
		for(int i=0; i<ReinasGame.BOARD_SIZE; i++) {
			for(int j=0; j<ReinasGame.BOARD_SIZE; j++) {
				// Not free 
				if(!this.isValidPosition(i, j))
					continue;
				// Try position
				this.row_positions[current_queen] = i;
				this.col_positions[current_queen] = j;
				this.setVisited(i, j, ReinasGame.QUEEN);
				// Recur path
				if(this.recursion(current_queen + 1))
					return true;
				// Clean queen in board
				this.unsetVisited(i, j);
			}
		}
		// No solution was found, clean and return
		this.row_positions[current_queen] = ReinasGame.EMPTY;
		this.col_positions[current_queen] = ReinasGame.EMPTY;
		return false;
	}

	private void setHorse(int row, int col) {

		//Occupy horse space
		this.setVisited(row, col, ReinasGame.HORSE);
		this.row_positions[0] = row;
		this.col_positions[0] = col;

		// Store horse's affected squares as visited
		if (!this.isOut(row + 2, col + 1))
			this.setVisited(row + 2, col + 1, ReinasGame.REACHABLE);

		if (!this.isOut(row + 1, col + 2))
			this.setVisited(row + 1, col + 2, ReinasGame.REACHABLE);

		if (!this.isOut(row - 2, col - 1))
			this.setVisited(row - 2, col - 1, ReinasGame.REACHABLE);

		if (!this.isOut(row - 1, col - 2))
			this.setVisited(row - 1, col - 2, ReinasGame.REACHABLE);

		if (!this.isOut(row + 2, col - 1))
			this.setVisited(row + 2, col - 1, ReinasGame.REACHABLE);

		if (!this.isOut(row - 1, col + 2))
			this.setVisited(row - 1, col + 2, ReinasGame.REACHABLE);

		if (!this.isOut(row - 2, col + 1))
			this.setVisited(row - 2, col + 1, ReinasGame.REACHABLE);

		if (!this.isOut(row + 1, col - 2))
			this.setVisited(row + 1, col - 2, ReinasGame.REACHABLE);
	}

	private String indexOf(int row, int col) {
		return String.valueOf(row) + String.valueOf(col);
	}


	private boolean isOut(int row, int col) {
		return ((row < 0) || (row > ReinasGame.BOARD_SIZE - 1) ||
				(col < 0) || (col > ReinasGame.BOARD_SIZE - 1));
	}

	private void setVisited(int row, int col, char by) {
		this.visited.put(String.valueOf(row) + String.valueOf(col), by);
	}

	private void unsetVisited(int row, int col) {
		this.visited.remove(String.valueOf(row) + String.valueOf(col));
	}

	private boolean isVisited(int row, int col) {
		return (this.visited.get(this.indexOf(row, col)) != null);
	}
	
	private boolean isValidPosition(int row, int col) {
		// Check if i have already visited this (or someone is there)
		if(this.isVisited(row, col)){
			return false;
		}
		
		// Iterate positions
		for(int i=0; i<ReinasGame.BOARD_SIZE; i++) {
			// Next empty position means
			// we did not found collisions
			if (this.row_positions[i] < 0) {
				break;
			}

			// Check row and column
			if ((this.row_positions[i] == row) || 
				(this.col_positions[i] == col))
				return false;

			// Check diagonal positions
			// If distance on both axes is the same 
			// there is collision
			if (Math.abs(this.row_positions[i] - row) == 
				Math.abs(this.col_positions[i] - col))
				return false;
		}
		
		return true;
	}

}
