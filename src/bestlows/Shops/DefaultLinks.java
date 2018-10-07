package bestlows.Shops;

public class DefaultLinks {

	private final String _amazonurl = "https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords={SearchString}&sort=price-asc-rank";
	private final String _bestbuyurl = "https://www.bestbuy.com/site/searchpage.jsp?_dyncharset=UTF-8&cp=1&id=pcat17071&iht=y&keys=keys&ks=960&list=n&sc=Global&st={SearchString}&type=page&usc=All%20Categories";
	
	public DefaultLinks() {
		
	}
	public String get_amazonurl() {
		return _amazonurl;
	}
	
	public String get_bestbuyurl() {
		return _bestbuyurl;
	}


}
