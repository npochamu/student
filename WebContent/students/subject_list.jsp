<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
            <div class="my-2 text-end px-4">
                <a href="StudentCreate.action">新規登録</a>
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
                        <c:forEach var="subject" items="${subjects}">
                            <tr>
                                <td>${subject.sub_cd}</td>
                                <td>${subject.sub_name}</td>
                                <td><a href="StudentUpdate.action?no=${subject.no}">変更</a></td>
                                <td><a href="StudentDelete.action?no=${subject.no}">削除</a></td>
                            </tr>
                        </c:forEach>

                </tbody>
            </table>
        </section>
    </c:param>
</c:import>
