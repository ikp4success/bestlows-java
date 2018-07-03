package bestlows.Shops;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ShopLinks {
	private final String _amazonurl = "https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords={SearchString}&sort=price-asc-rank";
	
	private String _searchKeyword;
	
	public ShopLinks(String searchKeyword) {
		_searchKeyword = searchKeyword;
	}
	
	public void set_shoplink(String value) {
		this._searchKeyword = value;
	}
	
	public String get_UrlEncodeSearchString() {
		try {
			return URLEncoder.encode(_searchKeyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return _searchKeyword;
		}
	}
	
	public String get_UrlSearchKeyword() {
		return  _amazonurl.replace("{SearchString}", get_UrlEncodeSearchString());
	}
}
