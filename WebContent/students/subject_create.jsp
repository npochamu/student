<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        <h1 class="toptitle">得点管理システム</h1>
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2>科目情報登録</h2>

	<form action="SubjectCreateComplete" method="post">
		<table class="form-table">
		                    <div class="mb-3">
			<tr>
				<td><label class="form-label" for="subCd">科目コード</label></td>
				<!-- 半角英数字で3桁 -->
				<td><input type="text" id="subCd" name="subCd" class="form-input"
					value="${param.subCd!= null ? param.subCd : ''}"
					pattern="[a-zA-Z0-9]{3}" placeholder="科目コードを入力してください" required title="科目コードは3文字の半角英数字で入力してください" class="form-control">
					</td>
			</tr>
			<tr>
			</div>
			<div class="mb-3">
				<td><label class="form-label" for="subName">科目名</label></td>
				<td><input type="text" id="subName" name="subName" class="form-input"
					value="${param.subName!= null ? param.subName : ''}"
					placeholder="科目名を入力してください"  required class="form-control"></td>
			</tr>
			</div>
		</table>

<!-- 科目コード重複のメッセージ （insert実行前にselect～where文バージョンでのチェック）-->
				<c:if test="${not empty insertErrorMsg}">
					<p style="color: red;">${insertErrorMsg}</p>
		 		</c:if>
<!-- 科目コード重複のメッセージ （insert文実行時の例外キャッチによるチェック）-->
				<c:if test="${not empty duplicateMsg}">
					<p style="color: red;">${duplicateMsg}</p>
		 		</c:if>


                    <div class="mb-3 text-center">
		<button type="submit" class="btn btn-secondary">登録</button>
<!-- 		<input type="hidden" id="schoolCd" name="schoolCd" value="111"> -->

	</form>

	         <!-- 戻るリンク -->
     <div>
			<a href="SubjectListAction">戻る</a>
     </div>

        </section>
    </c:param>
</c:import>
