<%-- 成績参照JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>


<c:import url="/common/base.jsp">
<c:param name="title">
<h1 class="toptitle">得点管理システム</h1>
</c:param>

    <c:param name="scripts"></c:param>
    <c:param name="content">
<section class="mo-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
<p>科目情報</p>
<form action="TestListSubjectAction" method="get">
<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
<div class="col-4">
        <label class="form-label" for="entYear">入学年度</label>
	        <select name="entYear">
			<option value="">----</option>
			<%for (int year = 2014; year <= 2025; year++) {%>
			<option value="<%=year %>" ><%=year %></option>
			<% } %>
			</select>
        <label class="form-label" for="classNo">クラス</label>
        <select name="classNo" id="classNo">
        <c:forEach var="cls" items="${clslist}">
		<option value=${cls.classNo}>${cls.classNo}</option>
		</c:forEach>
		</select>

        <label class="form-label" for="subCd">科目</label>
        <select name="subcd" id="subcd">
        <c:forEach var="sub" items="${sublist}">
		<option value=${sub.subCd}>${sub.subName}（${sub.subCd}）</option>
		</c:forEach>
		</select>
        <input type="submit" value="検索">

</form>
</section>
</c:param>
</c:import>