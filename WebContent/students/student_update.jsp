<%-- 学生一覧JSP --%>
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
<% request.setCharacterEncoding("UTF-8"); %>
<style>

</style>
<c:import url="/common/base.jsp">

    <c:param name="scripts"></c:param>

    <c:param name="content">
<section class="mo-4">
<h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">学生情報変更</h2>
<form action ="updateinsert" method="post">
<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

<div class="col-3">
<label class="form-label" for="ent_year">入学年度</label>
<br><span id="ent_year"><%String Year=request.getParameter("Year"); %><%=Year %></span>
<input type="hidden" id="ent_year" name="ent_year" value=<%=Year %>>
</div>

<div class="col-3">
<label class="form-label" for="classnum">クラス</label>
<%String Class_No=request.getParameter("Class_No"); %>
<%request.setAttribute("Class_No", Class_No); %>
<select id="classnum" name="classnum" class="form-control">
<c:forEach var="list2"  items="${classall }" >
<option value="${list2.getClassNo() }" <c:if test="${Class_No == list2.getClassNo()}">selected</c:if>>${list2.getClassNo() }</option>
</c:forEach>
</select>
</div>

<div class="col-3">
<label class="form-label" for="name">氏名</label>
<input type="text" id="name" name="name" placeholder="氏名を入力してください" required class="form-control" value="<%String Student_name=request.getParameter("Student_name"); %><%=Student_name %>">
</div>

<div class="col-3 form-check">
<%String Is_Attend=request.getParameter("Is_Attend"); %>
<%request.setAttribute("Is_Attend", Is_Attend); %>
<input class="form-check-input" type="checkbox" id="isattend" name="isattend" value="true" <c:if test="${Is_Attend}">checked</c:if> />
<label class="form-check-label ms-2" for="isattend" >在学中</label>
</div>

<div class="col-3">
<label class="form-label" for="student_id">学生番号</label>
<br><span id="student_id_display"><%String Student_no=request.getParameter("Student_no"); %><%=Student_no %></span>
<input type="hidden" id="student_id" name="student_id" value="<%=Student_no %>">
</div>

<div class="col-3">
<label class="form-label" for="gender">性別</label>
<%String Gender=request.getParameter("Gender"); %>
<%request.setAttribute("Gender", Gender); %>
<select id="gender" name="gender" class="form-control">

<option value="男" <c:if test="${Gender == '男'}">selected</c:if>>男</option>
<option value="女" <c:if test="${Gender == '女'}">selected</c:if>>女</option>
</select>
</div>

<div class="col-3">
<label class="form-label" for="name_kana">フリガナ</label>
<input type="text" id="name_kana" name="name_kana" placeholder="フリガナを入力してください" pattern="^[ァ-ヶー]+$" required title="フリガナはカタカナで入力してください" class="form-control" value="<%String Student_kana=request.getParameter("Student_kana"); %><%=Student_kana %>">
</div>

<div class="col-2 form-check text-center">
<button type="submit" class="btn btn-secondary" id="filter-button">変更</button>
</div>

<div class="col-2 form-check text-center">${errors.get("f1")}</div>
</div>
</form>

            <!-- 戻るリンク -->
<div class="m-3">
<a href="../students/all">戻る</a>
</div>
</section>
</c:param>
</c:import>
<c:import url="/common/footer.jsp"/>