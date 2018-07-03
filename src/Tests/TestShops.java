package Tests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bestlows.Shops.Amazon;
import bestlows.Utilities.Results;

class TestShops {
	private Results amazon_results = null;
	private final String amazon_shopName = "AMAZON";

	@BeforeEach
	void setUp() throws Exception {
		amazon_results = new Amazon("wallet").getAmazonResults();
	}

	@Test
	void testAmazonShop() {
		Assert.assertNotNull(amazon_results);
		Assert.assertNotNull(amazon_results.displayResults());
		Assert.assertNotNull(amazon_results.get_description());
		Assert.assertNotNull(amazon_results.get_image());
		Assert.assertNotNull(amazon_results.get_price());
		Assert.assertNotNull(amazon_results.get_link());
		Assert.assertNotNull(amazon_results.get_shop());
		Assert.assertNotNull(amazon_results.get_title());
		
		Assert.assertEquals(amazon_shopName, amazon_results.get_shop());
	}

}
