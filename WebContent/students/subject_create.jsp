<%-- 科目登録JSP --%>
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

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base.jsp">

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">科目情報登録</h2>

	<form action="SubjectCreateComplete" method="post">
		                    <div class="mb-3 col-6">
				<label class="form-label" for="subCd">科目コード</label>
				<!-- 半角英数字で3桁 -->
				<input type="text" id="subCd" name="subCd" class="form-control"
					value="${param.subCd!= null ? param.subCd : ''}"
					pattern="[a-zA-Z0-9]{3}" placeholder="科目コードを入力してください" required title="科目コードは3文字の半角英数字で入力してください" class="form-control">
<small class="form-text text-muted">※3文字の半角英数字</small>
			</div>
			<div class="mb-3 col-6">
				<label class="form-label" for="subName">科目名</label>
				<input type="text" id="subName" name="subName" class="form-control"
					value="${param.subName!= null ? param.subName : ''}"
					placeholder="科目名を入力してください"  required class="form-control">
			</div>

<!-- 科目コード重複のメッセージ （insert実行前にselect～where文バージョンでのチェック）-->
				<c:if test="${not empty insertErrorMsg}">
					<p style="color: red;">${insertErrorMsg}</p>
		 		</c:if>
<!-- 科目コード重複のメッセージ （insert文実行時の例外キャッチによるチェック）-->
				<c:if test="${not empty duplicateMsg}">
					<p style="color: red;">${duplicateMsg}</p>
		 		</c:if>


                    <div class="mb-3 col-6">
		<button type="submit" class="btn btn-secondary">登録</button>
		</div>
<!-- 		<input type="hidden" id="schoolCd" name="schoolCd" value="111"> -->

	</form>

	         <!-- 戻るリンク -->
     <div class="mb-3 col-6">
			<a href="SubjectListAction">戻る</a>
     </div>

        </section>
    </c:param>
</c:import>
<c:import url="/common/footer.jsp"/>