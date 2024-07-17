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

<c:import url="/common/base.jsp">

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">成績管理</h2>
            <div class="my-2 text-end px-4">
            <div style="background-color: lightgreen; text-align: center;">
<!--             完了or登録ゼロのメッセージ -->
			<p>${message}</p>
			</div>
			</div>



            <!-- 戻るリンク -->
	        <div class="ml-4">
	            <a href="../students/test_insert">戻る</a>
	            <a href="../students/TestListAction" class="ml-5">成績参照</a>
	        </div>
        </section>
    </c:param>
</c:import>
<c:import url="/common/footer.jsp"/>