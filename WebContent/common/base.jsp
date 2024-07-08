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
