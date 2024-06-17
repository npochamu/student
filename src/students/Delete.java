package students;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentsDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/delete"})
public class Delete extends HttpServlet {

	@SuppressWarnings("unused")
	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);
		try {
			String student_no=request.getParameter("Number");
			// ここから

			Student p=new Student();
			p.setStudent_No(student_no);
			StudentsDAO dao=new StudentsDAO();

			int count = dao.delete(p);

			// ここまで

			request.setAttribute("insert", "学生情報登録");
			request.setAttribute("insert2", "登録が完了しました");
			request.getRequestDispatcher("sturegiser_complete.jsp")
				.forward(request, response);

			out.println("削除が完了しました。");
			out.println("<br><br>");
			out.println("<a href='student_list.jsp'>学生一覧</a>");
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}