package bestlows.Utilities;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ShopConnection {
	
	private int _timeout = 6999999;
	private String _userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0";
	
	public ShopConnection() {
		
	}
	
	public Document connect(final String url) {
		try {
			return Jsoup.connect(url).timeout(_timeout).userAgent(_userAgent).followRedirects(true).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Document DynamicConnection(final String url) {
		// TODO still need some fixing 
		System.setProperty("webdriver.gecko.driver", "src/bestlows/Utilities/gecko_folder/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
		Document doc = connect(driver.getPageSource());
		return doc;
	}

}
