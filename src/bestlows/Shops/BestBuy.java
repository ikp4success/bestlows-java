package bestlows.Shops;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import bestlows.Utilities.Results;
import bestlows.Utilities.ShopConnection;
import bestlows.Utilities.ShopNames;

public class BestBuy {
	private String _searchKeyword;
	private final ShopNames _shopName = ShopNames.BESTBUY;
	private final Boolean _active = true;

	public BestBuy(String searchString) {
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
		return new ShopLinks(value, new DefaultLinks().get_bestbuyurl());
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
			Element title = doc.selectFirst("#sku-title");
			Element description = doc.selectFirst("#long-description");
			Element image = doc.selectFirst(".shop-media-gallery img.primary-image");
			Element price = doc.selectFirst(".priceView-hero-price");

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
		doc = new ShopConnection().connect_default(get_shoplink(_searchKeyword).get_UrlSearchKeyword());
		if (doc == null) {
			return null;
		}
		
		Element item = doc.selectFirst(".sku-item .sku-header a");
		
		if (item != null) {
			item_url = item.attr("href");
		}
			
		return item_url;
	}


}
