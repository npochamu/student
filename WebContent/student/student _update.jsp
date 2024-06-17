<%-- 学生一覧JSP --%>
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
<h2>学生情報変更</h2>
<form action ="updateinsert" method="post">
<div class="d-flex flex-column border mx-3 mb-3 py-2 px-4 align-items-start rounded" id="filter">
<div class="mb-3">
<label class="form-label" for="ent_year">入学年度</label>
<span id="ent_year"><%String Year=request.getParameter("Year"); %><%=Year %></span>
<input type="hidden" id="ent_year" name="ent_year" value=<%=Year %>>
</div>
<div class="mb-3">
<label class="form-label" for="student_id">学生番号</label>
<span id="student_id_display"><%String Student_no=request.getParameter("Student_no"); %><%=Student_no %></span>
<input type="hidden" id="student_id" name="student_id" value="<%=Student_no %>">
</div>
<div class="mb-3">
<label class="form-label" for="name">氏名</label>
<input type="text" id="name" name="name" placeholder="氏名を入力してください" required class="form-control" value="<%String Student_name=request.getParameter("Student_name"); %><%=Student_name %>">
</div>
<div class="mb-3">
<label class="form-label" for="name_kana">フリガナ</label>
<input type="text" id="name_kana" name="name_kana" placeholder="フリガナを入力してください" required class="form-control" value="<%String Student_kana=request.getParameter("Student_kana"); %><%=Student_kana %>">
</div>
<div class="mb-3">
<label class="form-label" for="classnum">クラス</label>
<%String Class_No=request.getParameter("Class_No"); %>
<%request.setAttribute("Class_No", Class_No); %>
<select id="classnum" name="classnum" class="form-select">
<option value="101" <c:if test="${Class_No == 101}">selected</c:if>>101</option>
<option value="201" <c:if test="${Class_No == 201}">selected</c:if>>201</option>
</select>
</div>
<div class="mb-3">
<label class="form-label" for="gender">性別</label>
<%String Gender=request.getParameter("Gender"); %>
<%request.setAttribute("Gender", Gender); %>
<select id="gender" name="gender" class="form-select">

<option value="男" <c:if test="${Gender == '男'}">selected</c:if>>男</option>
<option value="女" <c:if test="${Gender == '女'}">selected</c:if>>女</option>
</select>
</div>
<div class="mb-3 form-check">
<%String Is_Attend=request.getParameter("Is_Attend"); %>
<%request.setAttribute("Is_Attend", Is_Attend); %>
<input class="form-check-input" type="checkbox" id="isattend" name="isattend" value="true" <c:if test="${!empty Is_Attend}">checked</c:if> />
<label class="form-check-label ms-2" for="isattend" >在学中</label>
</div>
<div class="mb-3 text-center">
<button type="submit" class="btn btn-secondary" id="filter-button">変更</button>
</div>
<div class="mt-2 text-warning">${errors.get("f1")}</div>
</div>
</form>

            <!-- 戻るリンク -->
<div>
<a href="student_list.jsp">戻る</a>
</div>
</section>
</c:param>
</c:import>
