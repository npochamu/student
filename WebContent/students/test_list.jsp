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
<!-- <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2> -->
    <!-- test_list_subject.jsp から呼び出される場合のみ表示 -->
    <c:choose>
    <c:when test="${empty param.isFromSubject}">
        <h2>成績参照</h2>
    </c:when>
    <c:otherwise>
    	<h2>成績一覧（科目）</h2>
    </c:otherwise>
    </c:choose>


<form action="TestListSubjectAction" method="get">
<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

<div class="col-8">
<div class="row">
<label>科目情報</label>

<div class="form-group col-2">
        <label class="form-label" for="entYear">入学年度</label>
	        <select class="form-control" name="entYear">
			<option value="">--------</option>
			<%for (int year = 2014; year <= 2025; year++) {%>
			<option value="<%=year %>" ><%=year %></option>
			<% } %>
			</select>
</div>

<div class="form-group col-2">
			<label class="form-label" for="classNo">クラス</label>
			<select name="classNo" id="classNo">
			<option value="">--------</option>
			    <c:forEach var="cls" items="${clslist}">
			        <option value="${cls}">${cls}</option>
			    </c:forEach>
			</select>
</div>
<div class="form-group col-4">
        <label class="form-label" for="subCd">科目</label>
        <select name="subcd" id="subcd">
        <option value="">--------</option>
        <c:forEach var="sub" items="${sublist}">
		<option value=${sub.subCd}>${sub.subName}（${sub.subCd}）</option>
		</c:forEach>
		</select>
        <input type="submit" value="検索">
 </div>
</div>
</form>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-warning">${errorMessage}</div>
    </c:if>



<hr>

			<form action="subject_score_search" method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-8">
						<label>学生情報</label>
					    <label for="studentNumber">学生番号: </label>
					    <input type="text" id="student_no" name="student_no" placeholder="学生番号を入力してください" required class="form-control" value="${param.student_no}">
   						<!-- エラーメッセージの表示 -->
						<c:if test="${not empty errorMessage}">
						    <p style="color:red;">${errorMessage}</p>
						</c:if>
					    <input type="submit" value="検索">
					</div>
				</div>
			</form>
			<c:choose>
				<c:when test="${test.size()>0}">
                    <div>検索結果:${test.size()}件</div>
            	<p>氏名：${test[0].student_Name} (${test[0].student_No})</p>
                    <table class="table table-hover">
                        <tr>
                            <th>科目名</th>
                            <th>科目コード</th>
                            <th>回数</th>
                            <th>点数</th>
                        </tr>
                         <c:forEach var="test" items="${test}">
                            <tr>
                                <td>${test.getSub_Name()}</td>
                                <td>${test.getSubject_Cd()}</td>
                                <td>${test.getTest_No()}</td>
                                <td>${test.getPoint()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
			    <c:otherwise>
			  <p>氏名：${test[0].student_Name} (${test[0].student_No})</p>
					<div>成績情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>

<c:if test="${empty param.isFromSubject}">
<p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
</c:if>

</section>
</c:param>
</c:import>