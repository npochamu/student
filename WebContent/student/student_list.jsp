<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 文字化けの対策 --%>
<% request.setCharacterEncoding("UTF-8"); %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        <h1 class="toptitle">得点管理システム</h1>
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mo-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>
            <div class="my-2 text-end px-4">
                <a href="student_in.jsp">新規登録</a>
            </div>
            <form action="search" method="post">

                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select name="year" required>
                        <option value="">----</option>
							<%for (int year = 2014; year <= 2034; year++) {%>
							<option value="<%=year %>" ><%=year %></option>
							<%}%>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select name="class" required>
			                <option value="">---</option>
			                <option value="101">101</option>
			                <option value="201">201</option>
                        </select>
                    </div>
                    <div class="col-2 form-check text-center">
                        <label class="form-check-label" for="student-f3-check">在学中
                            <input type="checkbox" name="Zaigaku" value="True">
                        </label>
                    </div>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button" >絞込み</button>
                    </div>
                </div>
            </form>
            <c:choose>
                <c:when test="${students.size()>0}">
                    <div>検索結果:${students.size()}件</div>
                    <table class="table table-hover">
                        <tr>
                            <th>入学年度</th>
                            <th>学生番号</th>
                            <th>学校コード</th>
                            <th>氏名</th>
                            <th>フリガナ</th>
                            <th>クラス</th>
                            <th>性別</th>
                            <th>在学中</th>
                            <th></th>
                            <th></th>
                        </tr>

                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.getEnt_Year() }</td>
                                <td>${student.getStudent_No() }</td>
                                <td>${student.getSchool_Cd() }</td>
                                <td>${student.getStudent_Name() }</td>
                                <td>${student.getStudent_Kana() }</td>
                                <td>${student.getClass_No() }</td>
                                <td>${student.getStu_Seibetu() }</td>

                                <td><c:choose>
                                      <c:when test="${student.getIs_Attend() }">
                                        〇
                                      </c:when>
                                      <c:otherwise>
                                        ×
                                      </c:otherwise>
                                    </c:choose>
                                </td>

                                <td><a href="student _update.jsp?Year=${student.getEnt_Year()}&Student_no=${student.getStudent_No()}&Student_name=${student.getStudent_Name()}&Student_kana=${student.getStudent_Kana()}&Class_No=${student.getClass_No()}&Gender=${student.getStu_Seibetu()}&Is_Attend=${student.getIs_Attend()}">変更</a></td>
                                <!-- <td><a href="delete?Number=${student.getStudent_No()}">削除</a></td> -->
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:when test="${all.size()>0}">
                	<div>検索結果:${all.size()}件</div>
                    <table class="table table-hover">
                        <tr>
                            <th>入学年度</th>
                            <th>学生番号</th>
                            <th>学校コード</th>
                            <th>氏名</th>
                            <th>フリガナ</th>
                            <th>クラス</th>
                            <th>性別</th>
                            <th>在学中</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="all" items="${all}">
                            <tr>
                                <td>${all.getEnt_Year() }</td>
                                <td>${all.getStudent_No() }</td>
                                <td>${all.getSchool_Cd() }</td>
                                <td>${all.getStudent_Name() }</td>
                                <td>${all.getStudent_Kana() }</td>
                                <td>${all.getClass_No() }</td>
                                <td>${all.getStu_Seibetu() }</td>
                                <td><c:choose>
                                      <c:when test="${all.getIs_Attend() }">
                                        〇
                                      </c:when>
                                      <c:otherwise>
                                        ×
                                      </c:otherwise>
                                    </c:choose>
                                </td>

                                <td><a href="student _update.jsp?Year=${all.getEnt_Year()}&Student_no=${all.getStudent_No()}&Student_name=${all.getStudent_Name()}&Student_kana=${all.getStudent_Kana()}&Class_No=${all.getClass_No()}&Gender=${all.getStu_Seibetu()}&Is_Attend=${all.getIs_Attend()}">変更</a></td>
                                <!--<td><a href="delete?Number=${all.getStudent_No()}">削除</a></td>-->
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <div>学生情報が存在しませんでした</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
    a
</c:import>