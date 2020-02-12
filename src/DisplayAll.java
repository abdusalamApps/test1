
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AllInfoRow;

/**
 * Servlet implementation class DisplayAll
 */
@WebServlet("/DisplayAll")
public class DisplayAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static private Database db = new Database();

	/**
	 * Default constructor.
	 */
	public DisplayAll() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String cssTag = "<link rel='stylesheet' type='text/css' href='displayall.css'>";

		List<AllInfoRow> allInfo = db.getAllData();

		String[] heads = {"Respondent",
				"Char. Answer 1", "Char. Answer 2",
				"Data Answer 1", "Data Answer 2", "Data Answer 3", "Data Answer 4"
				};
		
		
//		FormGenerator generator = new FormGenerator();
//		out.println(generator.generateTable(heads, allInfo, cssTag));
		
		request.setAttribute("heads", heads);
		request.getRequestDispatcher("displayall.jsp");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
