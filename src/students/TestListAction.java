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
import bean.School;
import bean.Subject;
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
            // フィルター条件をリクエストから取得
//                String filterSubCd = request.getParameter("subCd");
//                String filterClassNo = request.getParameter("classNo");
//                String filterEntYear = request.getParameter("entYear");
//                String filterSubCd = "BBB";
//                String filterClassNo = "101";
//                String filterEntYear = "2023";

            SubjectDAO subdao = new SubjectDAO();
            List<Subject> sublist = subdao.getAllSubject();
            request.setAttribute("sublist", sublist);

            School school = new School();
            school.setSchoolCd("111");  // 仮の学校コードを設定

            ClassNumDAO clsdao = new ClassNumDAO();
            List<ClassNum> clslist = clsdao.filter(school);  // Schoolオブジェクトを渡す
            request.setAttribute("clslist", clslist);



            // フィルター条件に基づいてリストをフィルタリング
//                if (filterSubCd != null && !filterSubCd.isEmpty()) {
//                    list = list.stream()
//                               .filter(tls -> tls.getSubCd().equals(filterSubCd))
//                               .collect(Collectors.toList());
//                }
//                if (filterClassNo != null && !filterClassNo.isEmpty()) {
//                    list = list.stream()
//                               .filter(tls -> tls.getClassNo().equals(filterClassNo))
//                               .collect(Collectors.toList());
//                }
//                if (filterEntYear != null && !filterEntYear.isEmpty()) {
//                    list = list.stream()
//                               .filter(tls -> tls.getEntYear().equals(filterEntYear))
//                               .collect(Collectors.toList());
//                }

            // フィルターされたリストをリクエストにセット
//                request.setAttribute("items", list);


        } catch (Exception e) {
        e.printStackTrace(out);
        }

        // JSPページにフォワード
        request.getRequestDispatcher("/students/test_list.jsp").forward(request, response);
        Page.footer(out);
    }
}
