package bestlows.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtraClass {

	public ExtraClass() {

	}

	public Integer isInteger(String text) {
		if (text.isEmpty() || text == null) {
			return -1;
		}
		try {
			return Integer.parseInt(text);

		} catch (NumberFormatException ex) {
			return -1;
		}
	}

	public String ifNullReturnString(String value) {
		if (value == null)
			return "";
		return value;
	}

	public boolean iSNotNullEmpty(String value) {
		return value!="" || value != null;
	}

	private Matcher get_matcher(String pattern_val, String result) {
		if (result == null) {
			return null;
		}
		Pattern pattern = Pattern.compile(pattern_val);
		Matcher matcher = pattern.matcher(result);
		if (matcher.find()) {
			return matcher;
		}
		return null;
	}

	public String str_get_match_group(String pattern_val, String result) {
		Matcher matcher = get_matcher(pattern_val, result);
		if (matcher == null) {
			return result;
		}
		return matcher.group();
	}

	public String str_get_match(String pattern_val, String result, int group_index) {
		Matcher matcher = get_matcher(pattern_val, result);
		if(group_index  < 0) {
			return result;
		}
		
		if (matcher == null) {
			return result;
		}
		
		if (group_index >= matcher.group().length()) {
			return result;
		}
		
		try {
		   return matcher.group(group_index);
		}catch(Exception ex){
			return null;
		}
		
	}

}
