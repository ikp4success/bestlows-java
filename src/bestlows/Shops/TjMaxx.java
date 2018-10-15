package bestlows.Shops;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bestlows.Utilities.Results;
import bestlows.Utilities.ShopConnection;
import bestlows.Utilities.ShopNames;

public class TjMaxx {
	private String _searchKeyword;
	private final ShopNames _shopName = ShopNames.TJMAXX;
	private final Boolean _active = true;

	public TjMaxx(String searchString) {
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
		return new ShopLinks(value, new DefaultLinks().get_tjmaxxurl());
	}

	public Results getTjMaxxResults() {
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
			Element brand = doc.selectFirst(".product-brand");
			Element title = doc.selectFirst(".product-title");
			Elements descriptions = doc.select(".description-list li");
			Element image = doc.selectFirst(".main-image");
			Element price = doc.selectFirst(".product-price");

			result.set_shopName(get_ShopName());
			result.set_link(bestitemLink, parent_url);
			if (image != null) {
				result.set_image("https:" + image.attr("src").trim(), parent_url);
			}
			if (title != null) {
				if(brand != null) {
					result.set_title(brand.text().trim() + " " + title.text().trim());
				}else {
					result.set_title(title.text().trim());
				}
			}
			if (price != null) {
				result.set_price(price.text().trim());
			}
			if (descriptions != null) {
				String content = "";
				for(Element description: descriptions) {
					content += description.text().trim() + "\n";
				}
				result.set_description(content);
				
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
		
		Elements items = doc.select(".product-details .product-title");
		Element link = doc.selectFirst(".product-image a.product-link"); // get first item
		int i = 0;
		for(Element item: items) {
			if (item != null) {
				if(item.text().toLowerCase().contains(_searchKeyword.toLowerCase())) { // try to get best item, else use first item
					link = doc.select(".product-image a.product-link").get(i);
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
