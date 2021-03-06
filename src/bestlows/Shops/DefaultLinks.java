package bestlows.Shops;

public class DefaultLinks {

	private final String _amazonurl = "https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords={SearchString}&sort=price-asc-rank";
	private final String _bestbuyurl = "https://www.bestbuy.com/site/searchpage.jsp?st={SearchString}&_dyncharset=UTF-8&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys";
	private final String _ebayurl = "https://www.ebay.com/sch/i.html?_from=R40&_sacat=0&_nkw={SearchString}&rt=nc&LH_PrefLoc=1&_ipg=25";
	private final String _walmarturl = "https://www.walmart.com/search/?page=1&query={SearchString}&sort=price_low#searchProductResult";
	private final String _tjmaxxurl = "https://tjmaxx.tjx.com/store/shop?Nr=AND%28OR%28product.catalogId%3Atjmaxx%29%2Cproduct.siteId%3Atjmaxx%2CisEarlyAccess%3Afalse%29&Ns=product.minListPrice%7C0%7C%7Cproduct.inventory%7C1&Ntt={SearchString}&_dyncharset=utf-8&initSubmit=true&qfh_sch=Search&tag=srt";
	private final String _googleurl = "https://www.google.com/search?q={SearchString}&sa=X&tbas=0&biw=1920&bih=966&tbm=shop&tbs=p_ord:p";
	private final String _targeturl = "https://www.target.com/s?searchTerm={SearchString}&sortBy=PriceLow&Nao=0";
	private final String _neweggurl = "https://www.newegg.com/Product/ProductList.aspx?Submit=ENE&N=-1&IsNodeId=1&Description={SearchString}&bop=And&Order=BESTSELLING&PageSize=36"; 
	
	public DefaultLinks() {
		
	}
	public String get_amazonurl() {
		return _amazonurl;
	}
	
	public String get_bestbuyurl() {
		return _bestbuyurl;
	}
	
	public String get_ebayurl() {
		return _ebayurl;
	}
	
	public String get_walmarturl() {
		return _walmarturl;
	}
	public String get_tjmaxxurl() {
		return _tjmaxxurl;
	}
	public String get_googleshopurl() {
		return _googleurl;
	}
	public String get_targeturl() {
		return _targeturl;
	}
	public String get_neweggurl() {
		return _neweggurl;
	}


}
