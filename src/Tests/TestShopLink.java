package Tests;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bestlows.Shops.DefaultLinks;
import bestlows.Shops.ShopLinks;
import bestlows.Utilities.ShopConnection;

class TestShopLink {
	
	private final String shop_searchkeyword_test = "wallet";
	ShopLinks amazon_shoplink = null;

	@BeforeEach
	void setUp() throws Exception {
		/**Amazon**/
		amazon_shoplink = new ShopLinks(shop_searchkeyword_test, new DefaultLinks().get_amazonurl());
	}

	@Test
	void testShopLinks() {
		/**Amazon**/
		Assert.assertNotNull(amazon_shoplink);
		Assert.assertNotNull(TestShopConnection(amazon_shoplink.get_UrlSearchKeyword()));
		
	}
	
	private Document TestShopConnection(String url) {
		return new ShopConnection().connect(url);
	}

}
