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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理(学生)</h2>

			<form action="student_score_search" method="post">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-4">
						<label>科目情報</label>
					</div>
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
						<select name="dubject" id="subject">
						<option value="">---</option>
						<option value="国語">国語</option>
						<option value="数学">数学</option>
						</select>
					</div>
					<div class="col-4">
						<input type="submit" value="検索">
					</div>
				</div>
			</form>

			<form action="subject_score_search" method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-4">
						<label>学生情報</label>
					    <label for="studentNumber">学生番号: </label>
					    <input type="text" id="student_no" name="student_no" placeholder="学生番号を入力してください" required class="form-control" value="${param.student_no}">
   						<!-- エラーメッセージの表示 -->
						<c:if test="${not empty errorMessage}">
						    <p style="color:red;">${errorMessage}</p>
						</c:if>
					    <input type="submit" value="検索">
					</div>
				</div>
			</form>
			<c:choose>
				<c:when test="${test.size()>0}">
                    <div>検索結果:${test.size()}件</div>
            		<p>氏名：${test[0].student_Name} (${test[0].student_No})</p>
                    <table class="table table-hover">
                        <tr>
                            <th>科目名</th>
                            <th>科目コード</th>
                            <th>回数</th>
                            <th>点数</th>
                        </tr>
                         <c:forEach var="test" items="${test}">
                            <tr>
                                <td>${test.getSub_Name()}</td>
                                <td>${test.getSubject_Cd()}</td>
                                <td>${test.getTest_No()}</td>
                                <td>${test.getPoint()}</td>
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