package students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/test_search"})
public class Test_Search extends HttpServlet {

	public void doPost (
		    HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
		    PrintWriter out = response.getWriter();
		    Page.header(out);
		    try {
		    	boolean hasErrors = false;
		        String ent_year = request.getParameter("ent_year");
		        String class_num = request.getParameter("class_num");
		        String sub_cd = request.getParameter("sub_cd");
		        String test_no_str = request.getParameter("test_no");

		        if (ent_year == null || ent_year.isEmpty()) {
	                request.setAttribute("yearError", "入学年度を選択してください。");
	                hasErrors = true;
	            }
	            if (class_num == null || class_num.isEmpty()) {
	                request.setAttribute("class_numError", "クラスを選択してください。");
	                hasErrors = true;
	            }
	            if (sub_cd == null || sub_cd.isEmpty()) {
	                request.setAttribute("sub_cdError", "科目を選択してください。");
	                hasErrors = true;
	            }
	            if (test_no_str == null || test_no_str.isEmpty()) {
	                request.setAttribute("test_noError", "回数を選択してください。");
	                hasErrors = true;
	            }

	            if (hasErrors) {
	                request.getRequestDispatcher("/students/test_insert").forward(request, response);
	                return;
	            }

		        // test_noを整数に変換
		        int test_no = Integer.parseInt(test_no_str);

		        // Testオブジェクトの作成
		        Test p = new Test();
		        p.setEnt_Year(ent_year);
		        p.setClass_Num(class_num);
		        p.setSubject_Cd(sub_cd);
		        p.setTest_No(test_no);

		        // DAOを使用して検索処理
		        TestDAO dao = new TestDAO();
		        List<Test> list = dao.search2(p);
		        System.out.println(list); // デバッグ用の出力

		        // 検索結果をリクエスト属性に設定してJSPにフォワード
		        request.setAttribute("students", list);
		        request.getRequestDispatcher("/students/test_insert").forward(request, response);

		    } catch (Exception e) {
		        // その他の例外が発生した場合の処理
		        e.printStackTrace(out); // デバッグ用の出力
		        request.setAttribute("errorMessage", "予期しないエラーが発生しました。");
		        request.getRequestDispatcher("/students/test_regist.jsp").forward(request, response);
		    }
		    Page.footer(out);

}}
