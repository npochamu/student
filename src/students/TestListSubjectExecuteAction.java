package students;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TestListSubject;
import dao.TestListSubjectDAO;

@WebServlet("/students/TestListSubjectAction")
public class TestListSubjectExecuteAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	HttpSession session = request.getSession();


		if (session.getAttribute("teacher") == null) {
			request.getRequestDispatcher("/login/login.jsp")
				.forward(request, response);
		}

        String year = request.getParameter("entYear");
        String classNum = request.getParameter("classNo");
        String subject = request.getParameter("subcd");
        String sort = request.getParameter("sort");

        if (year == null || year.isEmpty() || classNum == null || classNum.isEmpty() || subject == null || subject.isEmpty()) {
            request.setAttribute("errorMessage", "入学年度とクラスと科目を選択してください");
            request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
            return;
        }

        List<TestListSubject> testList = null;
        String subjectName = null;
        try {
            TestListSubjectDAO dao = new TestListSubjectDAO();
            testList = dao.selectClassSubjectScore(classNum, subject, year);
            if (!testList.isEmpty()) {
                subjectName = testList.get(0).getSubName();
            }

            // Sort list if needed
            if ("kana".equals(sort)) {
                Collections.sort(testList, Comparator.comparing(TestListSubject::getKana));
            } else if ("studentNo".equals(sort)) {
                Collections.sort(testList, Comparator.comparing(TestListSubject::getStudentNo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("testList", testList);
        request.setAttribute("subjectName", subjectName);

        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }
}
