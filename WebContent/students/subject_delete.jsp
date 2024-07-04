<%-- 科目削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.*"%>

<%-- 文字化けの対策 --%>
<%
	request.setCharacterEncoding("UTF-8");
%>

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
			<h2>科目情報削除</h2>
			<form action="SubjectDeleteComplete" method="post">
				<table class="form-table">
					<tr>
						<%
							if (subjectBean.getSubName() == null) {
						%>
						<td>「<%=(String) request.getParameter("subName")%>（<%=(String) request.getParameter("subCd")%>）」は既に削除されています
						</td>
					</tr>
				</table>
				<%
					} else {
				%>
				<td>「<%=subjectBean.getSubName()%>（<%=subjectBean.getSubCd()%>）」を削除してもよろしいですか
				</td>
				</tr>
				</table>
				<button type="submit" class="form-button"
					style="color: white; background-color: red;">削除</button>
				<%
					}
				%>

				<input type="hidden" name="schoolCd"
					value="<%=subjectBean.getSchoolCd()%>">
				<input type="hidden" name="subCd" value="<%=subjectBean.getSubCd()%>">
				<input type="hidden" name="subName"
					value="<%=subjectBean.getSubName()%>">

			</form>

			<!-- 戻るリンク -->
			<div>
				<a href="SubjectListAction">戻る</a>
			</div>

        </section>
    </c:param>
</c:import>