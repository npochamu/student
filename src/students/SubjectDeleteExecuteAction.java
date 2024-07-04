package students;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;

/**
 * Servlet implementation class SubjectDeleteComplete
 */
@WebServlet("/students/SubjectDeleteComplete")
public class SubjectDeleteExecuteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectDeleteExecuteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//科目ロジックの作成
		SubjectDAO dao = new SubjectDAO();
		try {
			// DBへ反映
			Subject sub = new Subject();
			sub.setSubCd(request.getParameter("subCd"));

			School sc = new School();
			HttpSession session = request.getSession();
			// セッションスコープからインスタンスを取得
			Teacher tc = (Teacher)session.getAttribute("teacher");
			String scCd = tc.getSchoolCd().getSchoolCd();
			sc.setSchoolCd(scCd);

			sub.setSchoolCd(scCd);
			int result = dao.deleteSubject(sub);
//			コンソールに表示
			System.out.println("削除完了件数："+result);

			//完了ページにリダイレクト
			String encodedView = URLEncoder.encode("削除", StandardCharsets.UTF_8.toString());
			String encodedViewMsg = URLEncoder.encode("削除が完了しました", StandardCharsets.UTF_8.toString());
			response.sendRedirect("subject_done.jsp?view="+ encodedView +  "&viewMsg=" +encodedViewMsg);

		} catch (SQLException | NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			//エラーページへ転送
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
