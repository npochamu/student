<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<%@page import="java.sql.*"%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        <h1 class="toptitle">得点管理システム</h1>
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学情報登録</h2>

            <!-- エラーメッセージの表示 -->
            <c:if test="${not empty errorMessage}">
                <p style="color:red;">${errorMessage}</p>
            </c:if>

            <form action="insert" method="post">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select name="year" required>
                        	<option value="">-------</option>
                            <c:forEach var="year" begin="1900" end="2030">
        						<option value="${year}" <c:if test="${param.year == year}">selected</c:if>>${year}</option>
    						</c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">学生番号</label>
                        <input type="text" name="Num" placeholder="学生番号を入力してください" required class="form-control" value="${param.Num}">
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">氏名</label>
                        <input type="text" name="name" placeholder="氏名を入力してください" required class="form-control" value="${param.name}">
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">性別</label>
                        <select name="gender" required>
                        	<option value="">---</option>
                            <option value="男" <c:if test="${param.gender == '男'}">selected</c:if>>男</option>
                            <option value="女" <c:if test="${param.gender == '女'}">selected</c:if>>女</option>

                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select name="class" id="student-f2-select">
                            <option value="101">101</option>
                            <option value="201">201</option>

                        </select>
                    </div>

                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">フリガナ</label>
                        <input type="text" name="name_huri" placeholder="フリガナを入力してください" required class="form-control" value="${param.name_huri}">
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">学校コード</label>
                        <input type="text" name="sc_cd" value="111" readonly placeholder="学校コードを入力してください" required class="form-control">
                    </div>
                    <div class="col-2 form-check text-center">
                        <label class="form-check-label" for="student-f3-check">在学中
                            <input type="checkbox" name="Zaigaku" value="1">
                        </label>
                    </div>

                    <div>
                        <input type="submit" value="登録して終了">
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>
