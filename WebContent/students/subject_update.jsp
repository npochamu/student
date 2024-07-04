<%-- 科目変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<%@page import="bean.Subject"%>


<%-- subjectBeanから科目情報を取得する --%>
<%
	Subject subjectBean = (Subject) request.getAttribute("subjectBean");
%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		<h1 class="toptitle">得点管理システム</h1>
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="mo-4">
			<h2>科目情報変更</h2>

			<form action="SubjectUpdateComplete" method="post">
				<table class="form-table">
<!-- 					<tr> -->
<!-- 						<td><label for="schoolCd">学校コード:</label></td> -->
<%-- 						<td><%=subjectBean.getSchoolCd()%></td> --%>
<!-- 					</tr> -->
					<tr>
						<td><label for="subCd">科目コード:</label></td>
						<td>${param.subCd!= null ? param.subCd : ''}</td>
						<%-- 				<td>${subjectBean.subCd}</td> --%>
					</tr>
					<tr>
						<td><label for="subName">科目名:</label></td>
						<td><input type="text" id="subName" name="subName"
							class="form-input" placeholder="科目名を入力してください"
							value="${param.subName!= null ? param.subName : ''}" required></td>
					</tr>
				</table>

				<!-- メッセージ表示：リクエストした科目名と、変更クリック後にサーブレットで取得し直した科目名が異なる場合-->
				<c:if
					test="${subjectBean.subName != null && param.subName != subjectBean.subName}">
					<p style="color: red;">科目名は現在「${subjectBean.subName}」に変更されています</p>
				</c:if>
				<!-- メッセージ表示：科目更新対象が削除済だった場合-->
				<c:if test="${subjectBean.getSubCd()==null}">
					<p style="color: red;">科目が存在していません</p>
				</c:if>

				<button type="submit" class="form-button">変更</button>

				<input type="hidden" name="schoolCd" value="${subjectBean.schoolCd}">
				<input type="hidden" name="subCd" value="${subjectBean.subCd}">
				<input type="hidden" name="subName" value="${subjectBean.subName}">

			</form>

			<!-- 戻るリンク -->
			<div>
				<a href="SubjectListAction">戻る</a>
			</div>

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





		</section>
	</c:param>
</c:import>