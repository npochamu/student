<%-- 処理完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		<h1 class="toptitle">得点管理システム</h1>

	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="mo-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報${param.view}</h2>
			<p>${param.viewMsg}</p>

			<!-- 戻るリンク -->
			<div>
				<a href="SubjectListAction">戻る</a>
			</div>
			<div>
				<a href="<%="SubjectListAction"%>">科目一覧</a>
			</div>
		</section>
	</c:param>
</c:import>



