package bestlows;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bestlows.Shops.Amazon;
import bestlows.Shops.BestBuy;
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
			Results amazonResults = new Amazon(searchParameter).getAmazonResults();
			Results bestbuyResults = new BestBuy(searchParameter).getBestBuyResults();
			
			ArrayList<Results> results = new ArrayList<Results>();
			if (amazonResults != null) {
				displayResults = amazonResults.displayResults();
			}
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

}
