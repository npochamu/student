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

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Dela+Gothic+One&family=Noto+Serif+JP:wght@200..900&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">

</head>

<body>
            <header class="main-header">
        <nav class="navbar navbar-expand-lg ">
            <div class="container">
        <h1 class="toptitle zen-maru-gothic-regular">得点管理システム</h1>
        </div></nav></header>
<%-- ${param.title} --%>
${param.scripts}
${param.content}
</body>
</html>