package bestlows.Shops;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bestlows.Utilities.ExtraClass;
import bestlows.Utilities.Results;
import bestlows.Utilities.ShopNames;
import bestlows.Utilities.ShopConnection;

public class Amazon {

	private String _searchKeyword;
	private final ShopNames _shopName = ShopNames.AMAZON;
	private final Boolean _active = true;

	public Amazon(String searchString) {
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
		return new ShopLinks(value, new DefaultLinks().get_amazonurl());
	}

	public Results getAmazonResults() {
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
			Element title = doc.selectFirst("#titleSection #productTitle");
			Element description = doc.selectFirst("#featurebullets_feature_div");
			Element image = doc.selectFirst("#imgTagWrapperId img#landingImage");
			Element price = doc.selectFirst("#priceblock_ourprice");

			result.set_shopName(get_ShopName());
			result.set_link(bestitemLink, parent_url);
			if (image != null) {
				result.set_image(image.attr("data-old-hires").trim());
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
		String bestItemUrl = null;
		doc = new ShopConnection().connect_default(get_shoplink(_searchKeyword).get_UrlSearchKeyword());
		//System.out.println(doc);

		if (doc == null) {
			return null;
		}
		if (doc.select("#noResultsTitle:contains(did not match any products)") != null
				&& !doc.select("#noResultsTitle:contains(did not match any products)").text().isEmpty()) {

			System.out.println("ERROR");
			return null;
		}

		Elements items_1 = doc.select("div ul#s-results-list-atf li");
		int maxratingcurrent = 0;

		while (items_1.next() != null) {

			if (!items_1.html().contains("Top Rated from Our Brands")) {
				Elements items_2 = items_1.select("a");
				for (int i = 0; items_2.size() > i; i++) {
					String item = items_2.get(i).attr("href");
					if (item.contains(get_shoplink(_searchKeyword).get_UrlEncodeSearchString() + "#customerReviews")) {
						String fitem = items_2.get(i).text();
						if (new ExtraClass().isInteger(fitem) != -1) {
							int ratingcurrent = Integer.parseInt(fitem);
							if (ratingcurrent > maxratingcurrent) {
								maxratingcurrent = ratingcurrent;
								bestItemUrl = items_2.get(0).attr("href");
								System.out.println(bestItemUrl);
								if (!bestItemUrl.contains("https://www.amazon.com")) {
									bestItemUrl = "https://www.amazon.com" + bestItemUrl;
								}
								System.out.println(maxratingcurrent);
								System.out.println(bestItemUrl);
								System.out.println(get_shoplink(_searchKeyword).get_UrlEncodeSearchString());
								break;
							}
						}
					}
				}
			}

			if (maxratingcurrent > 0) {
				break;
			}
			items_1 = items_1.next();
		}

		return bestItemUrl;
	}

}
