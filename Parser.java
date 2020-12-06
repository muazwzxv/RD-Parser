import java.io.*;
import java.util.*;

class Parser {
	public final static Scanner in = new Scanner(System.in);
	static String[] token;
	static int index = 0;

	private static final HashMap<String, Character> operatorStore = new HashMap<>();
	private static final HashMap<String, String> identifierStore = new HashMap<>();
	private static List<String> variables = new ArrayList<String>();

	static {
		operatorStore.put("ADD", '+');
		operatorStore.put("SUB", '-');
		operatorStore.put("MUL", '*');
		operatorStore.put("DIV", '/');
		operatorStore.put("MOD", '%');
		operatorStore.put("ASSIGN", '=');
		operatorStore.put("BREAK", ';');

		identifierStore.put("LET", "let");
		identifierStore.put("CONST", "const");
		identifierStore.put("VAR", "var");
		identifierStore.put("TYPE", "type");

	}

	// Constants for operators
	static final char ADD = '+';
	static final char SUB = '-';
	static final char MUL = '*';
	static final char DIV = '/';
	static final char MOD = '%';
	static final char ASSIGN = '=';
	static final char BREAK = ';';

	// Constant for identifier
	static final String LET = "let";
	static final String CONST = "const";
	static final String VAR = "var";

	public static void main(String[] args) throws IOException {
		in.useDelimiter("\n");
		System.out.println("Enter String to validate");

		String stmt = in.next();
		token = stmt.split("\\s");

		try {
			System.out.println(getToken());
			System.out.println(getNextToken());
			System.out.println(getToken());
		} catch(Exception e) {
			e.printStackTrace();
		}

		/*for (int i = 0; i < token.length; i++) {
			try {
				System.out.println(getNextToken());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}*/

	}

	public static void parse() {
		type();
		
	}

	void type() {
		if (checkTokenIdentifier(getToken())) {
			registerVariable(getNextToken());	
		}
	}

	boolean registerVariable(String var) {

	}

	public static String getToken() {
		return token[index];
	}

	public static String getNextToken() throws Exception {
		if (index > token.length) throw new Exception("Array out of bound");
		return token[++index];
	}

	public static boolean checkTokenIdentifier(String token) {
		return identifierStore.containsValue(token);
	}

	public static boolean checkTokenOperator(String token) {
		return operatorStore.containsValue(token);
	}
}
