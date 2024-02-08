<%@page import="aiwa.entity.Category"%>
<%@page import="aiwa.entity.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Untree.co">
<link rel="shortcut icon" href="favicon.png">

<meta name="description" content="" />
<meta name="keywords" content="bootstrap, bootstrap4" />

<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<link href="css/tiny-slider.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>Manga Store</title>

<%
	List<Item> items = (List<Item>) request.getAttribute("items");
	List<Category> categories = (List<Category>) request.getAttribute("categories");
	String word = (String) request.getAttribute("word");
	String categoryId = (String) request.getAttribute("categoryid");
	Category c = (Category) request.getAttribute("category");
	String userid = (String) session.getAttribute("loginuser");
	String p = (String) request.getParameter("p");
%>

</head>
<body>

	<!-- Start Header/Navigation -->
	<nav class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark" arial-label="Furni navigation bar">

		<div class="container">
			<a class="navbar-brand" href="index.html">Manga Store<span>.</span></a>

			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarsFurni">
				<ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
				<% for (Category category : categories) { %>
					<li class="nav-item "><a  class="nav-link" href="ItemListController?keyword=<%=word %>&categoryid=<%= category.getCategoryId() %>"><%= category.getCategoryName() %></a></li>
					<% } %>
				</ul>

				<ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
					<li><a class="nav-link" href="#"><img src="images/user.svg"></a></li>
					<li><a class="nav-link" href="cart.html"><img src="images/cart.svg"></a></li>
				</ul>
			</div>
		</div>

	</nav>
	<!-- End Header/Navigation -->

	<!-- Start Hero Section -->
	<div class="hero">
		<div class="container">
			<div class="row justify-content-between">
				<div class="col-lg-5">
					<div class="intro-excerpt">
						<h2><span><%= (c== null) ? "すべて" : c.getCategoryName() %></span></h2>
						<form action="ItemListController">
						<input type="hidden" name="categoryid" value="<%=categoryId %>">
						<input type="text" name="keyword" placeholder="キーワード" value="<%= word%>">
						<button type="submit">検索</button>
						</form>
					</div>
					<a href="ItemInsertController">新規登録</a>
				</div>
				<div class="col-lg-7"></div>
			</div>
		</div>
	</div>

</body>
</html>