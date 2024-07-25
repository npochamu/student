package students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.ClassNumDAO;
import dao.TestDAO;
import tool.Page;

@WebServlet(urlPatterns = { "/students/test_regist" })

public class Test_Regist extends HttpServlet {

	public void doPost(

			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		Page.header(out);

		try {
			int pointSize = Integer.parseInt(request.getParameter("student_count"));
			String subCd = request.getParameter("sub_cd");
			int testNo = Integer.parseInt(request.getParameter("test_no"));
			String scCd = "111";
			// String entYear = request.getParameter("ent_year");
			// String classNum = request.getParameter("class_num");
			String subName = request.getParameter("sub_name");

			List<Test> pointList = new ArrayList<>();
			boolean hasValidInput = false;
			boolean hasPointError = false;
			int i = 1;
			while (i <= pointSize) {
				String stuN = request.getParameter("stucd" + i);
				String pointParam = request.getParameter("point" + stuN);

				// if (pointParam != null && !pointParam.trim().isEmpty()) {

				try {
					if (!pointParam.matches("^(100|[1-9]?[0-9])$")&& !pointParam.trim().isEmpty()) {
						hasPointError = true;
					} else {
						int point = Integer.parseInt(pointParam);
						if (point >= 0 && point <= 100) {
							hasValidInput = true;
						} else {
							hasPointError = true;
						}
					}

				} catch (NumberFormatException e) {
					if (!pointParam.equals("")) {
						hasPointError = true;
					}
				}

				Test test = new Test();
				test.setStudent_No(request.getParameter("stucd" + i));
				test.setSubject_Cd(subCd);
				test.setSchool_Cd(scCd);
				test.setEnt_Year(request.getParameter("entye" + i));
				test.setStudent_Name(request.getParameter("stuna" + i));
				test.setTest_No(testNo);
				// 文字が入るとエラーになるので整数型にするのはなしで
				// test.setPoint(Integer.parseInt(pointParam));
				test.setPoint_str(pointParam);
				test.setClass_Num(request.getParameter("clsno" + i));
				test.setSubject_Name(subName);
				pointList.add(test);

				i++;
			}

			if (hasPointError || !hasValidInput) {
				HttpSession session = request.getSession();
				// セッションスコープからインスタンスを取得
				Teacher tc = (Teacher) session.getAttribute("teacher");

				ClassNumDAO clsdao = new ClassNumDAO();
				List<String> clslist = new ArrayList<>();
				// ログイン時に学校コードをTeacherに設定済のためtc.getSchoolCd()でOK
				clslist = clsdao.filter(tc.getSchoolCd());
				request.setAttribute("clslist", clslist);

				TestDAO dao = new TestDAO();
				List<Test> list = dao.all2();
				request.setAttribute("list", list);

				if (!hasPointError && !hasValidInput) {
					request.setAttribute("noUpsertMsg", "登録する成績がありません");
				}
				request.setAttribute("students", pointList);
				request.getRequestDispatcher("/students/test_regist.jsp").forward(request, response);

			} else if (hasValidInput && !hasPointError) {
				// 点数が空白のデータは処理しないのはdaoで処理

				TestDAO dao = new TestDAO();
				int line = dao.Insert2(pointList);

				if (line > 0) {
					request.setAttribute("message", "登録が完了しました");
				} else {
					request.setAttribute("message", "登録できませんでした");
				}
				request.getRequestDispatcher("/students/test_regist_done.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace(out);
		}

		Page.footer(out);
	}
}
