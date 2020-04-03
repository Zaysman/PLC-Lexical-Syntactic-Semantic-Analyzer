package Main;
//Packages
import Objects.KeywordToken;
import Objects.Token; //Parent class for all tokens
import Objects.OpToken; //Class for all operation tokens
import Objects.literalToken;

//Libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.List;

public class Main {

	static File file = new File("Sample input");
	static List<String> lexemes = new LinkedList<String>(); //The list we add our lexemes into
	static List<Token> tokens = new LinkedList<Token>(); //The list we add our tokens into


	public static void main(String[] args) throws FileNotFoundException, IOException {
		//Get Lexemes
		Scanner scan = new Scanner(file);
		String input = new String();
		
		
		while(scan.hasNext()) {
			input = scan.next(); //input becomes the next token in the file
			handleString(input);
		}
		scan.close();

		clearEmptySpaces(lexemes);
		printList(lexemes); //here we have our list of lexemes 

		//Convert Lexemes into Tokens
		String token = new String();

		for(int i = 0; i < lexemes.size(); i++) {
			token = lexemes.get(i);
			convertToken(token);
		}


	}

	public static void convertToken(String token) {
		if(checkOperator(token) == true) {
			handleOperator(token);
			
		} else if(checkKeyword(token) == true) {
			handleKeyword(token);
			
		} else if(checkLiteral(token) == true) {
			
		}
		
	}


	public static boolean checkOperator(String op) {
		switch(op) {
		case "+":
		case "-":
		case "*":
		case "/":
			return true;
		default:
			return false;
		}
	}
	 
	public static void handleOperator(String op) {
		if(op.compareTo("+") == 0) { //adds addition operator to the list
			OpToken optoken = new OpToken("+");
			tokens.add(optoken);
			
		} else if(op.compareTo("-") == 0) { //adds subtraction operator to token list
			OpToken optoken = new OpToken("-");
			tokens.add(optoken);
			
		} else if(op.compareTo("*") == 0) { //addes multiplication operator to token list
			OpToken optoken = new OpToken("*");
			tokens.add(optoken);
			
		} else if(op.compareTo("/") == 0) { //addes division operator to token list
			OpToken optoken = new OpToken("/");
			tokens.add(optoken);
			
		}
		
	}

	public static boolean checkKeyword(String key) {
		switch(key) {
		case "new":
		case "num":
		case "real":
		case "String" :
		case "is":
		case "isnot":
		case "for":
		case "until":
		case "perform":
		case "open":
		case "closed":
		case "empty":
		case "skip":
		case "jump":
		case "jump_out":	
			return true;
		default:
			return false;
		}
	
	}

	public static void handleKeyword(String key) {
		if(key.compareTo("new") == 0) {
			KeywordToken keytoken = new KeywordToken("new");
			tokens.add(keytoken);
			
		} else if(key.compareTo("num") == 0) {
			KeywordToken keytoken = new KeywordToken("num");
			tokens.add(keytoken);
			
		} else if(key.compareTo("real") == 0) {
			KeywordToken keytoken = new KeywordToken("real");
			tokens.add(keytoken);
			
		} else if(key.compareTo("String") == 0) {
			KeywordToken keytoken = new KeywordToken("String");
			tokens.add(keytoken);
			
		} else if(key.compareTo("is") == 0) {
			KeywordToken keytoken = new KeywordToken("is");
			tokens.add(keytoken);
			
		} else if(key.compareTo("isnot") == 0) {
			KeywordToken keytoken = new KeywordToken("isnot");
			tokens.add(keytoken);
			
		} else if(key.compareTo("for") == 0) {
			KeywordToken keytoken = new KeywordToken("for");
			tokens.add(keytoken);
			
		} else if(key.compareTo("until") == 0) {
			KeywordToken keytoken = new KeywordToken("until");
			tokens.add(keytoken);
			
		} else if(key.compareTo("perform") == 0) {
			KeywordToken keytoken = new KeywordToken("perform");
			tokens.add(keytoken);
			
		} else if(key.compareTo("open") == 0) {
			KeywordToken keytoken = new KeywordToken("open");
			tokens.add(keytoken);
			
		} else if(key.compareTo("closed") == 0) {
			KeywordToken keytoken = new KeywordToken("closed");
			tokens.add(keytoken);
			
		} else if(key.compareTo("empty") == 0) {
			KeywordToken keytoken = new KeywordToken("empty");
			tokens.add(keytoken);
			
		} else if(key.compareTo("skip") == 0) {
			KeywordToken keytoken = new KeywordToken("skip");
			tokens.add(keytoken);
			
		} else if(key.compareTo("jump") == 0) {
			KeywordToken keytoken = new KeywordToken("jump");
			tokens.add(keytoken);
			
		} else if(key.compareTo("jump_out") == 0) {
			KeywordToken keytoken = new KeywordToken("jump_out");
			tokens.add(keytoken);
		}
	}
	
	public static boolean checkLiteral(String lit) {
		//check boolean literal
		switch(lit) {
		case "true":
		case "false":
		return true;
		}
		
		
		
		
		return false;
	}
	
	public static void handleString(String str) {
		if(hasSpecialCharacter(str) == false) {
			lexemes.add(str);
		} else {
			spliceString(str);
		}


	}

	public static void spliceString(String str) { 
		int special = specialCharacterAt(str);
		String substr = str.substring(0, special);
		lexemes.add(substr);

		lexemes.add(Character.toString(str.charAt(special)));
		substr = str.substring(special + 1, str.length());
		handleString(substr);
	}

	/*
	 * The purpose of this method is to determine if a special character exists within a string. If so, returns true. else returns false
	 * 
	 * str - String parameter
	 */

	public static boolean hasSpecialCharacter(String str) {

		for(int i = 0; i < str.length(); i++) {
			if(checkSpecialCharacter(str.charAt(i)) == true) {
				return true;
			}
		}

		return false;
	}

	/*
	 * Assuming a special character has been found within a string, this method finds and returns the index of the first special character found.
	 * 
	 * str - String parameter
	 */

	public static int specialCharacterAt(String str) {

		for(int i = 0; i < str.length(); i++) {
			if(checkSpecialCharacter(str.charAt(i)) == true) {
				return i;
			}
		}

		return -1;
	}

	/*
	 * method that check if the given character is a special character. if so, returns true. else returns false.
	 */

	public static boolean checkSpecialCharacter(char ch) {

		switch(ch) {
		case ';' :
		case '!' :
		case '#' :
		case '"' :
		case '$' :
		case '%' :
		case '&' :
		case '(' :
		case ')' :
		case '*' :
		case '+' :
		case ',' :
		case '-' :
			//case '.' :
		case '/' :
		case ':' :
		case '<' :
		case '=' :
		case '>' :
		case '?' :
		case '@' :
		case '[' :
		case '\\':
		case ']' :
		case '^' :
		case '_' :
		case '`' :
		case '{' :
		case '}' :
		case '|' :
		case '~' :	
			return true;
		default:
			return false;
		}
	}

	/*
	 * Utility method that prints out the contents of the list of lexemes
	 */
	public static void printList(List list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println("list(" + i + "): " + list.get(i));
		}
	}
	/*
	 * Utility that removes any empty spaces in our list just in case
	 */
	public static void clearEmptySpaces(List list) {
		for(int i = 0; i < list.size(); i++) {
			String str = (String) list.get(i);
			if(str.isEmpty() == true) {
				list.remove(i);
			}
		}
	}
}
