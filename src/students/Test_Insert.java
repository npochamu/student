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

import bean.Teacher;
import bean.Test;
import dao.ClassNumDAO;
import dao.TestDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/test_insert"})
public class Test_Insert extends HttpServlet {

    public void doGet (
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost (
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Page.header(out);
        try {
        	HttpSession session = request.getSession();

        	if (session.getAttribute("teacher") == null) {
				request.getRequestDispatcher("/login/login.jsp")
					.forward(request, response);
			}

        	// セッションスコープからインスタンスを取得
        	Teacher tc = (Teacher)session.getAttribute("teacher");

        	ClassNumDAO clsdao = new ClassNumDAO();
        	List<String> clslist = new ArrayList<>();
//        	ログイン時に学校コードをTeacherに設定済のためtc.getSchoolCd()でOK
        	clslist = clsdao.filter(tc.getSchoolCd());
        	request.setAttribute("clslist", clslist);


        	TestDAO dao = new TestDAO();
        	List<Test> list = dao.all2();

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