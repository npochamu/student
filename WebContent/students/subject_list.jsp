<%-- 科目一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

<%@page import="java.util.ArrayList, java.net.URLEncoder"%>
<%@page import="bean.Subject"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        <h1 class="toptitle">得点管理システム</h1>
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">科目管理</h2>
            <div class="my-2 text-end px-4">
            	<!-- 「科目を登録する」リンク -->
                <a href="SubjectCreateAction">新規登録</a>
            </div>

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>科目コード</th>
                        <th>科目名</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>

     <!-- 		リクエストパラメーターに全角文字をセットしたくてURLEncoder.encode(,)を使うため、スクリプト式、スクリプトレットで対応 -->
		<%ArrayList<Subject> subjectList = (ArrayList<Subject>) request.getAttribute("subjectList");%>
            <%for (Subject subjectBean : subjectList) {%>
            <tr>
    <%-- 			<td><%=subjectBean.getSchoolCd()%></td> --%>
                <td><%=subjectBean.getSubCd()%></td>
                <td><%=subjectBean.getSubName()%></td>
                <td><a href="SubjectUpdateAction?subCd=<%=URLEncoder.encode(subjectBean.getSubCd(), "utf-8") %>&subName=<%=URLEncoder.encode(subjectBean.getSubName(), "utf-8") %>">変更</a></td>
                <td><a href="SubjectDeleteAction?subCd=<%=URLEncoder.encode(subjectBean.getSubCd(), "utf-8") %>&subName=<%=URLEncoder.encode(subjectBean.getSubName(), "utf-8") %>">削除</a></td>
            </tr>
            <%}%>

                </tbody>
            </table>
        </section>
    </c:param>
</c:import>
