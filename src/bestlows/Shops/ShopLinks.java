package bestlows.Shops;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ShopLinks {
	/***
	 * Shop links
	 * **/
	
	private String _searchKeyword;
	private String _url;
	
	public ShopLinks(String searchKeyword, String url) {
		_searchKeyword = searchKeyword;
		_url = url;
	}
	
	public void set_shoplink(String value) {
		this._searchKeyword = value;
	}
	
	public void set_shopurl(String value) {
		this._url = value;
	}
	
	public String get_UrlEncodeSearchString() {
		try {
			return URLEncoder.encode(_searchKeyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return _searchKeyword;
		}
	}
	
	public String get_UrlSearchKeyword() {
		return  _url.replace("{SearchString}", get_UrlEncodeSearchString());
	}
}
