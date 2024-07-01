
package students;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentsDAO;
import tool.Page;


@WebServlet(urlPatterns={"/students/updateinsert"})

public class UpdateInsert extends HttpServlet {

	public void doPost (

			HttpServletRequest request, HttpServletResponse response

		) throws ServletException, IOException {

			PrintWriter out=response.getWriter();

			Page.header(out);

			try {

				String student_no=request.getParameter("student_id");

				String student_name=request.getParameter("name");

				String ent_year = ""; // デフォルト値を設定

				String ent_year_str = request.getParameter("ent_year");


				if (ent_year_str != null && !ent_year_str.isEmpty()) {
	                ent_year = ent_year_str;
	            }


				boolean isAttend = request.getParameter("isattend") != null;

				String stu_seibetu=request.getParameter("gender");

				String student_kana=request.getParameter("name_kana");

				String class_no=request.getParameter("classnum");


				// ここから

				Student p=new Student();

				p.setStudent_No(student_no);

				p.setStudent_Name(student_name);

				p.setEnt_Year(ent_year);

				p.setIs_Attend(isAttend);

				p.setStu_Seibetu(stu_seibetu);

				p.setStudent_Kana(student_kana);

				p.setClass_No(class_no);

				StudentsDAO dao=new StudentsDAO();

				int line=dao.UpdateInsert(p);

				// ここまで

				if (line>0) {

					request.setAttribute("insert", "学生情報登録");
					request.setAttribute("insert2", "登録が完了しました");
					request.getRequestDispatcher("sturegiser_complete.jsp")
						.forward(request, response);

				}

		        out.println("<br><br>");

				out.println("<a href='student_list.jsp'>学生一覧</a>");

			} catch (Exception e) {

				e.printStackTrace(out);

			}

			Page.footer(out);

		}

	}