import java.io.*;
import java.util.*;

class Parser {
	public final static Scanner in = new Scanner(System.in);
	static String[] TOKEN;
	static int INDEX = 0;

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

			if (checkTokenIdentifier(getToken())) {
				incrementPointer();
			}

			if (checkTokenOperator(getToken()) && INDEX == 0) reject();
			if (checkLineBreak(getToken()) && INDEX == 0) reject();
			if (checkTokenComparitor(getToken()) && INDEX == 0) reject();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static boolean registerVariable(String token) {
		if (VARIABLE.contains(token)) {
			System.out.println("variable already exists");
			reject();
		}
		System.out.println(token);
		VARIABLE.add(token);
		return true;
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

	static boolen checkTokenComparitor(String token) {
		return COMPARITOR.contains(token);
	}

	static boolean checkLineBreak(String token) {
		if (Character.compare(token.charAt(0), BREAK) == 0) return true;
		return false;
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
