package bestlows.Utilities;

public class ExtraClass {
	
	public ExtraClass() {
		
	}
	
	public Integer isInteger(String text) {
		if(text.isEmpty() || text ==null) {
			return -1;
		}
		try
	    {
	        return Integer.parseInt(text);
	       
	    } catch (NumberFormatException ex)
	    {
	        return -1;
	    }
	}
	
	public String ifNullReturnString(String value) {
		if(value==null) 
			return "";
		return value;
	}
	
	public boolean iSNotNullEmpty(String value) {
		return !value.isEmpty() || value!=null;
	}

}
