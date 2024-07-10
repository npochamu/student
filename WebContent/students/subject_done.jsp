<%-- 処理完了JSP --%>
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

<c:import url="/common/base.jsp">

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="mo-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">科目情報${param.view}</h2>
<!-- 			○○が完了しましたのメッセージ -->
<div class="my-2 text-end px-4">
			<div style="background-color: lightgreen; text-align: center;">
			<p>${param.viewMsg}</p>
			</div>
			</div>

			<!-- リンク -->
  <div class="container mt-5">
    <div>
<!--     科目情報登録完了の時だけ新規登録画面へ戻るリンクを表示 -->
					<c:if test="${param.view == '登録'}">
						<a href="SubjectCreateAction" class="mr-5">戻る</a>
					</c:if>
      <a href="<%="SubjectListAction"%>">科目一覧</a>
    </div>
  </div>
		</section>
	</c:param>
</c:import>
<c:import url="/common/footer.jsp"/>


