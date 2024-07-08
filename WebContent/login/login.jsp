<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<%@page import="java.sql.*"%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base_login.jsp">
    <c:param name="title">
        <h1 class="toptitle">得点管理システム</h1>
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <h2>ログイン</h2>
                    <p style="color: red">${errorMessage}</p>
                    <form action="loginaction" method="post">
                        <div class="form-group">
                            <label for="userId">ID</label>
                            <input type="text" class="form-control" name="userId" placeholder="半角でご入力ください" maxlength="20" style="ime-mode:disabled" required>
                        </div>
                        <div class="form-group">
                            <label for="password">パスワード</label>
                            <div class="input-group">
                                <input type="password" class="form-control" name="password" placeholder="20文字以内の半角英数字でご入力ください" maxlength="20" style="ime-mode:disabled" required>
                            </div>
                        </div>
                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="chk_d_ps" onclick="togglePassword()">
                            <label class="form-check-label" for="chk_d_ps">パスワードを表示</label>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">ログイン</button>
                    </form>
                </div>
            </div>
        </div>
    </c:param>
</c:import>

<script type="text/javascript">
    function togglePassword() {
        let passwordField = document.getElementsByName('password')[0];
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
        } else {
            passwordField.type = 'password';
        }
    }
</script>
