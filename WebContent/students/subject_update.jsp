<%-- 科目変更JSP --%>
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

<%@page import="bean.Subject"%>


<%-- subjectBeanから科目情報を取得する --%>
<%
	Subject subjectBean = (Subject) request.getAttribute("subjectBean");
%>

<c:import url="/common/base.jsp">

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="mo-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">科目情報変更</h2>

			<form action="SubjectUpdateComplete" method="post">

<!-- 						<td><label for="schoolCd">学校コード:</label></td> -->
<%-- 						<td><%=subjectBean.getSchoolCd()%></td> --%>
<div class="form-group mb-3 col-6">
						<llabel class="form-label" for="subCd">科目コード</label>
						<input class="form-control" type="text" id="subCd"
						value=${param.subCd!= null ? param.subCd : ''} readonly>
						<%-- 				${subjectBean.subCd} --%>
</div>

				<!-- メッセージ表示：科目更新対象が削除済だった場合-->
				<c:if test="${subjectBean.getSubCd()==null}">
					<p class="ml-3" style="color: red;">科目が存在していません</p>
				</c:if>

<div class="form-group mb-3 col-6" >
						<label class="form-label" for="subName">科目名</label>
						<input type="text" id="subName" name="subName"
							class="form-control" placeholder="科目名を入力してください"
							value="${param.subName!= null ? param.subName : ''}" required>
</div>
				<!-- メッセージ表示：リクエストした科目名と、変更クリック後にサーブレットで取得し直した科目名が異なる場合-->
				<c:if
					test="${subjectBean.subName != null && param.subName != subjectBean.subName}">
					<p class="ml-3" style="color: red;">科目名は現在「${subjectBean.subName}」に変更されています</p>
				</c:if>

<div class="mb-3 col-3">
				<button type="submit" class="btn btn-secondary">変更</button>
</div>
				<input type="hidden" name="schoolCd" value="${subjectBean.schoolCd}">
				<input type="hidden" name="subCd" value="${subjectBean.subCd}">
				<input type="hidden" name="subName" value="${subjectBean.subName}">

			</form>

			<!-- 戻るリンク -->
			<div class="col-3">
				<a href="SubjectListAction">戻る</a>
			</div>

		</section>
	</c:param>
</c:import>
<c:import url="/common/footer.jsp"/>