import java.io.*;
import java.util.*;

class Parser {

	// Scanner
	public final static Scanner in = new Scanner(System.in);

	// Token in buffer
	static String[] TOKEN;

	// index pointer 
	static int INDEX = 0;
	
	// flag
	static boolean flagO = false;
	static boolean flagV = false;
	static boolean flagC = false;
	static boolean flagI = false;

	// Reserved word store
	private final static String BREAK = ";"; 
	private static List<String> VARIABLE = new ArrayList<String>();
	private static List<String> IDENTIFIER = new ArrayList<String>();
	private static List<String> OPERATOR = new ArrayList<String>();
	private static List<String> COMPARITOR = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		in.useDelimiter("\n");
		System.out.println("Enter String to validate");

		String stmt = in.next();
		TOKEN = stmt.split("\\s");
		
		// Setup reserved words
		setup();

		// Parse incoming string
		parse();
	}

	public void setup() {
		OPERATOR.add("+");
		OPERATOR.add("-");
		OPERATOR.add("*");
		OPERATOR.add("/");
		OPERATOR.add("%");

		COMPARITOR.add("=");

		IDENTIFIER.add("let");
		IDENTIFIER.add("type");
		IDENTIFIER.add("var");
		IDENTIFIER.add("const");
		IDENTIFIER.add("enum");
	}

	public static void parse() {
		base();
	}

	static void base() {
		try {
			// Checks if index reached its limit
			if (INDEX == TOKEN.length - 1) {
				if (checkLineBreak(getToken()) && flagV == true) accept();
				else reject();
			}

			// throw all error first
			if (INDEX == 0) {

				// When pointer index is 0
				if (checkTokenOperator(getToken())) reject();
				if (checkLineBreak(getToken())) reject();
				if (checkTokenComparitor(getToken())) reject();

			} else {

				// When pointer is not 0
				if (checkTokenOperator(getToken()) && flagO == true) reject();
				if (checkLineBreak(getToken()) && (flagI == true || flagO == true)) reject();
				if (checkTokenComparitor(getToken()) && (flagI == true || flagO == true)) reject();
			}

			// Checks for identifier
			if (checkTokenIdentifier(getToken())) {
				if (flagI == true) reject();
				resetFlag();

				flagI = true;
				incrementPointer();

				// Recursion
				base();
			}

			// Checks for operator
			if (checkTokenOperator(getToken())) {
				if (flagV == true) resetFlag();

				flagO = true;
				incrementPointer();

				// Recursion 
				base();
			}

			// Checks for comparitor
			if (checkTokenComparitor(getToken())) {
				if (flagV == true) resetFlag();

				flagC = true;
				incrementPointer();

				// Recursion
				base();
			}

			// Chekcs for variable
			if (checkTokenVariable(getToken())) {
				if ((flagI == true || flagC == true || flagO == true) || INDEX == 0) {
					if(registerVariable(getToken())) {
						resetFlag();
						flagV = true;
					}
					incrementPointer();

					// Recursion
					base();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static boolean registerVariable(String token) {
		if (checkTokenOperator(token) || checkLineBreak(token) || checkTokenComparitor(token)) reject();

		if (VARIABLE.contains(token)) {
			System.out.println("variable already exists");
			reject();
		}
		VARIABLE.add(token);
		return true;
	}

	static void resetFlag() {
		flagV = false;
		flagO = false;
		flagI = false;
		flagC = false;
	}

	static String getToken() {
		return TOKEN[INDEX];
	}

	static String getNextToken() throws Exception {
		if (INDEX > TOKEN.length) throw new Exception("Array out of bound");
		return TOKEN[++INDEX];
	}

	static void incrementPointer() throws Exception {
		if (INDEX > TOKEN.length) throw new Exception("Array out of bound");
		INDEX++;
	}

	static boolean checkTokenIdentifier(String token) {
		return IDENTIFIER.contains(token);
	}

	static boolean checkTokenOperator(String token) {
		return OPERATOR.contains(token);
	}

	static boolean checkTokenComparitor(String token) {
		return COMPARITOR.contains(token);
	}

	static boolean checkLineBreak(String token) {
		if (BREAK.equalsIgnoreCase(token)) return true;
		return false;
	}

	static boolean checkTokenVariable(String token) {
		return (!checkLineBreak(token) && !checkTokenIdentifier(token) && !checkTokenOperator(token));
	}

	static void reject() {
		System.out.println("reject");
		System.exit(0);
	}

	static void accept() {
		System.out.println("accept");
		System.exit(0);
	}
}
