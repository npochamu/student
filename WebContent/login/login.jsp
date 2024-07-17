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
                            <input type="text" class="form-control" name="userId" placeholder="半角でご入力ください" value="${param.userId }" maxlength="20" style="ime-mode:disabled" required>
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
<br>
<br>
<br>
<c:import url="/common/footer.jsp"/>

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


[水曜日 11:22] 関野　桂子
<h2>Request Parameters:</h2>
<ul>
   <%
      java.util.Enumeration<String> parameterNames = request.getParameterNames();
       while (parameterNames.hasMoreElements()) {
           String paramName = parameterNames.nextElement();
           String paramValue = request.getParameter(paramName);
          out.println("<li>" + paramName + ": " + paramValue + "</li>");
       }
   %>
</ul>

<h2>Request Attributes:</h2>
<ul>
   <%
      java.util.Enumeration<String> attributeNames = request.getAttributeNames();
       while (attributeNames.hasMoreElements()) {
           String attributeName = attributeNames.nextElement();
           Object attributeValue = request.getAttribute(attributeName);

           if (attributeValue instanceof java.util.List) {
              java.util.List<?> list = (java.util.List<?>) attributeValue;
              out.println("<li>" + attributeName + ":");
              out.println("<ul>");
               for (Object item : list) {
                  out.println("<li>" + item + "</li>");
               }
              out.println("</ul>");
              out.println("</li>");
           } else {
              out.println("<li>" + attributeName + ": " + attributeValue + "</li>");
           }
       }
   %>
</ul>

