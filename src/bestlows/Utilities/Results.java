package bestlows.Utilities;

import java.net.URISyntaxException;

public class Results {
	private String _title;
	private String _price;
	private String _description;
	private String _image;
	private String _link;
	private String _shop;
	
	public Results() {
		
	}
	
	public Results(String title, String price, String description, String image, String link, String shop) {
		_title = title;
		_price = price;
		_description = description;
		_image = image;
		_link = link;
		_shop = shop;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_price() {
		return _price;
	}
	
	public Double get_sort_price() {
		if (_price != null){
			return Double.parseDouble(_price.replace("$", ""));
		}
		return 0.0;
	}

	public void set_price(String _price) {
		this._price = _price;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public String get_image() {
		return _image;
	}

	public void set_image(String _image) {
		this._image = _image;
	}

	public String get_link() {
		return _link;
	}

	public void set_link(String _link, String domain) {
		try {
			this._link = new ShopConnection().prepend_domain(_link, domain);
		}catch(Exception ex) {
			this._link = null;
		}
	}
	
	public void set_shopName(String shop) {
		this._shop = shop;
	}
	
	public String get_shop() {
		return _shop;
	}
	
	public String displayResults() {
		if(!Valid()) {
			System.out.println(get_title());
			System.out.println(get_price());
			System.out.println(get_link());
			System.out.println(get_description());
			return null;
		}
		try {
			String result_row_html = new DefaultResources().ResultRow();
			final String display_result = result_row_html.replace("{PRODUCTIMAGESOURCE}", ifNullReturnString(get_image()))
					.replace("{PRODUCTTITLE}",ifNullReturnString(get_title()))
					.replace("{PRODUCTPRICE}", ifNullReturnString(get_price()))
					.replace("{PRODUCTLINK}", ifNullReturnString(get_link()))
					.replace("{PRODUCTDESCRIPTION}", ifNullReturnString(get_description()))
					.replace("{PRODUCTSHOPNAME}", ifNullReturnString(get_shop()));
			return display_result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Somthing went wrong somewhere";
		}
	}
	
	private boolean Valid() {
		return IfNullEmpty(get_title())|| IfNullEmpty(get_price())|| IfNullEmpty(get_link());
	}
	
	private boolean IfNullEmpty(String value) {
		return new ExtraClass().iSNotNullEmpty(value);
	}
	
	private String ifNullReturnString(String value) {
		return new ExtraClass().ifNullReturnString(value);
	}
	
}
