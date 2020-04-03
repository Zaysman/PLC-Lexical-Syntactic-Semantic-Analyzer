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
			handleLiteral(token);
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
		boolean foundBool = false;

		//check boolean literal
		switch(lit) {
		case "true":
		case "false":
			foundBool = true;
		default:
			foundBool = false;
		}

		if(foundBool == true) { //If a boolean was found to be true, we can return a true for the method
			return true;
		} else { //If a boolean wasn't found we need to check if its a num (integer) or real (double) value
			
			//check for real
			if(checkReal(lit) == true) {
				return true;
				
			} else if(checkNum(lit) == true) {
				return true;
			} //check if the lexeme is a string lexeme
			
			
			
		}
		
		return false;
	}
	
	public static void handleLiteral(String lit) {
		if(lit.compareTo("true") == 0) {
			literalToken litToken = new literalToken(lit);
			tokens.add(litToken);
		} else if(lit.compareTo("false") == 0) {
			literalToken litToken = new literalToken(lit);
			tokens.add(litToken);
		} else if(checkReal(lit) == true) {
			literalToken litToken = new literalToken(lit, "real");
			tokens.add(litToken);
		} else if(checkNum(lit) == true) {
			literalToken litToken = new literalToken(lit, "num");
			tokens.add(litToken);
		}
		
		
	}

	public static boolean checkReal(String real) {
		char check; //this char variable will hold characters from the passed string
		
		//check the first character of the string if see if it's true
		check = real.charAt(0);
		
		//in order to not confused with a potential String, we check to see if the first character is a numeric value. If so, we can move on.
		//If it doesn't then, we know the lexeme does not qualifiy as a real value in our language and thus, we return false
		if(checkNum(check) == false) {
			return false;
		}
		
		
		check = real.charAt(real.length() - 1); //check is set to the last character of the string
		
		if(check == 'r') { //if the last character of the lexmeme is 'r', we can assume the lexeme is to be converted to a real_value token
			return true;
			
		} else { //If the above isn't true, we need to check for a period to see if the lexeme is a real_value
			
			//iterates through the string looking for a period.
			for(int i = 0; i < real.length(); i++) { 
				check = real.charAt(0);
				
				if(check == '.') { //If we encounter a period in our lexeme, at this point we can assume that the lexeme is a real_value token
					return true;
				}
				
			}
			
		}
		
		//If none of the above statements are true, then our lexeme does not qualifiy as a real_value token and we can move on to check 
		//something else
		
		return false; 
		}
	
	public static boolean checkNum(String num) {
		char check; //this char variable will hold characters from the passed string
		
		check = num.charAt(0); // we set check to the first character of the passed string
		
		
		//we check to see if the first character of the passed string is a numeric character. If so, we continue on.
		//If not, the lexeme does not qualify as a num_value token in our language. therefore we return false.
		if(checkNum(check) == false) { 
			return false;
		}
		
		//Since we called check num, we can assume that the lexeme isn't a real_value.
		//We can simply check to make sure all characters in the string are a numeric value to ensure it isn't a string
		for(int i = 0; i < num.length(); i++) {
			check = num.charAt(i);
			
			//we iterate/traverse through the string to check if all indexes are a numeric value
			//if we find one non-numeric character, the lexeme, 
			if(checkNum(check) == false) {
				return false;
			}
		}
		
		//If the above for loop iterates to completion, we can assume that the lexeme contained all numeric characters
		//This means we can assume the lexeme is a num_value token and thus return true;
		return true;
	}
	
	public static boolean checkNum(char ch) {
		switch(ch) {
		case '0':
		case '1':
		case '2':
		case '3':	
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			return true;
		default:
			return false;
		}
		
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
