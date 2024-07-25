<%-- 成績登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


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
<%
	request.setCharacterEncoding("UTF-8");
%>

<%@ page import="bean.Test, dao.TestDAO, java.util.List"%>

<style>
.error-border {
	border: 2px solid red;
}
</style>

<c:import url="/common/base.jsp">

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="mo-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">成績管理</h2>

			<!-- エラーメッセージの表示 -->
			<c:if test="${not empty errorMessage}">
				<p style="color: red;">${errorMessage}</p>
			</c:if>

			<form action="test_search" method="post">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded"
					id="filter">
					<div class="col-2">
						<label class="form-label" for="ent_year">入学年度</label> <select
							name="ent_year" id="ent_year" class="form-control">
							<option value="">----</option>
							<c:forEach begin="2014" end="2034" var="year">
								<option value="${year}"
									<c:if test="${year == param.ent_year}">selected</c:if>>${year}</option>
							</c:forEach>
						</select> <br>
					</div>

					<div class="col-2">
						<label class="form-label" for="class_num">クラス</label> <select
							name="class_num" , id="class_num" class="form-control">
							<option value="">--------</option>
							<c:forEach var="cls" items="${clslist}">
								<option value="${cls}"
									<c:if test="${cls==param.class_num}">selected</c:if>>${cls}</option>
							</c:forEach>
						</select><br>


					</div>
					<div class="col-4">
						<label class="form-label" for="sub_cd">科目</label> <select
							name="sub_cd" id="sub_cd" class="form-control">
							<option value="">---</option>
							<c:forEach var="test" items="${list}">
								<option value="${test.subject_Cd}"
									${param.sub_cd == test.subject_Cd ? 'selected' : ''}>
									${test.subject_Name}</option>
							</c:forEach>
						</select> <br>

					</div>

					<div class="col-2">
						<label class="form-label" for="test_no">回数</label> <select
							name="test_no" id="test_no" class="form-control">
							<option value="">---</option>
							<option value="1"
								<c:if test="${param.test_no=='1'}">selected</c:if>>1</option>
							<option value="2"
								<c:if test="${param.test_no=='2'}">selected</c:if>>2</option>
						</select> <br>

					</div>
					<div class="col-2 text-center">
						<button type="submit" class="btn btn-secondary">検索</button>
					</div>

					<span class="ml-3"> <c:set var="errorMessages" value="" />

						<c:if test="${not empty yearError}">
							<c:set var="errorMessages" value="${errorMessages}入学年度と" />
						</c:if> <c:if test="${not empty classError}">
							<c:set var="errorMessages" value="${errorMessages}クラスと" />
						</c:if> <c:if test="${not empty sub_cdError}">
							<c:set var="errorMessages" value="${errorMessages}科目と" />
						</c:if> <c:if test="${not empty test_noError}">
							<c:set var="errorMessages" value="${errorMessages}回数と" />
						</c:if> <c:if test="${not empty errorMessages}">
							<span style="color: red;"> <c:out
									value="${fn:substring(errorMessages, 0, fn:length(errorMessages) - 1)}" />を選択してください
							</span>
						</c:if>
					</span>

				</div>
			</form>
			<c:choose>
				<c:when test="${students.size() > 0}">
					<div class="ml-3">科目：${students[0].subject_Name}（${param.test_no}回）
						検索結果：${students.size()}件</div>
					<form action="test_regist" method="post" " class="ml-3">
						<table class="table table-hover" style="width: 85%">
							<tr>
								<th style="width: 15%">入学年度</th>
								<th style="width: 12%">クラス</th>
								<th style="width: 15%">学生番号</th>
								<th style="width: 25%">氏名</th>
								<th style="width: 33%">点数</th>
							</tr>
							<c:forEach var="test" items="${students}" varStatus="status">
								<tr>
									<td>${test.getEnt_Year()}</td>
									<td>${test.getClass_Num()}</td>
									<td>${test.getStudent_No()}</td>
									<td>${test.getStudent_Name()}</td>
									<td class="py-1"><input type="text"
										id="point${status.count}" name="point${test.getStudent_No()}"
										value="${test.getPoint_str()}" class="form-control">
								    <c:if test="${!test.point_str.equals('') && !test.point_str.matches('^(100|[1-9]?[0-9])$')}">
								        <small style="color: red;">0～100の範囲で入力してください</small>
								    </c:if></td>
								</tr>
								<input type="hidden" name="stucd${status.count}" value="${test.getStudent_No()}">
								<input type="hidden" name="clsno${status.count}" value="${test.getClass_Num()}">
								<input type="hidden" name="entye${status.count}" value="${test.getEnt_Year()}">
								<input type="hidden" name="stuna${status.count}" value="${test.getStudent_Name()}">
							</c:forEach>
						</table>
							<input type="hidden" name="student_count" value="${students.size()}">
							<input type="hidden" name="ent_year" value="${param.ent_year}">
							<input type="hidden" name="class_num" value="${param.class_num}">
							<input type="hidden" name="sub_cd" value="${param.sub_cd}">
							<input type="hidden" name="test_no" value="${param.test_no}">
							<input type="hidden" name="sub_name" value="${students[0].subject_Name}">
<!-- 							登録したい数値が1件もないときのメッセージ -->
						<p style="color: red;">${noUpsertMsg}</p>
						<button type="submit" class="btn btn-secondary" id="filter-button">登録して終了</button>
					</form>
				</c:when>
				<c:when test="${students.size()==0 and empty errorMessages}">
					<div class="ml-3">学生情報が存在しませんでした</div>
				</c:when>
			</c:choose>
		</section>
	</c:param>
</c:import>

<c:import url="/common/footer.jsp" />