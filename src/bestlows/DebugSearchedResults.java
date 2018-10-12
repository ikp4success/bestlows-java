package bestlows;

import bestlows.Shops.Amazon;
import bestlows.Shops.BestBuy;
import bestlows.Shops.Ebay;
import bestlows.Shops.TjMaxx;
import bestlows.Shops.Walmart;
import bestlows.Utilities.Results;

public class DebugSearchedResults {

	public static void main(String[] args) {
//		Results amazonResults = new Amazon("television").getAmazonResults();
//		Results bestbuyResults = new BestBuy("printer").getBestBuyResults();
//		Results ebayResults = new Ebay("cats").getEbayResults();
//		Results walmartResults = new Walmart("television").getWalmartResults();
		Results tjmaxtResults = new TjMaxx("wallet").getTjMaxxResults();
		System.out.println(tjmaxtResults.displayResults());
	}

}
