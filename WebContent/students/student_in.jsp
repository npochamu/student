<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<%-- 文字化けの対策 --%>
<%@page import="java.sql.*"%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base.jsp">

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary text-white py-2 px-4">学生情報登録</h2>

            <!-- エラーメッセージの表示 -->
            <c:if test="${not empty errorMessage}">
                <p class="ml-3" style="color:red;">${errorMessage}</p>
            </c:if>

            <form action="insert" method="post">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-4">
						<label class="form-label" for="entYear">入学年度</label>
                        <select name="year" class="form-control"  id="entYear" required>
                        	<option value="">-------</option>
                            <c:forEach var="year" begin="2014" end="2034">
        						<option value="${year}" <c:if test="${param.year == year}">selected</c:if>>${year}</option>
    						</c:forEach>
                        </select>
                    </div>

                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">学生番号</label>
                        <input type="text" name="Num" placeholder="学生番号を入力してください" id="student-f1-select" required class="form-control" value="${param.Num}">
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f1-name">氏名</label>
                        <input type="text" name="name" placeholder="氏名を入力してください" id="student-f1-name" required class="form-control" value="${param.name}">
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-gender">性別</label>
                        <select name="gender" class="form-control" id="student-f2-gender" required>
                        	<option value="">---</option>
                            <option value="男" <c:if test="${param.gender == '男'}">selected</c:if>>男</option>
                            <option value="女" <c:if test="${param.gender == '女'}">selected</c:if>>女</option>

                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-class">クラス</label>

                        <select name="classno" class="form-control" id="student-f2-class" required>
                        	<option value="">---</option>
                            <c:forEach var="list2"  items="${classall }" >
								<option value="${list2.getClassNo() }" <c:if test="${param.classno == list2.getClassNo()}">selected</c:if>>${list2.getClassNo() }</option>
							</c:forEach>
			            </select>
                    </div>

                    <div class="col-4">
                        <label class="form-label" for="student-f1-huri">フリガナ</label>
                        <input type="text" name="name_huri" placeholder="フリガナを入力してください" id="student-f1-huri" required class="form-control" value="${param.name_huri}">
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f1-selectcode">学校コード</label>
                        <input type="text" name="sc_cd" value="111" id="student-f1-selectcode" readonly placeholder="学校コードを入力してください" required class="form-control">
                    </div>
                    <div class="col-2 form-check text-center">
                        <label class="form-check-label" for="student-f3-check">在学中
                            <input type="checkbox" name="Zaigaku" value="true" id="student-f3-check" <c:if test="${param.Zaigaku == 'true'}">checked</c:if>>
                        </label>
                    </div>

                    <div>
                        <button type="submit" class="btn btn-secondary">登録して終了</button>
                    </div>
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


