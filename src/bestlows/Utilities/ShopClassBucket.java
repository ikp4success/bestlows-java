package bestlows.Utilities;

import bestlows.Shops.Amazon;
import bestlows.Shops.BestBuy;
import bestlows.Shops.Ebay;
import bestlows.Shops.Google;
import bestlows.Shops.Newegg;
import bestlows.Shops.Target;
import bestlows.Shops.TjMaxx;
import bestlows.Shops.Walmart;

public class ShopClassBucket {
	String display_content;

	ShopClassBucket() {

	}

	public Results GetShopClass(ShopNames shop_name, String searchParameter) {
		if (shop_name == ShopNames.AMAZON) {
			Amazon amazon = new Amazon(searchParameter);
			if (amazon.get_status()) {
				return amazon.getResults();
			}
		}
		if (shop_name == ShopNames.WALMART) {
			Walmart walmart = new Walmart(searchParameter);
			if (walmart.get_status()) {
				return walmart.getResults();
			}
		}
		if (shop_name == ShopNames.BESTBUY) {
			BestBuy bestbuy = new BestBuy(searchParameter);
			if (bestbuy.get_status()) {
				return bestbuy.getResults();
			}
		}
		if (shop_name == ShopNames.NEWEGG) {
			Newegg newegg = new Newegg(searchParameter);
			if (newegg.get_status()) {
				return newegg.getResults();
			}
		}
		if (shop_name == ShopNames.TJMAXX) {
			TjMaxx tjmaxx = new TjMaxx(searchParameter);
			if (tjmaxx.get_status()) {
				return tjmaxx.getResults();
			}
		}
		if (shop_name == ShopNames.EBAY) {
			Ebay ebay = new Ebay(searchParameter);
			if (ebay.get_status()) {
				return ebay.getResults();
			}
		}
		if (shop_name == ShopNames.TARGET) {
			Target target = new Target(searchParameter);
			if (target.get_status()) {
				return target.getResults();
			}
		}
		if (shop_name == ShopNames.GOOGLE) {
			Google google = new Google(searchParameter);
			if (google.get_status()) {
				return google.getResults();
			}
		}
		return null;
	}

}
