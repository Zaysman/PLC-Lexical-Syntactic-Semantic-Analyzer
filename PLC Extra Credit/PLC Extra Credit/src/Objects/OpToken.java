package Objects;
import Objects.Token;



/*
 * This class is for operator tokens in our programming language
 */
public class OpToken extends Token {

	private String type;

	/*
	 * Constructor
	 */
	public OpToken(String op) {
		if(op.compareTo("+") == 0) {
			this.type = "add_op";
			this.code = 0;
					
		} else if(op.compareTo("-") == 0) {
			this.type = "sub_op";
			this.code = 1;

		} else if(op.compareTo("*") == 0) {
			this.type = "multi_op";
			this.code = 2;

		} else if(op.compareTo("/") == 0) {
			this.type = "divis_op";
			this.code = 3;
		}

	}
	
	public String getType() {
		return this.type;
	}
	
	public int getCode() {
		return this.code;
	}

}
