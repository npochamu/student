<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Filtered Test Scores</title>
</head>
<body>
    <h1>科目別成績一覧</h1>
    <form method="get" action="score">
        <label for="entYear">入学年度:</label>
        <input type="text" id="entYear" name="entYear" value="${param.entYear}">
        <label for="classNo">クラス:</label>
        <input type="text" id="classNo" name="classNo" value="${param.classNo}">
        <label for="subCd">科目コード:</label>
        <input type="text" id="subCd" name="subCd" value="${param.subCd}">
        <input type="submit" value="フィルター">
    </form>

    <table border="1">
        <thead>
            <tr>
                <th>科目コード</th>
                <th>科目名</th>
                <th>入学年度</th>
                <th>クラス</th>
                <th>学生番号</th>
                <th>氏名</th>
                <th>1回</th>
                <th>2回</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.subCd}</td>
                    <td>${item.subName}</td>
                    <td>${item.entYear}</td>
                    <td>${item.classNo}</td>
                    <td>${item.studentNo}</td>
                    <td>${item.studentName}</td>
                    <td>${item.test_1}</td>
                    <td>${item.test_2}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
