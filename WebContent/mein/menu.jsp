<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base.jsp">

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">メニュー</h2>



<div class="container text-center">
<div class="row">
    <div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
    	style="height: 10rem;background-color: #eaf6fd;">
    <a href="../students/all" class="h5" style="color:blue;">学生管理</a>
    </div>
    <div class="col d-flex  flex-column align-items-center justify-content-center mx-2 rounded shadow"
    	style="height: 10rem;background-color: #c8e2e6;">
    <p class="h5">成績管理</p>
    <a href="..students/test_insert" class="h5" style="color:blue;">成績登録</a>
    <a href="../students/TestListAction" class="h5" style="color:blue;">成績参照</a>
    </div>
    <div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
    	style="height: 10rem;background-color: #f2efbd;">
    <a href="../students/SubjectListAction" class="h5" style="color:blue;">科目管理</a>
	</div>
	</div>
</div>

</section>
</c:param>
</c:import>
<c:import url="/common/footer.jsp"/>
