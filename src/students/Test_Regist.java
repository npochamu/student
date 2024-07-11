package students;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDAO;
import tool.Page;


@WebServlet(urlPatterns={"/students/test_regist"})

public class Test_Regist extends HttpServlet {

	public void doPost (

			HttpServletRequest request, HttpServletResponse response

		) throws ServletException, IOException {

			PrintWriter out=response.getWriter();

			Page.header(out);

			try {

				// リクエストから "point" パラメータを取得
				String pointStr = request.getParameter("point");

				// point のデフォルト値を設定
				int point = 0;

				if (pointStr != null && !pointStr.isEmpty()) {
				    try {
				        // pointStr を整数に変換
				        point = Integer.parseInt(pointStr);

				    } catch (NumberFormatException e) {
				        // 数値への変換に失敗した場合のエラーメッセージを設定
				        request.setAttribute("errorMessage", "点数は数値である必要があります。");
				        return; // エラーが発生した場合、ここで処理を終了
				    }
				}

				Test p=new Test();

				p.setPoint(point);

				TestDAO dao=new TestDAO();

				int line=dao.Insert2(p);


				if (line > 0) {
                    request.getRequestDispatcher("/students/test_regist_done.jsp").forward(request, response);
                }

			} catch (Exception e) {

				e.printStackTrace(out);

			}

			Page.footer(out);

		}

	}
