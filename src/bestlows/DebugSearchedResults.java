package bestlows;

import bestlows.Shops.Amazon;
import bestlows.Shops.BestBuy;
import bestlows.Shops.Ebay;
import bestlows.Shops.Google;
import bestlows.Shops.Newegg;
import bestlows.Shops.Target;
import bestlows.Shops.TjMaxx;
import bestlows.Shops.Walmart;
import bestlows.Utilities.Results;
import java.util.Base64;

public class DebugSearchedResults {

	public static void main(String[] args) {
//		Results amazonResults = new Amazon("television").getAmazonResults();
		Results bestbuyResults = new BestBuy("printer").getBestBuyResults();
//		Results ebayResults = new Ebay("cats").getEbayResults();
//		Results walmartResults = new Walmart("television").getWalmartResults();
//		String item = "data:image/gif;base64,R0lGODlhAQABAIAAAP///////yH5BAEKAAEALAAAAAABAAEAAAICTAEAOw=="
//		String base64Image = item.split(",")[1];
//		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
//		Results tjmaxtResults = new TjMaxx("wallet").getTjMaxxResults();
//		System.out.println(tjmaxtResults.displayResults());
//		Results googleResults = new Google("wallet").getGoogleResults();
	//	Results targetResults = new Target("wallet").getTargetResults();
//		Results neweggResults = new Newegg("wallet").getNeweggResults();
		System.out.println(bestbuyResults.displayResults());
	}

}
