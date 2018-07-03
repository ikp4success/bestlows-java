package bestlows;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bestlows.Shops.Amazon;
import bestlows.Utilities.Results;

/**
 * Servlet implementation class SearchedResults
 */
@WebServlet("/SearchedResults")
public class SearchedResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String displayResults = null;

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
		if (!searchParameter.isEmpty() && searchParameter != null) {
			Results amazonResults = new Amazon(searchParameter).getAmazonResults();
			displayResults = amazonResults.displayResults();
			
			if(displayResults !=null) {
				request.setAttribute("display_results", displayResults);
				request.getRequestDispatcher("/SearchedResultsWeb.jsp").forward(request, response);
			}else {
				//display error page
			}
			
			System.out.println(displayResults);

		}
	}

}
