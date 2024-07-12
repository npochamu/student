<%-- 成績登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
								<c:forEach begin="2014" end="2025" var="year">
									<option value="${year}"
									<c:if test="${year == param.ent_year}">selected</c:if>>${year}</option>
								</c:forEach>
							</select> <br>
							<c:if test="${not empty yearError}">
								<span style="color: red;">${yearError}</span>
							</c:if>
						</div>

						<div class="col-2">
							<label class="form-label" for="class_num">クラス</label> <select
								name="class_num" , id="class_num" class="form-control">
								<!-- ★ここにもif~selected入れる -->
								<option value="">---</option>
								<option value="101">101</option>
								<option value="201">201</option>
							</select> <br>
							<c:if test="${not empty class_numError}">
								<span style="color: red;">${class_numError}</span>
							</c:if>

						</div>
						<div class="col-4">
							<label class="form-label" for="sub_cd">科目</label> <select
								name="sub_cd" id="sub_cd" class="form-control">
								<option value="">---</option>
								<c:forEach var="test" items="${list}">
									<%--                                     <option value=${test.getSub_Cd()}> ${test.getSub_Name()}</option> --%>
								<!-- ★ここにもif~selected入れる -->
									<option value=${test.subject_Cd}>
										${test.getSubject_Name()}</option>
								</c:forEach>
							</select> <br>
							<c:if test="${not empty sub_cdError}">
								<span style="color: red;">${sub_cdError}</span>
							</c:if>
						</div>

						<div class="col-2">
							<label class="form-label" for="test_no">回数</label> <select
								name="test_no" id="test_no" class="form-control">
								<option value="">---</option>
								<option value="1"<c:if test="${param.test_no=='1'}">selected</c:if>>1</option>
								<option value="2"<c:if test="${param.test_no=='2'}">selected</c:if>>2</option>
							</select> <br>
							<c:if test="${not empty test_noError}">
								<span style="color: red;">${test_noError}</span>
							</c:if>
						</div>
						<div class="col-2 text-center">
							<button type="submit" class="btn btn-secondary">検索</button>
						</div>
					</div>
				</form>
				<c:choose>
					<c:when test="${students.size() > 0}">
						<div class="ml-3">科目：${students[0].subject_Name}（${param.test_no}回）
							検索結果：${students.size()}件</div>
						<form action="test_regist" method="post"
							onsubmit="return validateScores();" class="ml-3">
							<table class="table table-hover" style="width: 80%">
								<tr>
									<th style="width: 15%">入学年度</th>
									<th style="width: 15%">クラス</th>
									<th style="width: 20%">学生番号</th>
									<th style="width: 30%">氏名</th>
									<th style="width: 20%">点数</th>
								</tr>
								<c:forEach var="test" items="${students}" varStatus="status">
									<tr>
										<td>${test.getEnt_Year()}</td>
										<td>${test.getClass_Num()}</td>
										<td>${test.getStudent_No()}</td>
										<td>${test.getStudent_Name()}</td>
<!-- 										<td><input type="text" id="point" name="point" -->
<%-- 															value="${test.getPoint()}" class="form-control" required></td> --%>
											<td><input type="text" id="point" name="point${status.count}"

															value="${test.getPoint()}" class="form-control"  pattern="^(100|[1-9]?[0-9])$" title="0～100の範囲で入力してください" required></td>
												</tr>
												<input type="hidden" name="stucd${status.count}" value="${test.getStudent_No()}">
												<input type="hidden" name="clsno${status.count}" value="${test.getClass_Num()}">
											</c:forEach>
							</table>
							<button type="submit" class="btn btn-secondary"
								id="filter-button">登録して終了</button>
						</form>
					</c:when>
				</c:choose>
			</section>
		</c:param>
	</c:import>
	<c:import url="/common/footer.jsp" />

