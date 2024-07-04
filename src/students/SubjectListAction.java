package students;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class ShainIndex
 */
@WebServlet("/students/SubjectListAction")
public class SubjectListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjectListAction() {
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
			if (tc == null) {
				System.out.println("teacher情報が取得できません");
			} else {
				String scCd = tc.getSchoolCd().getSchoolCd();
				sc.setSchoolCd(scCd);
			}


			//科目リスト取得
			ArrayList<Subject> subjectList = dao.getAllSubject(sc);

			if (subjectList == null) {
			    throw new NullPointerException("subjectlistがnullです！");
			}

			// 科目リストをセットする
			request.setAttribute("subjectList", subjectList);
			// index.jspへ転送
			request.getRequestDispatcher("/students/subject_list.jsp").forward(request, response);
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
