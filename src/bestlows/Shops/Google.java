package bestlows.Shops;



import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import bestlows.Utilities.Results;
import bestlows.Utilities.ShopConnection;
import bestlows.Utilities.ShopNames;

public class Google {
	private String _searchKeyword;
	private final ShopNames _shopName = ShopNames.GOOGLE;
	private final Boolean _active = false;

	public Google(String searchString) {
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
		return new ShopLinks(value, new DefaultLinks().get_googleshopurl());
	}

	public Results getGoogleResults() {
		String parent_url = get_shoplink(_searchKeyword).get_UrlSearchKeyword();
		Document doc;
		String item_url = null;
		Results result = null;
		doc = new ShopConnection().connect_default(parent_url);

		if (doc == null) {
			return result;
		}

		result = new Results();
		Element item = doc.selectFirst(".sh-pr__product-results div a");
		Element title = doc.selectFirst(".sh-pr__product-results div a");
		Element image = doc.selectFirst(".MUQY0 img");
		Element price = doc.selectFirst(".kD8n3 span.O8U6h");

		if (item != null) {
			item_url = item.attr("href");
		}

		result.set_shopName(get_ShopName());
		result.set_link(item_url, parent_url);
		if (image != null) {
			result.set_image(image.attr("src").trim(), parent_url);
		}
		if (title != null) {
			result.set_title(item.text().trim());
		}
		if (price != null) {
			result.set_price(price.text().replace("US", "").trim());
		} else {
			return null;
		}

		return result;
	}

}
