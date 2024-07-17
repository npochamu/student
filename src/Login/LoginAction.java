package Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Page;

@WebServlet(urlPatterns={"/login/loginaction", "/students/loginaction", "/mein/loginaction"})
public class LoginAction extends HttpServlet {

	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);
		try {
		HttpSession session=request.getSession();

		String login=request.getParameter("userId");
		String password=request.getParameter("password");
		TeacherDAO dao = new TeacherDAO();
		Teacher teacher = dao.login(login, password);

		if (teacher!=null){
			session.setAttribute("teacher", teacher);
			request.getRequestDispatcher("/mein/menu.jsp")
			.forward(request, response);
		}else{
		request.setAttribute("errorMessage","ユーザ名またはパスワードが違います");
		request.getRequestDispatcher("/login/login.jsp")
		.forward(request, response);
		}
		} catch (Exception e) {
			request.getRequestDispatcher("/login/login-error.jsp")
			.forward(request, response);
		}
		Page.footer(out);
	}
}
