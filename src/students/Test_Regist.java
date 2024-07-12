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

				int pointSize = 9;
				String subCd = "A02";
				int testNo = 1;
				String scCd = "111";

				List<Test> pointList = new ArrayList<>();
				int i = 1;
				while (i<=pointSize) {
					Test test = new Test();
					test.setStudent_No(request.getParameter("stucd" + i));
					test.setSubject_Cd(subCd);
					test.setSchool_Cd(scCd);
					test.setTest_No(testNo);
					test.setPoint(Integer.parseInt(request.getParameter("point" + i)));
					test.setClass_Num(request.getParameter("clsno" + i));
					pointList.add(test);

					i++;
				}



				TestDAO dao=new TestDAO();
				int line=dao.Insert2(pointList);


				if (line > 0) {
                    request.getRequestDispatcher("/students/test_regist_done.jsp").forward(request, response);
                }

			} catch (Exception e) {

				e.printStackTrace(out);

			}

			Page.footer(out);

		}

	}
