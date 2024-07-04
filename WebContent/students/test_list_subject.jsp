<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- 文字化けの対策 --%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> --%>


<!DOCTYPE html>
<html>
<head>
<title>得点管理システム</title>
</head>
<body>
	<c:import url="TestListAction">
		<c:param name="isFromSubject" value="true" />
	</c:import>
	<!--     <h2>成績一覧（科目）</h2> -->

	<c:choose>
		<c:when test="${not empty testList}">
			<p>
				並び替え: <a
					href="TestListSubjectAction?entYear=${param.entYear}&classNo=${param.classNo}&subcd=${param.subcd}&sort=studentNo">学生番号順</a>
				<a
					href="TestListSubjectAction?entYear=${param.entYear}&classNo=${param.classNo}&subcd=${param.subcd}&sort=kana">フリガナ順</a>
			</p>
			<p>科目：${testList[0].subName}</p>

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
		</c:when>
		<c:otherwise>
			<c:if test="${empty errorMessage}">
				<p>学生情報が存在しませんでした</p>
			</c:if>
		</c:otherwise>
	</c:choose>

</body>
</html>
