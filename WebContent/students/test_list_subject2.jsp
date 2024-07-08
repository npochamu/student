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

	<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="http://localhost:8080/student_sc/TestListAction" />
<html>
<head>
    <title>得点管理システム</title>
</head>
<body>
    <h2>成績一覧（科目）</h2>

    <p>
        並び替え:
        <a href="TestListSubjectAction?entYear=${param.entYear}&classNo=${param.classNo}&subcd=${param.subcd}&sort=studentNo">学生番号順</a>
        <a href="TestListSubjectAction?entYear=${param.entYear}&classNo=${param.classNo}&subcd=${param.subcd}&sort=kana">フリガナ順</a>
    </p>
	<p>科目：${testList[0].subName}	</p>

    <table border="1">
<%--     <caption>科目：${testList[0].subName}</caption> --%>
        <tr>
            <th>入学年度</th>
            <th>クラス</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>フリガナ</th>
            <th>性別</th>
            <th>１回</th>
            <th>２回</th>
        </tr>
        <c:forEach var="test" items="${testList}">
            <tr>
                <td>${test.entYear}</td>
                <td>${test.classNo}</td>
                <td>${test.studentNo}</td>
                <td>${test.studentName}</td>
                <td>${test.kana}</td>
                <td>${test.seibetu}</td>
                <td>${test.test_1}</td>
                <td>${test.test_2}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>