package bestlows.Shops;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import bestlows.Utilities.Results;
import bestlows.Utilities.ShopConnection;
import bestlows.Utilities.ShopNames;

public class Walmart {
	private String _searchKeyword;
	private final ShopNames _shopName = ShopNames.WALMART;
	private final Boolean _active = true;

	public Walmart(String searchString) {
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
		return new ShopLinks(value, new DefaultLinks().get_walmarturl());
	}

	public Results getWalmartResults() {
		String parent_url = get_shoplink(_searchKeyword).get_UrlSearchKeyword();
		Results result = null;
		Document doc;
		String bestitemLink = getBestItem();
		if (bestitemLink == null) {
			return null;
		}
		doc = new ShopConnection().connect(bestitemLink, get_shoplink(_searchKeyword).get_UrlSearchKeyword());
		if (doc != null) {
			result = new Results();
			Element title = doc.selectFirst(".ProductTitle div");
			Element description = doc.selectFirst(".about-desc");
			Element image = doc.selectFirst(".prod-hero-image-image");
			Element price = doc.selectFirst(".prod-PriceHero span");

			result.set_shopName(get_ShopName());
			result.set_link(bestitemLink, parent_url);
			if (image != null) {
				result.set_image(image.attr("src").trim(), parent_url);
			}
			if (title != null) {
				result.set_title(title.text().trim());
			}
			if (price != null) {
				result.set_price(price.text().replace("US", "").trim());
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
		
		Element item = doc.selectFirst("#searchProductResult .search-result-gridview-items li a");
		
		if (item != null) {
			item_url = item.attr("href");
		}
			
		return item_url;
	}

}
