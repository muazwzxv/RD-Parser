import java.io.*;
import java.util.Scanner;

class Parser {
	public final static Scanner in = new Scanner(System.in);
	static String token;

	static final char ADD = '+';
	static final char SUB = '-';
	static final char MUL = '*';
	static final char DIV = '/';
	static final char MOD = '%';
	static final char ASSIGN = '=';


	static final String LET = "let";
	static final String CONST = "const";
	static final String VAR = "var";



	public static void main(String[] args) throws IOException {

		in.useDelimiter("\n");
		System.out.println("Enter String to validate");
		String stmt = in.next();
		

		String[] token = stmt.split("\\s");
		for (String w: token) {
			System.out.println(w);
		}

	}

	public static String getToken() {
		return token = in.next();
	}
}
