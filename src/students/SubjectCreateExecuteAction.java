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
 * Servlet implementation class SubjectInsertComplete
 */
@WebServlet("/students/SubjectCreateComplete")
public class SubjectCreateExecuteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjectCreateExecuteAction() {
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

		String subName = request.getParameter("subName");
		String subCd = request.getParameter("subCd");

		//科目ロジックの作成
		SubjectDAO dao = new SubjectDAO();

		try {
			//科目Beanの作成
			Subject subjectBean = dao.getSubjectBean(request);

			School sc = new School();
			HttpSession session = request.getSession();
			// セッションスコープからインスタンスを取得
			Teacher tc = (Teacher)session.getAttribute("teacher");
			String scCd = tc.getSchoolCd().getSchoolCd();
			sc.setSchoolCd(scCd);


            // ★登録する科目コードの重複チェックselect～where文のケース①★
			Subject subjectBean_2 = dao.getSubjectBean(subCd, sc);
            // 重複チェック: 取得したkamokuBeanが空でないかを確認
            if (subjectBean_2.getSubName() != null && !subjectBean_2.getSubName().isEmpty()) {
                request.setAttribute("insertErrorMsg", "科目コードが重複しています。");
                request.getRequestDispatcher("subject_create.jsp").forward(request, response);
                return;
            }


            // 新しい科目情報を設定
            subjectBean.setSchoolCd(scCd);  // SCHOOL_CDを設定
            subjectBean.setSubCd(subCd);
            subjectBean.setSubName(subName);

				// DBへ反映
				dao.insertSubject(subjectBean);
				//正常処理完了ページにリダイレクト
				String encodedView = URLEncoder.encode("登録", StandardCharsets.UTF_8.toString());
				String encodedViewMsg = URLEncoder.encode("登録が完了しました", StandardCharsets.UTF_8.toString());
				response.sendRedirect("subject_done.jsp?view="+ encodedView +  "&viewMsg=" +encodedViewMsg);

				//	重複チェック：insert文による例外チェックのケース②
		} catch (SQLException e) {
				if (e.getMessage().equals("duplicateError")) {
					//主キー重複メッセージをセット
					request.setAttribute("duplicateMsg", "科目コードが重複しています");
					// 入力値を保持
					request.setAttribute("subCd", subCd);
					request.setAttribute("subName", subName);
					request.getRequestDispatcher("/students/subject_create.jsp").forward(request, response);

				} else {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					//エラーページへ転送
					request.getRequestDispatcher("/students/error.jsp").forward(request, response);
				}
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			//エラーページへ転送
			request.getRequestDispatcher("/students/error.jsp").forward(request, response);
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
