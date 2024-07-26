
package students;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentsDAO;
import tool.Page;


@WebServlet(urlPatterns={"/students/updateinsert"})

public class UpdateInsert extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

	public void processRequest (

			HttpServletRequest request, HttpServletResponse response

		) throws ServletException, IOException {

			PrintWriter out=response.getWriter();

			Page.header(out);

			try {

				HttpSession session = request.getSession();


				if (session.getAttribute("teacher") == null) {
					request.getRequestDispatcher("/login/login.jsp")
						.forward(request, response);
				}

				String student_no=request.getParameter("student_id");

				String student_name=request.getParameter("name");

				String ent_year = "0"; // デフォルト値を設定


				ent_year = request.getParameter("ent_year");

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

					request.setAttribute("insert", "学生情報変更");
					request.setAttribute("insert2", "変更が完了しました");
					request.getRequestDispatcher("sturegiser_complete.jsp")
						.forward(request, response);
				}else{
					List<Student> list=dao.all();
					request.setAttribute("all", list);
					request.setAttribute("class3", class_no);
					request.getRequestDispatcher("student_list.jsp")
						.forward(request, response);
				}


			} catch (Exception e) {

				e.printStackTrace(out);

			}

			Page.footer(out);

		}

	}