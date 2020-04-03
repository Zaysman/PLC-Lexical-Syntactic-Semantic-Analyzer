package Objects;
import Objects.Token;

public class KeywordToken extends Token {

	private String type; //Holds what type of string we're working with
	
	/*
	 * public constructor
	 */
	
	public KeywordToken(String key) {
		if(key.compareTo("new") == 0) {
			this.type = "new_token";
			this.code = 4;
		} else if(key.compareTo("num") == 0) {
			this.type = "num_token";
			this.code = 5;
		} else if(key.compareTo("real") == 0) {
			this.type = "real_value";
			this.code = 6;
		} else if(key.compareTo("Sentence") == 0) {
			this.type = "sentence_keyword";
			this.code = 7;
		} else if(key.compareTo("is") == 0) {
			this.type = "is_keyword";
			this.code = 8;
		} else if(key.compareTo("isnot") == 0) {
			this.type = "isnot_key";
			this.code = 9;
		} else if(key.compareTo("for") == 0) {
			this.type = "for_keyword";
			this.code = 10;
		} else if(key.compareTo("until") == 0) {
			this.type = "until_keyword";
			this.code = 11;
		} else if(key.compareTo("perform") == 0) {
			this.type = "perform_keyword";
			this.code = 12;
		} else if(key.compareTo("open") == 0) {
			this.type = "open_keyword";
			this.code = 13;
		} else if(key.compareTo("closed") == 0) {
			this.type = "closed_keyword";
			this.code = 14;
		} else if(key.compareTo("empty") == 0) {
			this.type = "empty_keyword";
			this.code = 15;
		} else if(key.compareTo("skip") == 0) {
			this.type = "skip_keyword";
			this.code = 16;
		} else if(key.compareTo("jump") == 0) {
			this.type = "jump_keyword";
			this.code = 17;
		} else if(key.compareTo("jump_out") == 0) {
			this.type = "jump_out_keyword";
			this.code = 18;
		} else if(key.compareTo("bool") == 0) {
			this.type = "bool_keyword";
			this.code = 19;
		}
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getCode() {
		return this.code;
	}
}
