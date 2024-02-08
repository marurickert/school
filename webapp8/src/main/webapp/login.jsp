<%@page import="aiwa.entity.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Untree.co">
<link rel="shortcut icon" href="favicon.png">

<title>ログイン画面</title>
</head>
<body>
<form action="LoginController" method="post">
<input type="text" name="userid" placeholder="ユーザーID">
<input type="password" name="password" placeholder="パスワード">
<input type="submit" value="ログイン">
</form>
<a href="userEditView.jsp">ユーザー登録</a>
</body>
</html>