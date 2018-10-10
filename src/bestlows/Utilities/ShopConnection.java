package bestlows.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ShopConnection {

	private int _timeout = 999999;
	private String _userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0";

	public ShopConnection() {

	}
	
	public Document connect_default(String url) {
		try {
			return Jsoup.connect(url).timeout(_timeout).userAgent(_userAgent).referrer(url).followRedirects(true).get();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Document connect(String url, String parent_url) {
		try {
			String final_url = prepend_domain(url, parent_url);
			return connect_default(final_url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Document dynamic_connect(final String url) {
		// TODO still need some fixing
		System.setProperty("webdriver.gecko.driver", "ExternalResources/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
		// Document doc = connect(driver.getPageSource());
		Document doc = Jsoup.parse(driver.getPageSource());
		return doc;
	}

	public Document native_curl(String url, String parent_url) {
		String content = "";
		URL curl_url;
		try {
			String final_url = prepend_domain(url, parent_url);
			curl_url = new URL(final_url);
			BufferedReader reader = new BufferedReader(new InputStreamReader(curl_url.openStream(), "UTF-8"));
			for (String line; (line = reader.readLine()) != null;) {
				content += line;
			}
			return Jsoup.parse(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private String prepend_domain(String url, String domain) throws URISyntaxException {
		domain = get_domain_name(domain);
		if (domain != null) {
			if (!url.startsWith("http")) {
				return domain + url;
			}
			if (!url.startsWith("http") && !url.startsWith("/")) {
				return domain + "/" + url;
			}
		}
		return url;
	}

	private String get_domain_name(String url) throws URISyntaxException {
		URI uri = new URI(url);
		String domain = uri.getScheme() +"://" +uri.getHost();
		return domain;
	}

}
