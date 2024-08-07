<%-- 科目削除JSP --%>
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.*"%>

<%-- 文字化けの対策 --%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<%-- subjectBeanから科目情報を取得する --%>
<%
	Subject subjectBean = (Subject) request.getAttribute("subjectBean");
%>
<style>

</style>
<c:import url="/common/base.jsp">

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="mo-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">科目情報削除</h2>
				<div class="mb-3 col-6">
				<form action="SubjectDeleteComplete" method="post">
					<%
						if (subjectBean.getSubName() == null) {
					%>
					「<%=(String) request.getParameter("subName")%>（<%=(String) request.getParameter("subCd")%>）」は既に削除されています
					<%
						} else {
					%>
					「<%=subjectBean.getSubName()%>（<%=subjectBean.getSubCd()%>）」を削除してもよろしいですか
				</div>
				<div class="mb-3 col-6">
				<button type="submit" class="btn btn-danger">削除</button>
				<%
					}
				%>
				</div>
				<input type="hidden" name="schoolCd"
				value="<%=subjectBean.getSchoolCd()%>"> <input type="hidden"
				name="subCd" value="<%=subjectBean.getSubCd()%>"> <input
				type="hidden" name="subName" value="<%=subjectBean.getSubName()%>">

				</form>

			<!-- 戻るリンク -->
			<div class="mb-3 col-6">
				<a href="SubjectListAction">戻る</a>
			</div>

		</section>
	</c:param>
</c:import>
<c:import url="/common/footer.jsp"/>