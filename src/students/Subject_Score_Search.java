package students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Test;
import dao.TestDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/subject_score_search"})
public class Subject_Score_Search extends HttpServlet {

	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Page.header(out);

		try {

			HttpSession session=request.getSession();

			if (session.getAttribute("teacher") == null) {
				request.getRequestDispatcher("/login/login.jsp")
					.forward(request, response);
			}

			String student_no = request.getParameter("student_no");

			//			フォワード先で表示切替に使用
			request.setAttribute("isFromTestStudent", true );

            // 学生番号のチェック
            if (!Student_Nocheck(student_no)) {
                request.setAttribute("errorMessage", "学生番号は7桁の半角数字で入力してください");
                request.getRequestDispatcher("TestListAction").forward(request, response);
                return;
            }

			Test p = new Test();
			p.setStudent_No(student_no);

			TestDAO dao = new TestDAO();
			List<Test> list = dao.searchin(p);

            // 学生の存在チェック
            if (list.size()==0) {
            	Test stu = dao.getTestBean(student_no);
            	request.setAttribute("studentName", stu.getStudent_Name());
            	System.out.println(stu.getStudent_Name());
            	if (stu.getStudent_Name()==null) {
            		request.setAttribute("stuMessage", "学生番号に該当する学生がいません");
	            	}
            }
			// ここまで

			request.setAttribute("test", list);
			request.getRequestDispatcher("TestListAction").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
    // 学生番号のチェック
    private boolean Student_Nocheck(String studentNo) {
        // 7桁の数字のみで構成されているかどうかをチェックする
        return studentNo.matches("\\d{7}");
    }
}