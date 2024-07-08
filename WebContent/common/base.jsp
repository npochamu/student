<%-- 共通部分JSP --%>
<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>得点管理システム</title>
	<link rel="stylesheet" href="../css/bootstrap.min.css" />
	<link rel="stylesheet" href="../css/style.css">
	<style>

	</style>
</head>

<body>

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

${param.title}
<div class="wrapper">
	<div class="container-fluid content">
		<div class="row">
			<div class="col-2 p-0 sidebar">
				<!-- 固定するコンテンツ -->
				<ul class="nav nav-sidebar flex-column">
					<li><a href="../mein/menu.jsp">メニュー</a></li>
					<li><a href="../students/all">学生管理</a></li>
					<li><a href="../students/test_insert">成績登録</a></li>
					<li><a href="../students/TestListAction">成績参照</a></li>
					<li><a href="../students/SubjectListAction">科目管理</a></li>

				</ul>
			</div>
			<div class="col-10 p-0">
				<!-- メインコンテンツ -->
				${param.scripts}
				${param.content}
			</div>
		</div>
	</div>
</div>
<script src="../js/bootstrap.bundle.min.js"></script>
</body>
</html>
