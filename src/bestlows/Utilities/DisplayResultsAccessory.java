package bestlows.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bestlows.Shops.Amazon;
import bestlows.Shops.BestBuy;
import bestlows.Shops.Ebay;
import bestlows.Shops.Google;
import bestlows.Shops.Newegg;
import bestlows.Shops.Target;
import bestlows.Shops.TjMaxx;
import bestlows.Shops.Walmart;

public class DisplayResultsAccessory {
	String _searchParameter = null;
	int _results_size = 0;
	
	public DisplayResultsAccessory(String searchParameter, int results_size) {
		_searchParameter = searchParameter;
		_results_size = results_size;
	}
	
	
	public String getDisplayResults() {
		
		String displayResults = "";

		List<DisplayResults> dp_list = new ArrayList<DisplayResults>();
		
		List<ShopNames> shops = new ArrayList<ShopNames>(Arrays.asList(ShopNames.values()));
		
		for(int i = 0; shops.size()> i; i++) {
			ShopNames shop = shops.get(i);
			ShopRunnable shop_r = new ShopRunnable(shop, _searchParameter);
			shop_r.start();
			shop_r.join();
			
			DisplayResults shop_r_result = shop_r.get_results();
			
			if (shop_r_result != null) {
				dp_list.add(shop_r_result);
				List<DisplayResults> dp_list_sorted = dp_list_sort_by_low_price(dp_list);
				
				displayResults = append_valid_result(dp_list_sorted);
				
				if(i > dp_list.size()) {
					break;
				}
			}
			
		}
		
		return displayResults;
		
	}
	
	private List<DisplayResults> dp_list_sort_by_low_price(List<DisplayResults> dp_list) {
		if (dp_list.size() > 1) {
			dp_list.sort((r1, r2) -> {
				return r1.get_price().compareTo(r2.get_price());
			}); // sort price
		}
		return dp_list;
	}
	
	private String append_valid_result(List<DisplayResults> new_result) {
		String displayResults = "";
		for(DisplayResults rs: new_result) {
			displayResults += rs.get_content();
		}
		return displayResults;
	}

}
