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

        PrintWriter out = response.getWriter();

        Page.header(out);

        try {
            int pointSize = Integer.parseInt(request.getParameter("student_count"));
            String subCd = request.getParameter("sub_cd");
            int testNo = Integer.parseInt(request.getParameter("test_no"));
            String scCd = "111";

            List<Test> pointList = new ArrayList<>();
            boolean hasValidInput = false;
            int i = 1;
            while (i <= pointSize) {
                String pointParam = request.getParameter("point" + i);
                if (pointParam != null && !pointParam.trim().isEmpty()) {
                    try {
                        int point = Integer.parseInt(pointParam);
                        if (point >= 0 && point <= 100) {
                            hasValidInput = true;
                            Test test = new Test();
                            test.setStudent_No(request.getParameter("stucd" + i));
                            test.setSubject_Cd(subCd);
                            test.setSchool_Cd(scCd);
                            test.setTest_No(testNo);
                            test.setPoint(point);
                            test.setClass_Num(request.getParameter("clsno" + i));
                            pointList.add(test);
                        }
                    } catch (NumberFormatException e) {
                        // 無視して次のループへ
                    }
                }
                i++;
            }

            if (hasValidInput) {
                TestDAO dao = new TestDAO();
                int line = dao.Insert2(pointList);

                if (line > 0) {
                    request.setAttribute("message", "登録が完了しました");
                } else {
                    request.setAttribute("message", "登録する成績がありません");
                }
                request.getRequestDispatcher("/students/test_regist_done.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "登録する成績がありません");
                request.getRequestDispatcher("/students/test_regist_done.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace(out);
        }

        Page.footer(out);
    }
}
