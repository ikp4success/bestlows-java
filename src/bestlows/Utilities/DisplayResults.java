package bestlows.Utilities;

public class DisplayResults {
	
	private Double _price = null;
	private String _shopName = null;
	private String _content = null;
	
	public DisplayResults(String string, Double price, String content) {
		_shopName = string;
		_price = price;
		_content = content;
	}

	public Double get_price() {
		return _price;
	}

	public void set_price(Double _price) {
		this._price = _price;
	}

	public String get_shopName() {
		return _shopName;
	}

	public void set_shopName(String _shopName) {
		this._shopName = _shopName;
	}

	public String get_content() {
		return _content;
	}

	public void set_content(String _content) {
		this._content = _content;
	}
	

}
