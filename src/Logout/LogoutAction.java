package Logout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Page;

@WebServlet(urlPatterns={"/logout/logoutaction"})
public class LogoutAction extends HttpServlet {

	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);
		try {
		HttpSession session=request.getSession();

		if (session.getAttribute("teacher") == null) {
			request.getRequestDispatcher("/login/login.jsp")
				.forward(request, response);
		}

		if (session.getAttribute("teacher")!=null){
			session.invalidate();
			System.out.println("俺");
			request.getRequestDispatcher("/logout/logout.jsp")
			.forward(request, response);
		}

		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}
