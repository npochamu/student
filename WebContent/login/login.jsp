<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<%@page import="java.sql.*"%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        <h1 class="toptitle">得点管理システム</h1>
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

		<h2>ログイン</h2>
		<p style="color: red">
	        ${errorMessage}
	    </p>
		<form action="loginaction" method="post">
			ユーザID： <input type="text" name="userId" placeholder="半角でご入力ください" maxlength="20" required><br>
			パスワード： <input type="password" name="password" placeholder="20文字以内の半角英数字でご入力ください" maxlength="20" style="ime-mode: disabled" required><br>
	    <input type="checkbox" onclick="togglePassword()" name="chk_d_ps">
	    <label for="chk_d_ps">パスワードを表示</label>

	    <script type="text/javascript">
	        // id="password"を取得
	        let inputtype = document.getElementsByName('password')[0];

	        function togglePassword() {

	            if (inputtype.type === 'password') {
	                inputtype.type = 'text';
	            } else {
	                inputtype.type = 'password';
	            }
	        }
		</script>
			<input type="submit" value="ログイン">
		</form>
	</c:param>
</c:import>
