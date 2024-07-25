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

import bean.ClassNum;
import bean.Student;
import dao.ClassNumDAO;
import dao.StudentsDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/search"})
public class Search extends HttpServlet {

	@SuppressWarnings("unused")
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Page.header(out);
		try {


			HttpSession session=request.getSession();

			if (session.getAttribute("teacher") == null) {
				request.getRequestDispatcher("/login/login.jsp")
					.forward(request, response);
			}

			String ent_year=request.getParameter("year");
			String class_no=request.getParameter("classno");

			String is_attend=request.getParameter("Zaigaku");
			boolean isAttend = Boolean.parseBoolean(is_attend);
			System.out.println(isAttend);
			System.out.println(ent_year);
			System.out.println(class_no);

			System.out.println("なめこちゃん");




			// ここから
			Student p=new Student();
			p.setEnt_Year(ent_year);
			p.setClass_No(class_no);
			p.setIs_Attend(isAttend);

			StudentsDAO dao=new StudentsDAO();

			if(!(class_no.equals("")) && (ent_year.equals("") || is_attend.equals("false"))){
				ent_year=request.getParameter("year2");
				class_no=request.getParameter("classsno2");
				is_attend=request.getParameter("zaigaku2");
				System.out.println("なめこちゃ1ん");
				System.out.println(ent_year);
				System.out.println(class_no);
				System.out.println(is_attend);


				System.out.println(is_attend +"to");
				isAttend = Boolean.parseBoolean(is_attend);

                request.setAttribute("errorMessage", "クラスを指定する場合は入学年度も指定してください");

			}
			if((!(ent_year.equals("")) || is_attend == "false") && class_no.equals("")){
				System.out.println("サメ1");

				List<Student> list=dao.search(ent_year, isAttend);
				System.out.print(list);
				request.setAttribute("students", list);

			}else if(!(ent_year.equals("")) || !(class_no.equals("")) || is_attend.equals("true")){
				System.out.println("サメ");

				List<Student> list=dao.search(ent_year, class_no, isAttend);
				request.setAttribute("students", list);

			}else if(is_attend.equals("true") && (ent_year.equals("") && class_no.equals(""))){
				List<Student> list=dao.search(isAttend);
				request.setAttribute("students", list);
			}else{
				System.out.println("サメ3");
				List<Student> list=dao.all();
				request.setAttribute("all", list);
			}

			ClassNumDAO dao1=new ClassNumDAO();
			List<ClassNum> list2=dao1.classall();

			// ここまで

			request.setAttribute("classall", list2);
			request.setAttribute("year3", ent_year);
			request.setAttribute("class3", class_no);
			request.getRequestDispatcher("student_list.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}

