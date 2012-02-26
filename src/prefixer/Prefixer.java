package prefixer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import prefixer.ast.InfixParser;
import prefixer.ast.Node;
import prefixer.lex.SimpleLexer;

/**
 * Main running class
 * 
 * @author John
 * 
 */
public class Prefixer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0 || args.length > 2) {
			System.out.println("Usage: java -jar prefixer [-c] [-r] <file>");
			System.out.println("-r enabled output reduction.");
			System.out
					.println("-c starts calculator mode. Cannot be used with file.");
			return;
		}

		String fileName = "";
		boolean reduce = false;
		boolean calcMode = false;

		for (String arg : args) {
			if (arg.equals("-r")) {
				reduce = true;
			} else if (arg.equals("-c")) {
				calcMode = true;
			} else {
				fileName = arg;
			}
		}

		if (calcMode) {
			calculatorMode(reduce);
			return;
		}

		File inputFile = new File(fileName);
		String inputLine = "";
		try {
			FileInputStream fis = new FileInputStream(inputFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			inputLine = br.readLine();
		} catch (FileNotFoundException ex) {
			System.out.println("Could not find input file: " + fileName);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Instantiate components
		Lexer lexer = new SimpleLexer(inputLine);
		Parser parser = new InfixParser(reduce);
		Formatter formatter = new PrefixFormatter();

		// Attempt to parse and flatten into prefix
		try {
			Node root = parser.getSyntaxTree(lexer);
			String flatOutput = formatter.getFlattened(root);

			System.out.println(flatOutput);
		} catch (Exception e) {
			System.out.println("Could not read equation: " + e.getMessage());
		}
	}

	/**
	 * Runs calculator mode.
	 * 
	 * @param reduce
	 *            Reduction mode for the AST parser.
	 */
	private static void calculatorMode(boolean reduce) {
		boolean running = true;
		InputStreamReader inputReader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(inputReader);

		System.out
				.println("Prefixer Calculator Mode. \"quit\" to exit program.");
		if (reduce) {
			System.out.println("Expression reduction enabled.");
		}

		while (running) {
			System.out.print("\nInput> ");
			String input;
			try {
				input = br.readLine();
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Could not read the input equation.\n");
				continue;
			}

			if (input.equals("quit")) {
				running = false;
				continue;
			}

			Lexer lexer = new SimpleLexer(input);
			Parser parser = new InfixParser(reduce);
			try {
				Node node = parser.getSyntaxTree(lexer);
				Formatter formatter = new PrefixFormatter();

				String prefixFlat = formatter.getFlattened(node);
				System.out.println(prefixFlat);
			} catch (Exception e) {
				System.out
						.println("Exception during parsing! Perhaps malformed equation?");
			}
		}
	}
}
