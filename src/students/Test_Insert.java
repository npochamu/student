package students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/test_insert"})
public class Test_Insert extends HttpServlet {

	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);
		try {


			TestDAO dao=new TestDAO();
			List<Test> list=dao.all2();


			System.out.print(list);
			request.setAttribute("list", list);
			request.getRequestDispatcher("test_regist.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}
