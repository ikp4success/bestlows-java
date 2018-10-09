package bestlows.Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ShopConnection {
	
	private int _timeout = 999999;
	private String _userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0";
	
	
	public ShopConnection() {
		
	}
	
	public Document connect(final String url) {
		try {
			return Jsoup.connect(url).timeout(_timeout).userAgent(_userAgent).referrer(url).followRedirects(true).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Document DynamicConnection(final String url) {
		// TODO still need some fixing 
		System.setProperty("webdriver.gecko.driver", "ExternalResources/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
		//Document doc = connect(driver.getPageSource());
		Document doc = Jsoup.parse(driver.getPageSource());
		return doc;
	}
	
//	public Document connect_loadjs(final string url) {
//		URL url = new URL(url);
//		InputStream is = url.openStream();
//		int ptr = 0;
//		StringBuffer buffer = new StringBuffer();
//		while ((ptr = is.read()) != -1) {
//		    buffer.append((char)ptr);
//		}
//	}

}
