package bestlows.Shops;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bestlows.Utilities.ExtraClass;
import bestlows.Utilities.Results;
import bestlows.Utilities.ShopConnection;
import bestlows.Utilities.ShopNames;

public class Newegg {
	private String _searchKeyword;
	private final ShopNames _shopName = ShopNames.NEWEGG;
	private final Boolean _active = true;
	private String _price_element = "";

	public Newegg(String searchString) {
		_searchKeyword = searchString;

	}
	
	public Boolean get_status() {
		return _active;
	}

	public String get_searchedString() {
		return _searchKeyword;
	}

	public void set_searchString(String searchString) {
		_searchKeyword = searchString;
	}

	public String get_ShopName() {
		return _shopName.toString();
	}

	public ShopLinks get_shoplink(String value) {
		return new ShopLinks(value, new DefaultLinks().get_neweggurl());
	}

	public Results getResults() {
		String parent_url = get_shoplink(_searchKeyword).get_UrlSearchKeyword();
		Results result = null;
		Document doc;
		String bestitemLink = getBestItem();
		if (bestitemLink == null) {
			return null;
		}
		doc = new ShopConnection().connect_okhttpclient(bestitemLink, parent_url);
		if (doc != null) {
			result = new Results();
			Element title = doc.selectFirst("#grpDescrip_h");
			Element description = doc.selectFirst(".itemDesc");
			Element image = doc.selectFirst(".mainSlide img");

			result.set_shopName(get_ShopName());
			result.set_link(bestitemLink, parent_url);
			if (image != null) {
				result.set_image(image.attr("src").trim(), parent_url);
			}
			if (title != null) {
				result.set_title(title.text().trim());
			}
			if (_price_element != null) {
				result.set_price("$" + _price_element);
			}
			if (description != null) {
				result.set_description(description.text().trim());
			} else {
				return null;
			}
		}
		
		return result;
		
		
	}
	
	private String getBestItem() {
		Document doc;
		String item_url = null;
		doc = new ShopConnection().connect_okhttpclient_default(get_shoplink(_searchKeyword).get_UrlSearchKeyword());
		if (doc == null) {
			return null;
		}
		
		Element item = doc.selectFirst(".item-container a");
		Element price_1 = doc.selectFirst(".price-current strong");
		Element price_2 = doc.selectFirst(".price-current sup");
		
		if (item != null) {
			item_url = item.attr("href");
		}
		if (price_1 != null) {
			_price_element += price_1.text().trim();
		}
		if (price_2 != null) {
			_price_element += price_2.text().trim();
		}
			
		return item_url;
	}


}
