import java.io.*;
import java.util.*;

class Parser {
	public final static Scanner in = new Scanner(System.in);
	static String[] TOKEN;
	static int INDEX = 0;
	private final static char BREAK = ';'; 

	private static final HashMap<String, Character> operatorStore = new HashMap<>();
	private static final HashMap<String, String> identifierStore = new HashMap<>();
	private static final HashMap<String, Character> comparitorStore = new HashMap<>();
	private static List<String> variables = new ArrayList<String>();

	static {
		operatorStore.put("ADD", '+');
		operatorStore.put("SUB", '-');
		operatorStore.put("MUL", '*');
		operatorStore.put("DIV", '/');
		operatorStore.put("MOD", '%');

		identifierStore.put("LET", "let");
		identifierStore.put("CONST", "const");
		identifierStore.put("VAR", "var");
		identifierStore.put("TYPE", "type");

		comparitorStore.put("ASSIGN", '=');

	}

	public static void main(String[] args) throws IOException {
		in.useDelimiter("\n");
		System.out.println("Enter String to validate");

		String stmt = in.next();
		TOKEN = stmt.split("\\s");

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
		
	

	static void type() {
		try {
			// checks token for identifier
			if (checkTokenIdentifier(getToken())) registerVariable(getNextToken());	
			// chekcs token for operator
			if (checkTokenOperator(getToken())) reject(); 
			// Cheks if line breaker in the middle of string input
			if (TOKEN.length != INDEX + 1) {
				if (checkLineBreak(getToken())) reject();
			}
			
			// increment pointer for next iteration
			incrementPointer();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	static boolean registerVariable(String token) {
		if (variables.contains(token)) {
			System.out.println("variable already exists");
			reject();
		}
		variables.add(token);
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
		++INDEX;
	}

	static boolean checkTokenIdentifier(String token) {
		return identifierStore.containsValue(token);
	}

	static boolean checkTokenOperator(String token) {
		return operatorStore.containsValue(token);
	}

	static boolean checkLineBreak(String token) {
		char parsed = token.charAt(0);
		if (Character.compare(token.charAt(0), BREAK) == 0) return true;
		return false;
	}

	static void reject() {
		System.out.println("reject");
		System.exit(0);
	}

	static void acceptt() {
		System.out.println("accept");
		System.exit(0);
	}
	
}
