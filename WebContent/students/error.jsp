<%-- エラーページJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/css/bootstrap.min.css" />

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base_login.jsp">
    <c:param name="title">
        <h1 class="toptitle">得点管理システム</h1>

    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">

            <div class="my-2 text-end px-4">
	<p>エラーが発生しました</p>
<!-- 	<p>管理者にお問い合わせください</p> -->
             <br>
            <br>
            <br>
            <br>
            <br>
        </section>
    </c:param>
</c:import>
<c:import url="/common/footer.jsp"/>