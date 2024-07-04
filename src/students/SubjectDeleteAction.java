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
 * Servlet implementation class SubjectDelete
 */
@WebServlet("/students/SubjectDeleteAction")
public class SubjectDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectDeleteAction() {
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

			School sc = new School();
			HttpSession session = request.getSession();
			// セッションスコープからインスタンスを取得
			Teacher tc = (Teacher)session.getAttribute("teacher");
			String scCd = tc.getSchoolCd().getSchoolCd();
			sc.setSchoolCd(scCd);

			//科目Beanの作成
			Subject subjectBean = dao.getSubjectBean(request.getParameter("subCd"), sc);
			System.out.println(request.getParameter("subCd"));
			// 削除科目をセットする
			request.setAttribute("subjectBean", subjectBean);
			System.out.println(subjectBean.getSubCd());

			// delete.jspへ転送
			request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
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
