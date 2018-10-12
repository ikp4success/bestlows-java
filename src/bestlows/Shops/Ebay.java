package bestlows.Shops;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bestlows.Utilities.ExtraClass;
import bestlows.Utilities.Results;
import bestlows.Utilities.ShopConnection;
import bestlows.Utilities.ShopNames;

public class Ebay {
	private String _searchKeyword;
	private final ShopNames _shopName = ShopNames.EBAY;
	private final Boolean _active = false;

	public Ebay(String searchString) {
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
		return new ShopLinks(value, new DefaultLinks().get_ebayurl());
	}

	public Results getEbayResults() {
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
			Element title = doc.selectFirst("#itemTitle");
			Element description = doc.selectFirst("#prodDetailSec");
			Element image = doc.selectFirst("#mainImgHldr #icImg img");
			Element price = doc.selectFirst("#prcIsum");

			result.set_shopName(get_ShopName());
			result.set_link(bestitemLink, parent_url);
			if (image != null) {
				result.set_image(image.attr("src").trim());
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
		ExtraClass extra = new ExtraClass();
		Document doc;
		String item_url = null;
		doc = new ShopConnection().native_curl(get_shoplink(_searchKeyword).get_UrlSearchKeyword(), get_shoplink(_searchKeyword).get_UrlSearchKeyword());

		if (doc == null) {
			return null;
		}

		Elements items = doc
				.select("#ResultSetItems #ListViewInner .lvresult, #ResultSetItems #GalleryViewInner .lvresult");
		Double low_price = 0.0;
		Element low_item = null;
		for (Element item : items) {
			try {
				Element ispromoted = item.selectFirst(".promoted-lv");
				if (ispromoted != null) {
					continue;
				}
				Element price = item.selectFirst(".lvprice span");
				if (price != null) {
					String price_str = price.text().replace("$", "").replace(",", "");
					if (price_str.contains("Trending") || price_str.contains("to") || price_str.contains("List")) {
						price_str = extra.str_get_match("(.*?)\\s", price_str, 1);
						if (price_str == null) {
							continue;
						}
					}
					if (price_str != null || price_str != "") {
						Double price_num = Double.parseDouble(price_str);
						if (price_num > 0 && low_price == 0.0) {
							low_price = price_num;
						} else if (price_num < low_price) {
							low_price = price_num;
							low_item = item;
						}
					}
				}
			} catch (Exception ex) {
				continue;
			}
		}

		if (low_item != null) {
			item_url = low_item.selectFirst("a").attr("href");
		}

		return item_url;
	}

}
