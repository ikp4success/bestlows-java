package bestlows;

import bestlows.Shops.Amazon;
import bestlows.Shops.BestBuy;
import bestlows.Utilities.Results;

public class DebugSearchedResults {

	public static void main(String[] args) {
//		Results amazonResults = new Amazon("television").getAmazonResults();
		Results bestbuyResults = new BestBuy("printer").getBestBuyResults();
		System.out.println(bestbuyResults.displayResults());
	}

}
