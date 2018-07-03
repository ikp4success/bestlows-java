package bestlows.Utilities;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ShopConnection {
	
	private int _timeout = 6999999;
	
	public ShopConnection() {
		
	}
	
	public Document connect(final String url) {
		try {
			return Jsoup.connect(url).timeout(_timeout).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
