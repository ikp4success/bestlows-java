package bestlows.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bestlows.Shops.Amazon;
import bestlows.Shops.BestBuy;
import bestlows.Shops.Google;
import bestlows.Shops.Newegg;
import bestlows.Shops.Target;
import bestlows.Shops.TjMaxx;
import bestlows.Shops.Walmart;

public class DisplayResults {
	String _searchParameter = null;
	
	public DisplayResults(String searchParameter) {
		_searchParameter = searchParameter;
	}
	
	public String getDisplayResults() {
		Results amazonResults = new Amazon(_searchParameter).getAmazonResults();
		Results bestbuyResults = new BestBuy(_searchParameter).getBestBuyResults();
//		Results ebayResults = new Ebay(searchParameter).getEbayResults();
		Results walmartResults = new Walmart(_searchParameter).getWalmartResults();
		Results tjmaxxResults = new TjMaxx(_searchParameter).getTjMaxxResults();
//		Results googleResults = new Google(_searchParameter).getGoogleResults();
//		Results targetResults = new Target(_searchParameter).getTargetResults();
		Results neweggResults = new Newegg(_searchParameter).getNeweggResults();
		String displayResults = "";

		List<Results> results = removeEmptyResults(Arrays.asList(amazonResults, bestbuyResults, walmartResults, tjmaxxResults, neweggResults));

		if (results.size() > 1) {
			results.sort((r1, r2) -> {
				return r1.get_sort_price().compareTo(r2.get_sort_price());
			}); // sort price
		}

		for (Results result : results) {
			displayResults += result.displayResults();
		}

		return displayResults;
	}
	
	private List<Results> removeEmptyResults(List<Results> results) {
		List<Results> n_results = new ArrayList<Results>();
		for (Results result : results) {
			if(result != null) {
				n_results.add(result);
			}
		}
		return n_results;
	}

}
