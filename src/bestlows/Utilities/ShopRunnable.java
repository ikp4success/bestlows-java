package bestlows.Utilities;

class ShopRunnable implements Runnable {
	private Thread t;
	private ShopNames _shop_name;
	Results shop_result = null;

	private String _searchParameter;

	ShopRunnable(ShopNames shop_name, String searchParameter) {
		_shop_name = shop_name;
		_searchParameter = searchParameter;
	}

	public void run() {
		shop_result = new ShopClassBucket().GetShopClass(_shop_name, _searchParameter);
	}

	public void start() {
		System.out.println("Starting " + _shop_name);
		if (t == null) {
			t = new Thread(this, _shop_name.toString());
			t.start();
		}
	}

	public DisplayResults get_results() {
		if (shop_result != null) {
			DisplayResults displayResults = new DisplayResults(shop_result.get_shop(), shop_result.get_sort_price(), shop_result.displayResults());
			return displayResults;
		}
		return null;
	}

	public void join() {
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
