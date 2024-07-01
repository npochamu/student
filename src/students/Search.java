package students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.Student;
import dao.ClassNumDAO;
import dao.StudentsDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/search"})
public class Search extends HttpServlet {

	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);
		try {
			int ent_year=Integer.parseInt(request.getParameter("year"));
			String class_no=request.getParameter("class");
			String is_attend=request.getParameter("Zaigaku");
			boolean isAttend = Boolean.parseBoolean(is_attend);

			// ここから
			Student p=new Student();
			p.setEnt_Year(ent_year);
			p.setClass_No(class_no);
			p.setIs_Attend(isAttend);

			StudentsDAO dao=new StudentsDAO();
			List<Student> list=dao.search(p);
			ClassNumDAO dao1=new ClassNumDAO();
			List<ClassNum> list2=dao1.classall();

			// ここまで

			request.setAttribute("students", list);
			request.setAttribute("classall", list2);
			request.getRequestDispatcher("student_list.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}

