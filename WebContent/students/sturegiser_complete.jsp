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
<link rel="stylesheet" href="/css/bootstrap.min.css" />

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base.jsp">

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
        	<div style="background-color: #f0f0f0;">
            <h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">${insert}</h2>
            </div>
            <div class="my-2 text-end px-4">
            	<div style="background-color: lightgreen; text-align: center;">
                <p>${insert2}</p>
                </div>
				<c:if test="${insert == '学生情報登録' }">
                	<a href="classall" style="margin-right: 15px;">戻る</a>
                </c:if>
                <a href="../students/all">学生一覧</a>
            </div>
        </section>
    </c:param>
</c:import>

<c:import url="/common/footer.jsp"/>