<%-- 学生一覧JSP --%>
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
            <h2>科目情報変更</h2>
            <form action="update" method="post">
                <div class="d-flex flex-column border mx-3 mb-3 py-2 px-4 align-items-start rounded" id="filter">
                    <div class="mb-3">
                        <label class="form-label" for="sub_code">科目コード</label>
                        <input type="hidden" id="sub_code" name="sub_code" value="${code}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="sub_name">科目名</label>
                        <input type="text" id="sub_name" name="sub_name" placeholder="" value = "${name}" required class="form-control">
                    </div>


                    <div class="mb-3 text-center">
                        <button class="btn btn-secondary" id="filter-button" type="submit">変更</button>
                    </div>
                    <div class="mt-2 text-warning">${errors.get("f1")}</div>
                </div>
            </form>

            <!-- 戻るリンク -->
            <div>
                <a href="previousPage.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>
