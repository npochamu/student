<%-- 処理完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<c:import url="/common/base.jsp">

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="mo-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">科目情報${param.view}</h2>
			<div style="background-color: yellow; text-align: center;">
			<p>${param.viewMsg}</p>
			</div>

			<!-- 戻るリンク -->
  <div class="container mt-5">
    <div>
      <a href="SubjectListAction" class="mr-5">戻る</a>
      <a href="<%="SubjectListAction"%>">科目一覧</a>
    </div>
  </div>
		</section>
	</c:param>
</c:import>
<c:import url="/common/footer.jsp"/>


