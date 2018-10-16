package bestlows.Shops;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bestlows.Utilities.Results;
import bestlows.Utilities.ShopConnection;
import bestlows.Utilities.ShopNames;

public class Target {
	private String _searchKeyword;
	private final ShopNames _shopName = ShopNames.TARGET;
	private final Boolean _active = false;

	public Target(String searchString) {
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
		return new ShopLinks(value, new DefaultLinks().get_targeturl());
	}

	public Results getTargetResults() {
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
			Element title = doc.selectFirst("span[data-test=product-title]");
			Element description = doc.selectFirst("div[data-test=detailsTab]");
			Element image = doc.selectFirst("div[data-test=product-image] img");
			Element price = doc.selectFirst("span[data-test=product-price]");

			result.set_shopName(get_ShopName());
			result.set_link(bestitemLink, parent_url);
			if (image != null) {
				result.set_image(image.attr("src").trim(), parent_url);
			}
			if (title != null) {
				result.set_title(title.text().trim());
			}
			if (price != null) {
				result.set_price(price.text().trim());
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
		
		Elements items = doc.select("div[data-test=productGridContainer] li");
		Element link = doc.selectFirst("div[data-test=productGridContainer] li"); // get first item
		int i = 0;
		for(Element item: items) {
			if (item != null) {
				if(item.text().toLowerCase().contains(_searchKeyword.toLowerCase())) { // try to get best item, else use first item
					link = doc.select("a").get(i);
					break;
				}
			}
			i++;
		}
		
		if (link != null) {
			item_url = link.attr("href"); // release item
		}
			
		return item_url;
	}

}
