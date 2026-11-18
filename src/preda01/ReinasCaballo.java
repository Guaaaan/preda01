package preda01;

public class ReinasCaballo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//Check file
		
		//Check params
		int row, col;
		row =0;
		col =3;

		//Instance and solve ReinasGame.java
		ReinasGame rg = new ReinasGame(false, false);
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				System.out.println("Testing Horse at " + i + " " + j);
				rg.solve(i, j); 
			}
		}
		//rg.solve(row, col); 
	}
	private static void parseArgs(String[] args) {
		if (args.length == 0) {
			ReinasCaballo.printHelp();
			return;
		}
		
		for(int i=0; i<args.length; i++) {
			switch(args[i])
		}
		
		
	}
	
	private static void printHelp() {
		System.out.println("SINTAXIS: reinasCaballo [-t][-h] [-g] [fichero entrada] [fichero salida]");
		System.out.println("          -t Traza cada paso");
		System.out.println("          -h Muestra esta ayuda");
		System.out.println("          -g Modo salida gráfica");
		System.out.println("          [fichero entrada] Posición del caballo (filacolumna)");
		System.out.println("          [fichero salida] Posiciones del caballo y de las reinas, o tablero gráfico");
	}

}
