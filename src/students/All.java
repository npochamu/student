package students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentsDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/all"})
public class All extends HttpServlet {

	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);
		try {

			// ここから
			StudentsDAO dao=new StudentsDAO();
			List<Student> list=dao.all();



			// ここまで

			request.setAttribute("all", list);
			request.getRequestDispatcher("student_list.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}
