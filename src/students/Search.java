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

			if(class_no != "" && (ent_year == "" || is_attend == "false")){
				ent_year=request.getParameter("year2");
				class_no=request.getParameter("classsno2");
				is_attend=request.getParameter("zaigaku2");


				System.out.println(is_attend +"to");
				isAttend = Boolean.parseBoolean(is_attend);

                request.setAttribute("errorMessage", "クラスを指定する場合は入学年度も指定してください");

			}
			if((ent_year != "" || is_attend == "false") && class_no == ""){
				System.out.println("サメ1");

				List<Student> list=dao.search(ent_year, isAttend);
				System.out.print(list);
				request.setAttribute("students", list);

			}else if(ent_year != "" || class_no != "" || is_attend == "true"){
				System.out.println("サメ2");

				List<Student> list=dao.search(ent_year, class_no, isAttend);
				request.setAttribute("students", list);

			}else if(isAttend == true && (ent_year == "" && class_no == "" )){
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
			request.getRequestDispatcher("student_list.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}

