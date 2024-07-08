<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base.jsp">
    <c:param name="title">
            <header class="main-header">
        <nav class="navbar navbar-expand-lg ">
            <div class="container">
        <h1 class="toptitle">得点管理システム</h1>
                <div class="navbar-" id="navbarNav">
                    <ul class="navbar-nav">
                            <span>${teacher.getTeacherName()}&nbsp;様&nbsp;&nbsp;&nbsp;</span>
                            <a  href="../logout/logoutaction"> ログアウト</a>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">メニュー</h2>



<div class="container text-center">
<div class="row">
    <div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
    	style="height: 10rem;background-color: #eaf6fd;">
    <a href="../students/all">学生管理</a>
    </div>
    <div class="col d-flex  flex-column align-items-center justify-content-center mx-2 rounded shadow"
    	style="height: 10rem;background-color: #c8e2e6;">
    <p>成績管理</p>
    <a href="../students/test_insert">成績登録</a>
    <a href="../students/TestListAction">成績参照</a>
    </div>
    <div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
    	style="height: 10rem;background-color: #f2efbd;">
    <a href="../students/SubjectListAction">科目管理</a>
	</div>
	</div>
</div>

</section>
</c:param>
</c:import>
<c:import url="/common/footer.jsp"/>
