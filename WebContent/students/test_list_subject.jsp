<%-- 成績参照JSP 検索画面と成績一覧（学生）をインクルードした　成績一覧（科目） --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<%
	request.setCharacterEncoding("UTF-8");
%>



<c:import url="TestListAction">
	<c:param name="isFromTestSubject" value="true" />
</c:import>
<!--     <h2>成績一覧（科目）</h2> -->

		<div class="container-fluid content"></div>
<div class="row m-0">
	<div class="col-2 sidebar">
	</div>
	<div class="col-10">
		<div class=" mx-3 mb-4 align-items-center">
			<c:choose>
				<c:when test="${not empty testList}">

					<p class="m-0">科目：${testList[0].subName}
						<span class="d-block text-right">
						並び替え: <a
							href="TestListSubjectAction?entYear=${param.entYear}&classNo=${param.classNo}&subcd=${param.subcd}&sort=studentNo" class="mx-1">学生番号順</a>
						<a
							href="TestListSubjectAction?entYear=${param.entYear}&classNo=${param.classNo}&subcd=${param.subcd}&sort=kana">フリガナ順</a>
					</span></p>


					<table class="table table-hover">
						<%--     <caption>科目：${testList[0].subName}</caption> --%>
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>フリガナ</th>
							<th>性別</th>
							<th>１回</th>
							<th>２回</th>
						</tr>
						<c:forEach var="test" items="${testList}">
							<tr>
								<td>${test.entYear}</td>
								<td>${test.classNo}</td>
								<td>${test.studentNo}</td>
								<td>${test.studentName}</td>
								<td>${test.kana}</td>
								<td>${test.seibetu}</td>
								<td>${test.test_1}</td>
								<td>${test.test_2}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<c:if test="${empty eMessage}">
						<p>学生情報が存在しませんでした</p>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<c:import url="/common/footer.jsp"/>