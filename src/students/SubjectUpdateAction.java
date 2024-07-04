package students;

import java.io.IOException;
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
 * Servlet implementation class SubjectUpdate
 */
@WebServlet("/students/SubjectUpdateAction")
public class SubjectUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjectUpdateAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//科目ロジックの作成
		SubjectDAO dao = new SubjectDAO();

		try {

			School sc = new School();
			HttpSession session = request.getSession();
			// セッションスコープからインスタンスを取得
			Teacher tc = (Teacher)session.getAttribute("teacher");
			String scCd = tc.getSchoolCd().getSchoolCd();
			sc.setSchoolCd(scCd);

			//科目Beanの作成
			Subject subjectBean = dao.getSubjectBean(request.getParameter("subCd"), sc);
			// 更新科目をセットする
			request.setAttribute("subjectBean", subjectBean);
			// update.jspへ転送
			request.getRequestDispatcher("subject_update.jsp").forward(request, response);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
