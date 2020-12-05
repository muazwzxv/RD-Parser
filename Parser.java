import java.io.*;
import java.util.Scanner;

class Parser {
	public final static Scanner in = new Scanner(System.in);
	static String[] token;
	static int index = 0;

	// Constants for operators
	static final char ADD = '+';
	static final char SUB = '-';
	static final char MUL = '*';
	static final char DIV = '/';
	static final char MOD = '%';
	static final char ASSIGN = '=';

	// Constant for identifier
	static final String LET = "let";
	static final String CONST = "const";
	static final String VAR = "var";



	public static void main(String[] args) throws IOException {

		in.useDelimiter("\n");
		System.out.println("Enter String to validate");
		String stmt = in.next();

		token = stmt.split("\\s");
		for (int i = 0; i < token.length; i++) {
			try {
				System.out.println(getNextToken());
			} catch(Exception e) {
				System.out.println(e);
			}
		}
	}

	public static String getToken() {
		return token[index];
	}

	public static String getNextToken() throws Exception {
		if (index > token.length) throw new Exception("Array out of bound");
		return token[index++];
	}
}
