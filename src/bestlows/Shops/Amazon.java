package bestlows.Shops;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import bestlows.Utilities.ExtraClass;
import bestlows.Utilities.Results;
import bestlows.Utilities.ShopConnection;

public class Amazon {

	private String _searchKeyword;
	private final String _shopName = "AMAZON";	

	public Amazon(String searchString) {
		_searchKeyword = searchString;
		
	}

	public String get_searchedString() {
		return _searchKeyword;
	}

	public void set_searchString(String searchString) {
		_searchKeyword = searchString;
	}
	
	public String get_ShopName() {
		return _shopName;
	}
	
	public ShopLinks get_shoplink(String value) {
		return new ShopLinks(value);
	}

	public Results getAmazonResults() {
		Results result = null;
		Document doc;
		String bestitemLink = getBestItem();
		doc = new ShopConnection().connect(bestitemLink);
		if (doc != null) {
			result = new Results();
			result.set_title(doc.selectFirst("#titleSection #productTitle").text().trim());
			result.set_description(doc.selectFirst("#featurebullets_feature_div").text().trim());
			result.set_image(doc.select("#imgTagWrapperId img#landingImage").attr("data-old-hires").trim());
			result.set_price(doc.selectFirst("#priceblock_ourprice").text().trim());
			result.set_shopName(_shopName);
			result.set_link(bestitemLink);
		}
		return result;
	}

	private String getBestItem() {
		Document doc;
		String bestItemUrl = null;
		doc = new ShopConnection().connect( get_shoplink(_searchKeyword).get_UrlSearchKeyword());
		if (doc != null) {
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
									if(!bestItemUrl.contains("https://www.amazon.com")) {
										bestItemUrl = "https://www.amazon.com"+bestItemUrl;
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
		}
		return bestItemUrl;
	}

}
