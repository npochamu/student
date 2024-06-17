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

@WebServlet(urlPatterns={"/students/insert"})
public class Insert extends HttpServlet {

	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);
		try {
			String student_no=request.getParameter("Num");
			String student_name=request.getParameter("name");
			int ent_year=Integer.parseInt(request.getParameter("year"));
			String is_attend=request.getParameter("Zaigaku");
			boolean isAttend = Boolean.parseBoolean(is_attend);
			String school_cd=request.getParameter("sc_cd");
			String stu_seibetu=request.getParameter("gender");
			String student_kana=request.getParameter("name_huri");
			String class_no=request.getParameter("class");


			// ここから

			Student p=new Student();
			p.setStudent_No(student_no);
			p.setStudent_Name(student_name);
			p.setEnt_Year(ent_year);
			p.setIs_Attend(isAttend);
			p.setSchool_Cd(school_cd);
			p.setStu_Seibetu(stu_seibetu);
			p.setStudent_Kana(student_kana);
			p.setClass_No(class_no);

			StudentsDAO dao=new StudentsDAO();
			int line=dao.insert(p);

			// ここまで

			if (line>0) {
				request.setAttribute("insert", "学生情報登録");
				request.setAttribute("insert2", "登録が完了しました");
				request.getRequestDispatcher("sturegiser_complete.jsp")
					.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}