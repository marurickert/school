<%@page import="aiwa.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="aiwa.entity.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

List<Category> categories = (List<Category>)request.getAttribute("categories");
String word = (String) request.getAttribute("word");
if(word == null){
	word = "";
}

User user = (User) session.getAttribute("loginuser");

%>
<header>
	<h1 id="logo">
		こんにちは、<%=user!= null? "<a href='userEditVieww.jsp'>" + user.getUserName() + "</a>":"ゲスト" %>さん</h1>
		<div>
<%if(user!=null) {%>
<a href="LogoutController"><button>ログアウト</button></a>
<%}else {%>
<a href="login.jsp">ログイン</a>
<%} %>
<% if(user==null || user.getManager()==0) {%>
<a href="CartListController">カートを見る</a>
<%} %>
<%if(user!=null) {%>
<a href="OrderListController">購入履歴</a>
<%} %>
</div>
	<nav class="category-menu">
		<ul>
		<% for(Category category : categories) { %>
			<li><a href="ItemListController?keyword=<%= word %>&categoryid=<%= category.getCategoryId() %>"><span class="line1"><span><%= category.getCategoryName() %></span></span><span class="line2">Find It!</span></a></li>
		<% } %>
		</ul>
	</nav>
</header>