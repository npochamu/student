<%-- 共通部分JSP --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得点管理システム</title>
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/style.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Dela+Gothic+One&family=Noto+Serif+JP:wght@200..900&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">


<style>
.sidebar_fixed {
	position: sticky;
	top: 10px;
	height: 100%;
	margin-bottom: 60px;
	padding-top: 40px;
}

.sidebar_content {

}


</style>
</head>

<body>
<%
    if (session.getAttribute("teacher") == null) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
        dispatcher.forward(request, response);
        return;
    }
%>

    <c:param name="title">
            <header class="main-header">
        <nav class="navbar navbar-expand-lg ">
            <div class="container">
<!-- <img src="../img/title_img.png" class="img-fluid" alt="Responsive image"> -->
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

	${param.title}
	<!-- <div class="wrapper"> -->
	<div class="container-fluid content">
		<div class="row">
			<div class="col-2 p-0 sidebar">
<!-- 				<div class="sidebar_content"></div> -->
				<div class="sidebar_fixed">
<!-- 					固定するコンテンツ -->
					<ul class="nav nav-sidebar flex-column">
						<li><a href="../mein/menu.jsp">メニュー</a></li>
						<li><a href="../students/all">学生管理</a></li>
						<li><a style="color:gray; pointer-events: none; cursor: default;">成績管理</a></li>
						<li><a href="../students/test_insert">成績登録</a></li>
						<li><a href="../students/TestListAction">成績参照</a></li>
						<li><a href="../students/SubjectListAction">科目管理</a></li>

					</ul>
				</div>
			</div>
			<div class="col-10 p-0">
				<!-- メインコンテンツ -->
				${param.scripts} ${param.content}
			</div>
		</div>
	</div>
	<!-- </div> -->
	<script src="../js/bootstrap.bundle.min.js"></script>
</body>
</html>
