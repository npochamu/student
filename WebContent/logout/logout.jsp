<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/css/bootstrap.min.css" />

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

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base_login.jsp">
    <c:param name="title">
        <h1 class="toptitle">得点管理システム</h1>

    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
        	<div style="background-color: #f0f0f0;">
            <h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">ログアウト</h2>
            </div>
            <div class="my-2 text-end px-4">
            	<div style="background-color: lightgreen; text-align: center;">
                <p>ログアウトしました</p>
                </div>
                <a href="../login/login.jsp" style="margin-right: 15px;">ログイン</a>

            </div>
        </section>
    </c:param>
</c:import>
<br>
<br>
<br>
<c:import url="/common/footer.jsp"/>