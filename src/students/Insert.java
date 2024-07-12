package students;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentsDAO;
import tool.Page;

@WebServlet(urlPatterns={"/students/insert"})
public class Insert extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Page.header(out);
        try {

        	HttpSession session=request.getSession();

			if (session.getAttribute("teacher") == null) {
				request.getRequestDispatcher("/login/login.jsp")
					.forward(request, response);
			}


            String student_no = request.getParameter("Num");
            String student_name = request.getParameter("name");
            String ent_year = request.getParameter("year");
            String is_attend = request.getParameter("Zaigaku");
            boolean isAttend = Boolean.parseBoolean(is_attend);
            String school_cd = request.getParameter("sc_cd");
            String stu_seibetu = request.getParameter("gender");
            String student_kana = request.getParameter("name_huri");
            String class_no = request.getParameter("class");

            // 必須項目のチェック
            if (ent_year == null || ent_year.isEmpty() || stu_seibetu == null || stu_seibetu.isEmpty()) {
                request.setAttribute("errorMessage", "入学年度と性別は必須項目です");
                request.getRequestDispatcher("/students/classall").forward(request, response);
                return;
            }

            // 学生番号のチェック
            if (!Student_Nocheck(student_no)) {
                request.setAttribute("errorMessage", "学生番号は7桁の半角数字で入力してください");
                request.getRequestDispatcher("/students/classall").forward(request, response);
                return;
            }

            // フリガナのチェック
            if (!Katakana_check(student_kana)) {
                request.setAttribute("errorMessage", "フリガナはカタカナで入力してください");
                request.getRequestDispatcher("/students/classall").forward(request, response);
                return;
            }

            // 氏名のチェック
            if (!Student_Namecheck(student_name)) {
                request.setAttribute("errorMessage", "氏名は漢字、ひらがな、カタカナ、英語で入力してください");
                request.getRequestDispatcher("/students/classall").forward(request, response);
                return;
            }

            StudentsDAO dao = new StudentsDAO();

            // 学生番号が既に存在するかをチェック
            if (dao.isStudentNumberExists(student_no)) {
                request.setAttribute("errorMessage", "学生番号が既に登録されています");
                request.getRequestDispatcher("/students/classall").forward(request, response);
            } else {
                Student p = new Student();
                p.setStudent_No(student_no);
                p.setStudent_Name(student_name);
                p.setEnt_Year(ent_year);
                p.setIs_Attend(isAttend);
                p.setSchool_Cd(school_cd);
                p.setStu_Seibetu(stu_seibetu);
                p.setStudent_Kana(student_kana);
                p.setClass_No(class_no);

                int line = dao.insert(p);

                if (line > 0) {

                    request.getRequestDispatcher("/students/sturegiser_complete.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "登録に失敗しました");
                    request.getRequestDispatcher("/students/classall").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "エラーが発生しました");
            request.getRequestDispatcher("/students/classall").forward(request, response);
        }
        Page.footer(out);
    }

    // 学生番号のチェック
    private boolean Student_Nocheck(String studentNo) {
        // 7桁の数字のみで構成されているかどうかをチェックする
        return studentNo.matches("\\d{7}");
    }

    // フリガナのチェック
    private boolean Katakana_check(String katakana) {
        // カタカナのみで構成されているかどうかをチェックする
        return katakana.matches("[ァ-ヶー]+");
    }

    // 氏名のチェック
    private boolean Student_Namecheck(String name) {
        // 漢字、ひらがな、カタカナ、英語のみで構成されているかどうかをチェックする
        return name.matches("[一-龯ぁ-んァ-ヶa-zA-Z]+");
    }
}
