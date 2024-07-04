package students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TestListSubject;
import dao.TestListSubjectDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/subs"})
public class TestListSubjectAction extends HttpServlet {

    public void doGet (
        	HttpServletRequest request, HttpServletResponse response
        ) throws ServletException, IOException {

    		response.setContentType("text/html; charset=UTF-8");
        	PrintWriter out=response.getWriter();
        	Page.header(out);

        try {

        	TestListSubjectDAO dao=new TestListSubjectDAO();
            List<TestListSubject> list=dao.selectSubjectScore();

            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<th>科目コード</th><th>科目名</th><th>入学年度</th><th>クラス</th><th>学生番号</th><th>氏名</th><th>1回</th><th>2回</th>");
            out.println("</tr>");

            for (TestListSubject tls : list) {
                out.println("<tr>");
                out.println("<td>" + tls.getSubCd() + "</td>");
                out.println("<td>" + tls.getSubName() + "</td>");
                out.println("<td>" + tls.getEntYear() + "</td>");
                out.println("<td>" + tls.getClassNo() + "</td>");
                out.println("<td>" + tls.getStudentNo() + "</td>");
                out.println("<td>" + tls.getStudentName() + "</td>");
                out.println("<td>" + tls.getTest_1() + "</td>");
                out.println("<td>" + tls.getTest_2() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (Exception e) {
            e.printStackTrace(out);
        }
        Page.footer(out);
    }
}
