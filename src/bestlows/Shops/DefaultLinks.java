package bestlows.Shops;

public class DefaultLinks {

	private final String _amazonurl = "https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords={SearchString}&sort=price-asc-rank";
	private final String _bestbuyurl = "https://www.bestbuy.com/site/searchpage.jsp?st={SearchString}&_dyncharset=UTF-8&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys";
	
	public DefaultLinks() {
		
	}
	public String get_amazonurl() {
		return _amazonurl;
	}
	
	public String get_bestbuyurl() {
		return _bestbuyurl;
	}


}
