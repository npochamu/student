package students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.SubjectDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/TestListAction"})
public class TestListAction extends HttpServlet {

    public void doGet (
        	HttpServletRequest request, HttpServletResponse response
        ) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out=response.getWriter();
    	Page.header(out);

        try {

			HttpSession session = request.getSession();


			if (session.getAttribute("teacher") == null) {
				request.getRequestDispatcher("/login/login.jsp")
					.forward(request, response);
			}


			// セッションスコープからインスタンスを取得
			Teacher tc = (Teacher)session.getAttribute("teacher");
            if (tc == null) {
                response.sendRedirect("test_list.jsp");
                return;
            }

			School sc = new School();
			sc.setSchoolCd(tc.getSchoolCd().getSchoolCd());
			System.out.println(sc);

            SubjectDAO subdao = new SubjectDAO(); // Schoolオブジェクトを取得
            List<Subject> sublist = subdao.getAllSubject(sc);
            request.setAttribute("sublist", sublist);

            ClassNumDAO clsdao = new ClassNumDAO();
//            List<ClassNum> clslist = clsdao.filter(school);  // Schoolオブジェクトを渡す
            List<String> clslist = new ArrayList<>();
            clslist = clsdao.filter(sc);  // Schoolオブジェクトを渡す
            System.out.println(clslist);
            request.setAttribute("clslist", clslist);


        } catch (Exception e) {
        e.printStackTrace(out);
        }

        // JSPページにフォワード
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
        Page.footer(out);
    }
}
