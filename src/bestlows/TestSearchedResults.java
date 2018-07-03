package bestlows;

import bestlows.Shops.Amazon;
import bestlows.Utilities.Results;

public class TestSearchedResults {

	public static void main(String[] args) {
		Results amazonResults = new Amazon("television").getAmazonResults();
		System.out.println(amazonResults.displayResults());
	}

}
