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

import bean.Subject;
import dao.SubjectDAO;

/**
 * Servlet implementation class SubjectUpdateComplete
 */
@WebServlet("/students/SubjectUpdateComplete")
public class SubjectUpdateExecuteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjectUpdateExecuteAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字コード指定
		request.setCharacterEncoding("UTF-8");
		//科目ロジックの作成
		SubjectDAO dao = new SubjectDAO();
		try {

			HttpSession session=request.getSession();

			if (session.getAttribute("teacher") == null) {
				request.getRequestDispatcher("/login/login.jsp")
					.forward(request, response);
			}

			//科目Beanの作成
			Subject subjectBean = dao.getSubjectBean(request);
			// DBへ反映
			int result = dao.updateSubject(subjectBean);

			String encodedViewMsg;
			if (result == 1) {
				encodedViewMsg = URLEncoder.encode("変更が完了しました", StandardCharsets.UTF_8.toString());
			} else {
				encodedViewMsg = URLEncoder.encode("変更できません", StandardCharsets.UTF_8.toString());
			}
			//完了ページにリダイレクト
			String encodedView = URLEncoder.encode("変更", StandardCharsets.UTF_8.toString());
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
