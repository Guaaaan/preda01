package preda01;

public class ReinasGame {
	
	private boolean verbosity;
	private String CHAR_COLS = "abcdefgh";
	private byte char_gap = (byte)'a'; 
	
	private void log(String message) {
		if(this.verbosity) {
			System.out.println(message);
		}
	}
	
	private char colToChar(byte col) {
		return this.CHAR_COLS.charAt(col);
	}

	private byte colToByte(char col) {
		return (byte) ((byte)col - this.char_gap);
	}
	
	public ReinasGame(String position,
					  boolean verbose,
					  boolean full_output) {
		
		byte row;
		byte col;
		try {
			row = (byte)(Character.getNumericValue(position.charAt(0)) - 1);
			col = this.colToByte(position.toLowerCase().charAt(1));

			if (col< 0 || col > 7 || row < 0 || row > 7) {
				throw new Exception("Parse error.");
			}
		} catch	(Exception e){
			System.out.println("---ERROR---");
			System.out.println("Expected input as chess position: \n");
			System.out.println("First character(1-8), Second character(a-h).");
			System.out.println("i.e: 1d, 3f, 2h, etc");
			System.out.println("But got this instead: " + position);
		}

		this.verbosity = verbose;
		this.log("Starting Algorithm. "
			+ "Trace: " + verbose 
			+ " Full output: " + full_output);
		
		//this.solve
		
	}
}
