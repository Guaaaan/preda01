package preda01;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ReinasCaballo {
	
	private static boolean trace;
	private static boolean graph;
	private static boolean help;
	private static int col;
	private static int row;
	private static File source;
	private static Path destination;
	private static Charset charset;


	public static void main(String[] args) {
		// Init optional arguments default values
		ReinasCaballo.trace = false;
		ReinasCaballo.graph = false;
		ReinasCaballo.help = false;

		
		// Set charset
		ReinasCaballo.charset = StandardCharsets.UTF_8;
		/*
		for(int i=0; i<args.length; i++)
			System.out.println(args[i]);
		args = new String[]{"a", "b", "c"};
		args = new String[]{"-h"};
		args = new String[]{};
		args = new String[]{"-h", "-t", "-g", "/home/jgotor/eclipse-workspace/preda01/test", "./output.txt"};
		args = new String[]{"-t", "-g", "/home/jgotor/eclipse-workspace/preda01/test/source", "./output.txt"};
		args = new String[]{"-t", "-g", "/home/jgotor/eclipse-workspace/preda01/test/bad_source", "./output.txt"};
		*/

		// Parse arguments
		try {
			ReinasCaballo.parseArgs(args);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			ReinasCaballo.printHelp();
			return;
		} 
		
		if (ReinasCaballo.help)
		{
			ReinasCaballo.printHelp();
			if (args.length == 1)
				return;
		}
		
		// Open source file
		try {
			ReinasCaballo.readSource();
		} catch (Exception e) {
			System.out.println("ERROR reading file. No position found");
		}
		
		ReinasGame rg = new ReinasGame(ReinasCaballo.trace, ReinasCaballo.graph);
		try
		{
			ReinasCaballo.writeOutput(rg.solve(ReinasCaballo.row, ReinasCaballo.col));
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private static void readSource() throws Exception{
		String horse_pos;
		try (BufferedReader br = Files.newBufferedReader(
					Paths.get(ReinasCaballo.source.getAbsolutePath()),
					ReinasCaballo.charset)){
			horse_pos = br.readLine().strip(); 
		}
		ReinasCaballo.row = Integer.parseInt(
				(String)horse_pos.subSequence(0, 1)) - 1;
		ReinasCaballo.col = "abcdefgh".indexOf(
				(String)horse_pos.subSequence(1, 2));
        
	}
	
	private static void writeOutput(List<String> result) throws Exception{
		OpenOption[] write_options = new OpenOption[]{
				StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE};
		Files.write(ReinasCaballo.destination, result, StandardCharsets.UTF_8, write_options);
	}

	private static void parseArgs(String[] args) throws Exception{
		if (args.length == 0)
			throw new Exception("No arguments passed");
		
		if (args.length == 1)
		{
			if (args[0] == "-h") {
				ReinasCaballo.help = true;
				return;
			}
			throw new Exception("Source file does not exist.");
		}

		
		//Parse arguments at the end first
		ReinasCaballo.source = new File(args[args.length - 2]);
		String destination_arg = (args[args.length - 1]);
		
		// Check source
		if(!ReinasCaballo.source.exists())
			throw new Exception("Source file does not exist.");

		if(!ReinasCaballo.source.isFile())
			throw new Exception("Source file is not a file.");
		
		if(!ReinasCaballo.source.canRead())
			throw new Exception("Source file cannot be read.");
		
		// Check destination
		// Raises InvalidPathException if path syntax is bad
		ReinasCaballo.destination = Paths.get(destination_arg).toAbsolutePath().normalize();
		// Check if parent folder exists
        if (ReinasCaballo.destination.getParent() == null
        	|| !Files.isDirectory(ReinasCaballo.destination.getParent())) {
            throw new Exception(
            		"Destination folder does not exist: " + 
            		ReinasCaballo.destination.getParent());
        }
        if (!Files.isWritable(ReinasCaballo.destination.getParent())) {
            throw new Exception("Destination folder is not writable: " + 
            		ReinasCaballo.destination.getParent());
        }
			
        // Check optional args
		for(int i=args.length - 3; i>-1; i--) {
			switch(args[i]) {
			case "-t":
				ReinasCaballo.trace = true;
				break;
			case "-h":
				ReinasCaballo.help = true;
				break;
			case "-g":
				ReinasCaballo.graph = true;
				break;
			default:
				throw new Exception("Argument not recognized: " + args[i]);
			}
		}
	}
	
	private static void printHelp() {
		System.out.println("SINTAXIS: reinasCaballo [-t][-h] [-g] [fichero entrada] [fichero salida]");
		System.out.println("          -t Traza cada paso");
		System.out.println("          -h Muestra esta ayuda");
		System.out.println("          -g Modo salida gr�fica");
		System.out.println("          [fichero entrada] Posici�n del caballo (filacolumna)");
		System.out.println("          [fichero salida] Posiciones del caballo y de las reinas, o tablero gr�fico");
	}

}
