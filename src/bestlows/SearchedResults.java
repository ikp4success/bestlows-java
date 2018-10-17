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
import bestlows.Shops.Google;
import bestlows.Shops.TjMaxx;
import bestlows.Shops.Walmart;
import bestlows.Utilities.DefaultResources;
import bestlows.Utilities.DisplayResultsAccessory;
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
				request.setAttribute("error_message", ErrorMessage);
				request.getRequestDispatcher("/SearchedResultsWeb.jsp").forward(request, response);
			}			

		}else {
			// display error page
			ErrorMessage = new DefaultResources().ErrorMessage("Please Enter a search keyword");
			request.setAttribute("error_message", ErrorMessage);
			request.getRequestDispatcher("/SearchedResultsWeb.jsp").forward(request, response);
		}
	}

	private String getDisplayResults(String searchParameter) {
		DisplayResultsAccessory dr = new DisplayResultsAccessory(searchParameter, 2);
		return dr.getDisplayResults();
	}

}
