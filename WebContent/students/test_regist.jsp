<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<%@ page import="bean.Test, dao.TestDAO, java.util.List" %>

<%-- <%
// CourseDAOクラスを用いて、全コースの情報を取得。
    SubjectDAO dao = new SubjectDAO();
    List<Subject> list = dao.selectAll();
%> --%>

<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title>

    <style>
		.error-border { border: 2px solid red; }
	</style>

    <script type="text/javascript">
        function validateScores() {
            var scores = document.querySelectorAll("input[name='point']");
            var errorMessage = document.getElementById("error-message");
            for (var i = 0; i < scores.length; i++) {
                var score = parseInt(scores[i].value);
                if (isNaN(score) || score < 0 || score > 100) {
                    errorMessage.innerText = "0～100の間の範囲で入力してください。";
                    return false;
                }
            }
            errorMessage.innerText = "";
            return true;
        }
    </script>
</head>
<body>
    <c:import url="/common/base.jsp">
        <c:param name="title">
            <h1 class="toptitle">得点管理システム</h1>
        </c:param>

        <c:param name="scripts"></c:param>

        <c:param name="content">
            <section class="mo-4">
                <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

                 <!-- エラーメッセージの表示 -->
            <c:if test="${not empty errorMessage}">
                <p style="color:red;">${errorMessage}</p>
            </c:if>

                <div class="my-2 text-end px-4">
                    <a href="student_in.jsp">新規登録</a>
                </div>
                <form action="test_search" method="post">
                    <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                        <div class="col-4">
                            <label class="form-label" for="student-f1-select">入学年度</label>
                            <select name="year" id="year">
                                <option value="">----</option>
                                <%for (int year = 2014; year <= 2024; year++) {%>
                                <option value="<%=year %>" ><%=year %></option>
                                <% } %>
                            </select>
                            <br><c:if test="${not empty yearError}">
                				<span style="color:red;">${yearError}</span>
            				</c:if>
                        </div>

                        <div class="col-4">
                            <label class="form-label" for="student-f2-select">クラス</label>
                            <select name="class", id="class">
                                <option value="">---</option>
                                <option value="101">101</option>
                                <option value="201">201</option>
                            </select>
                            <br><c:if test="${not empty classError}">
				                <span style="color:red;">${classError}</span>
				            </c:if>

                        </div>
                        <div class="col-4">
                            <label class="form-label" for="student-f2-select">科目</label>
                            <select name="sub_cd" id="sub_cd">
                                <option value="">---</option>
                                <c:forEach var="test" items="${list}">
                                    <option value=${test.getSubject_Cd()}> ${test.getSubName()}</option>
                                </c:forEach>
                            </select>
                            <br><c:if test="${not empty sub_cdError}">
				                <span style="color:red;">${sub_cdError}</span>
				            </c:if>
                        </div>

                        <div class="col-4">
                            <label class="form-label" for="student-f2-select">回数</label>
                            <select name="test_no" id="test_no">
                                <option value="">---</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                            </select>
                            <br><c:if test="${not empty test_noError}">
				                <span style="color:red;">${test_noError}</span>
				            </c:if>
                        </div>
                        <div class="col-2 text-center">
                            <div><input type="submit" value="検索"></div>
                        </div>
                    </div>
                </form>
                <c:choose>
                    <c:when test="${students.size() > 0}">
                        <div>検索結果:${students.size()}件</div>
                        <form action="test_regist" method="post" onsubmit="return validateScores();">
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
                                <c:forEach var="test" items="${students}">
                                    <tr>
                                        <td>${test.getEnt_Year()}</td>
                                        <td>${test.getClass_No()}</td>
                                        <td>${test.getStudent_No()}</td>
                                        <td>${test.getStudent_Name()}</td>
                                        <td><input type="number" id="point" name="point" value="<%--<%= testBean.getTestScore() %> --%>" class="form-input">
										</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <button type="submit" class="btn btn-secondary" id="filter-button">登録して終了</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div>学生情報が存在しませんでした</div>
                    </c:otherwise>
                </c:choose>
            </section>
        </c:param>
    </c:import>
</body>
</html>
