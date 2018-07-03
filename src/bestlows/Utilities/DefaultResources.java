package bestlows.Utilities;

public class DefaultResources {
	
	private final  String _resultRow = " <div class=\"col-lg-3 col-md-4 col-sm-6 portfolio-item\">\r\n" + 
			"          <div class=\"card h-100\">\r\n" + 
			"            <a href=\"#\"><img class=\"card-img-top\" src=\"{PRODUCTIMAGESOURCE}\" alt=\"\"></a>\r\n" + 
			"            <div class=\"card-body\">\r\n" + 
			"              <h4 class=\"card-title\">\r\n" + 
			"                <a href=\"{PRODUCTLINK}\">{PRODUCTTITLE}</a>\r\n" + 
			"              </h4>\r\n" + 
			"              <p class=\"card-text\">{PRODUCTDESCRIPTION}</p>\r\n" + 
			"              <p class=\"card-text\">Price: {PRODUCTPRICE}</p>\r\n" + 
			"              <p class=\"card-text\">Shop: {PRODUCTSHOPNAME}</p>\r\n" + 
			"            </div>\r\n" + 
			"          </div>\r\n" + 
			"        </div>";
	public DefaultResources() {
		
	}
	
	public String ResultRow() {
		return _resultRow;
	}

}
