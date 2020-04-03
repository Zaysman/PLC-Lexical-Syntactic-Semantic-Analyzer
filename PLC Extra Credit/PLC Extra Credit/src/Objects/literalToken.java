package Objects;
//imports
import Objects.Token;

public class literalToken extends Token {

	private String type;
	
	/*
	 * Constructor for literal token
	 */
	public literalToken(String lit) {
		if(lit.compareTo("true") == 0) {
			this.type = "true_value";
			this.code = 20;
		} else if(lit.compareTo("false") == 0) {
			this.type = "false_value";
			this.code = 21;
		}
	}
	
	/*
	 * Construct for real/num literals
	 */
	
	public literalToken(String lit, String tokenType) {
		if(tokenType.compareTo("real") == 0) {
			this.type = "real_value";
			this.code = 22;
		} else if(tokenType.compareTo("num") == 0) {
			this.type = "num_value";
			this.code = 23;
		}
		
		
		
	}
}
