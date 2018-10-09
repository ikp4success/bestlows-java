package bestlows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bestlows.Shops.Amazon;
import bestlows.Shops.BestBuy;
import bestlows.Shops.Ebay;
import bestlows.Utilities.DefaultResources;
import bestlows.Utilities.Results;

/**
 * Servlet implementation class SearchedResults
 */
@WebServlet("/SearchedResults")
public class SearchedResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String ErrorMessage;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchedResults() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchParameter = request.getParameter("search");
		String displayResults = null;

		if (!searchParameter.isEmpty() && searchParameter != null) {
			displayResults = getDisplayResults(searchParameter);

			if (displayResults != null) {
				request.setAttribute("display_results", displayResults);
				request.getRequestDispatcher("/SearchedResultsWeb.jsp").forward(request, response);
			} else {
				// display error page
				ErrorMessage = new DefaultResources().ErrorMessage("Could not find " + searchParameter);
				System.out.println(searchParameter);
				request.setAttribute("error_message", ErrorMessage);
				request.getRequestDispatcher("/SearchedResultsWeb.jsp").forward(request, response);
			}

			System.out.println(displayResults);

		}
	}

	public String getDisplayResults(String searchParameter) {
		Results amazonResults = new Amazon(searchParameter).getAmazonResults();
		Results bestbuyResults = new BestBuy(searchParameter).getBestBuyResults();
		Results ebayResults = new Ebay(searchParameter).getEbayResults();
		String displayResults = "";

		List<Results> results = removeEmptyResults(Arrays.asList(amazonResults, bestbuyResults, ebayResults));

		if (results.size() > 1) {
			results.sort((r1, r2) -> {
				return r1.get_sort_price().compareTo(r2.get_sort_price());
			}); // sort price
		}

		for (Results result : results) {
			displayResults += result.displayResults();
		}

		return displayResults;
	}
	
	public List<Results> removeEmptyResults(List<Results> results) {
		List<Results> n_results = new ArrayList<Results>();
		for (Results result : results) {
			if(result != null) {
				n_results.add(result);
			}
		}
		return n_results;
	}

}
