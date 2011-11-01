package group1.location;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/LocationDataServlet")
public class LocationDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public LocationDataServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Testing...");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BufferedReader reader = request.getReader();
		String line = reader.readLine();

		while(line != null) {
			System.out.println("Got line: " + line);

			try {

				FileWriter fstream = new FileWriter("locationData.txt", true);
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(line + '\n');
				out.close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage()); }

			line = reader.readLine();
		}
	}
}
