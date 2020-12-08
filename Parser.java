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
	private final static char BREAK = ';'; 
	private static List<String> VARIABLE = new ArrayList<String>();
	private static List<String> IDENTIFIER = new ArrayList<String>();
	private static List<String> OPERATOR = new ArrayList<String>();
	private static List<String> COMPARITOR = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		in.useDelimiter("\n");
		System.out.println("Enter String to validate");

		String stmt = in.next();
		TOKEN = stmt.split("\\s");
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
		parse();

		/*try {
			System.out.println(getToken());
			incrementPointer();
			System.out.println(getToken());
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/

	}

	public static void parse() {
		base();
	}

	static void base() {
		try {

			if (INDEX == TOKEN.length - 1) {
				if (checkLineBreak(getToken())) accept();
				reject();
			}
			// throw all error first
			if (INDEX == 0) {
				if (checkTokenOperator(getToken())) reject();
				if (checkLineBreak(getToken())) reject();
				if (checkTokenComparitor(getToken())) reject();

			} else {
				if (checkTokenOperator(getToken()) && flagO == true) reject();
				if (checkLineBreak(getToken()) && flagI == true || flagO == true) reject();
				if (checkTokenComparitor(getToken()) && flagI == true || flagO == true) reject();
				//if (checkTokenOperator(getToken()))
			}

			if (checkTokenIdentifier(getToken())) {
				if (flagI == true) reject();
				
				resetFlag();
				flagI = true;
				incrementPointer();
				System.out.println("The index" + INDEX);
				base();
			}

			if(checTokenVariable(getToken())) {
				if (flagI == true) {
					if(registerVariable(getToken())) {
						resetFlag();
						flagV = true;
					}
					incrementPointer();
					System.out.println("The index" + INDEX);
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
		System.out.println(token);
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
		if (Character.compare(token.charAt(0), BREAK) == 0) return true;
		return false;
	}

	static boolean checTokenVariable(String token) {
		return (!checkTokenComparitor(token) && !checkLineBreak(token) && !checkTokenIdentifier(token) && !checkTokenOperator(token));
	}

	static void setup() {
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
