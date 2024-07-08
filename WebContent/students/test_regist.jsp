<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    // セッションを取得
    HttpSession sessions = request.getSession();

    // "teacher"属性がnullかどうかを確認
    if (sessions.getAttribute("teacher") == null) {
        // "teacher"属性がnullの場合、ログインページにフォワード
        request.getRequestDispatcher("/login/login.jsp").forward(request, response);
        return; // フォワード後に処理を中断
    }
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<%-- <%@ page import="bean.Subject, dao.SubjectDAO, java.util.List" %> --%>

<%-- <%
// CourseDAOクラスを用いて、全コースの情報を取得。
    SubjectDAO dao = new SubjectDAO();
    List<Subject> list = dao.selectAll();
%> --%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        <h1 class="toptitle">得点管理システム</h1>
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
            <div class="my-2 text-end px-4">
                <a href="student_in.jsp">新規登録</a>
            </div>
            <form action="search" method="post">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select name="year">
					    <option value="">----</option>
						<%for (int year = 2014; year <= 2024; year++) {%>
						<option value="<%=year %>" ><%=year %></option>
						<%
					        }
					    %>
						</select>
                    </div>

                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select name="class">
			                <option value="">---</option>
			                <option value="101">101</option>
			                <option value="201">201</option>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">科目</label>
                        <select name="sub_cd" id="sub_cd">
                    <%-- <% for (Subject sub : list) { %>
                        <option value="<%= sub.getSubCd() %>"><%= sub.getSubName()%></option>
                    <% } %> --%>
                </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">回数</label>
                        <select name="class">
			                <option value="">---</option>
			                <option value="1">1</option>
			                <option value="2">2</option>
                        </select>
                    </div>
                    <div class="col-2 text-center">
                        <div><input type="submit" value="検索">
                        </div>
                    </div>
                </div>
            </form>
            <c:choose>
                <c:when test="${students.size()>0}">
                    <div>検索結果:${students.size()}件</div>
                    <table class="table table-hover">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.getEnt_Year() }</td>
                                <td>${student.getClass_No() }</td>
                                <td>${student.getStudent_No() }</td>
                                <td>${student.getStudent_Name() }</td>
                                <td><input type="text" id="score" name="score" pattern="0|[1-9][0-9]{0,1}|100"
					 value="<%-- <%=testBean.getTestScore()%> --%>" class="form-input" ></td>




                                <td><a href="student _update.jsp?Year=${student.getEnt_Year()}&Student_no=${student.getStudent_No()}&Student_name=${student.getStudent_Name()}&Student_kana=${student.getStudent_Kana()}&Class_No=${student.getClass_No()}&Gender=${student.getStu_Seibetu()}&Is_Attend=${student.getIs_Attend()}">登録</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>

                <c:otherwise>
                    <div>学生情報が存在しませんでした</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>