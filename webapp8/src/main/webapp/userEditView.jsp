<%@page import="aiwa.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://ajaxzip3.github.io/ajaxzip3.js"></script>
<title>ユーザー登録</title>
<%
	String msg = (String) request.getAttribute("msg");
	if(msg == null){
		msg = "";
	}
	
	String mode="insert";
	
	User user = (User) request.getAttribute("user");
	
	User loginuser = (User) session.getAttribute("loginuser");
	if(loginuser != null) {
		mode="update";
		user = loginuser;
	}
%>
</head>
<body>
	<h5><%= msg %></h5>
	<form action="UserEditController" method="post">
	<input type="hidden" name="mode" value="<%= mode %>">
	<%if(user.equals("update")) { %>
	<input type="hidden" name="userId" value="<%=user.getUserId() %>">
	<%} %>
		<input <%=mode.equals("update") ? "disabled" : "" %> class="<%= !msg.equals("") ? "err" : "" %>" type="email" name="userid" placeholder="ユーザーID" required value="<%= user != null ? user.getUserId() : "" %>"><br>
		<input type="text" name="username" placeholder="ユーザー名" required value="<%= user != null ? user.getUserName() : "" %>"><br>
		<input type="password" name="password" placeholder="パスワード" required value="<%= user != null ? user.getPassword() : "" %>"><br>
		<input type="date" name="birthday" placeholder="誕生日" value="<%= user != null ? user.getBirthday() : "" %>"><br>
		<input type="text" name="zipcode" placeholder="郵便番号" 
			onKeyUp="AjaxZip3.zip2addr( this, '', 'address', 'address');" value="<%= user != null ? user.getZipCode() : "" %>"><br>
		<input type="text" name="address" placeholder="住所" value="<%= user != null ? user.getAddress() : "" %>"><br>
		<input type="submit" value="<%=mode.equals("insert") ? "登録" : "修正" %>">
	</form>
</body>
</html>