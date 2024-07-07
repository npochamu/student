<%-- 成績参照JSP 検索画面と成績一覧（学生） --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<%
	request.setCharacterEncoding("UTF-8");
%>


<c:import url="/common/base.jsp">
    <c:param name="title">
            <header class="main-header">
        <nav class="navbar navbar-expand-lg ">
            <div class="container">
        <h1 class="toptitle">得点管理システム</h1>
                <div class="navbar-" id="navbarNav">
                    <ul class="navbar-nav">
                            <span>${teacher.getTeacherName()}&nbsp;様&nbsp;&nbsp;&nbsp;</span>
                            <a  href="../logout/logoutaction"> ログアウト</a>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    </c:param>

	<c:param name="scripts"></c:param>
	<c:param name="content">
		<!-- 		<section class="mo-4"> -->
		<!-- <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2> -->

		<!-- test_list_subject.jsp から呼び出される場合は成績一覧（科目）と表示 -->
		<c:choose>
			<c:when
				test="${empty param.isFromTestSubject and empty requestScope.isFromTestStudent}">
				<h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">成績参照</h2>
			</c:when>
			<c:when test="${empty requestScope.isFromTestStudent}">
				<h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">成績一覧（科目）</h2>
			</c:when>
			<c:otherwise>
				<h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">成績一覧（学生）</h2>
			</c:otherwise>
		</c:choose>


		<div class="container mb-4 border rounded py-2 px-4">
			<form action="TestListSubjectAction" method="get">
				<div class="form-row align-items-center">
					<div class="col-auto pr-4">
						<label>科目情報</label>
					</div>
					<div class="form-group col-2 pr-2">
						<label class="form-label" for="entYear">入学年度</label> <select
							class="form-control" name="entYear" id="entYear">
							<option value="">--------</option>
							<c:forEach begin="2014" end="2025" var="year">
								<option value="${year}"
									<c:if test="${year == param.entYear}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group col-2 pr-2">
						<label class="form-label" for="classNo">クラス</label> <select
							class="form-control" name="classNo" id="classNo">
							<option value="">--------</option>
							<c:forEach var="cls" items="${clslist}">
								<option value="${cls}"
									<c:if test="${cls==param.classNo}">selected</c:if>>${cls}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-4">
						<label class="form-label" for="subcd">科目</label> <select
							class="form-control" name="subcd" id="subcd">
							<option value="">--------</option>
							<c:forEach var="sub" items="${sublist}">
								<option value=${sub.subCd
											}
									<c:if test="${sub.subCd==param.subcd}">selected</c:if>>${sub.subName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-auto px-4">
						<button type="submit" class="btn btn-secondary">検索</button>
					</div>
				</div>
			<c:if test="${not empty eMessage}">
				<p style="color: red;">${eMessage}</p>
			</c:if>
			</form>

			<hr>

			<form action="subject_score_search" method="get">
				<div class="form-row align-items-center">
					<div class="col-auto pr-4">
						<label>学生情報</label>
					</div>
					<div class="form-group col-4">
						<label for="student_no">学生番号</label> <input type="text"
							id="student_no" name="student_no" placeholder="学生番号を入力してください"
							required class="form-control" value="${param.student_no}">
							<small class="form-text text-muted">※７桁の半角数字</small>
					</div>
					<div class="form-group col-auto px-4">
						<button type="submit" class="btn btn-secondary">検索</button>
					</div>
				</div>
					<!-- エラーメッセージの表示 -->
					<c:if test="${not empty errorMessage}">
						<p style="color: red;">${errorMessage}</p>
					</c:if>
					<c:if test="${not empty stuMessage}">
						<p style="color: red;">${stuMessage}</p>
					</c:if>
			</form>
		</div>

		<div class="col">
			<c:if
				test="${empty param.isFromTestSubject and empty test.size() and empty errorMessage}">
				<p style="color: blue;">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
			</c:if>
		</div>
		<c:choose>
			<c:when test="${test.size()>0}">
				<div>検索結果:${test.size()}件</div>
				<p>氏名：${test[0].student_Name}（${test[0].student_No}）</p>
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
			<c:when
				test="${empty param.isFromTestSubject and test.size()==0 and empty errorMessage and empty stuMessage}">
				<p>氏名：${studentName}（${param.student_no}）</p>
				<div>成績情報が存在しませんでした</div>
			</c:when>
		</c:choose>


		</section>
	</c:param>
</c:import>
<c:if test="${empty param.isFromTestSubject}">
    <c:import url="/common/footer.jsp"/>
</c:if>