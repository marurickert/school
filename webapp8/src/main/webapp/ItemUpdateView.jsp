<%@page import="aiwa.entity.Item"%>
<%@page import="aiwa.entity.Category"%>
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
Item item = (Item) request.getAttribute("item");
	List<Category> categories = (List<Category>) request.getAttribute("categories");
	String word = (String) request.getAttribute("word");
	String categoryId = (String) request.getAttribute("categoryid");
	Category c = (Category) request.getAttribute("category");
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
<form action="ItemUpdateController" method="post">
<input value="<%= item.getItemId() %>" type="hidden" name="itemid">
<input value="<%= item.getItemName() %>" type="text" name="itemname" placeholder="商品名" required><br>
<input value="<%= item.getPrice() %>" type="number" name="price" placeholder="価格" required><br>
<textarea name="detail" placeholder="説明"><%= item.getDetail() %></textarea><br>
<input value="<%= item.getRating() %>" type="number" min="0" max="5" name="rating" placeholder="評価" required><br>
<input value="<%= item.getImage() %>" type="text" name="image" placeholder="画像" required><br>
<select name="categoryid" required>
<option value=""></option>
<% for(Category category : categories) {%>
<option <%= (category.getCategoryId() == item.getCategory().getCategoryId()) ? "selected" : ""%>  value="<%=category.getCategoryId() %>">
<%=category.getCategoryName() %>
</option>
<%} %>
</select>

<input type="submit" value="登録">
</form>

	<footer class="footer-section">
		<div class="container relative">

			<div class="sofa-img">
				<img src="images/onepiece.png" alt="Image" class="img-fluid">
			</div>

			<div class="row">
				<div class="col-lg-8">
					<div class="subscription-form">
						<h3 class="d-flex align-items-center">
							<span class="me-1"><img src="images/envelope-outline.svg" alt="Image" class="img-fluid"></span><span>Subscribe to Newsletter</span>
						</h3>

						<form action="#" class="row g-3">
							<div class="col-auto">
								<input type="text" class="form-control" placeholder="Enter your name">
							</div>
							<div class="col-auto">
								<input type="email" class="form-control" placeholder="Enter your email">
							</div>
							<div class="col-auto">
								<button class="btn btn-primary">
									<span class="fa fa-paper-plane"></span>
								</button>
							</div>
						</form>

					</div>
				</div>
			</div>

			<div class="row g-5 mb-5">
				<div class="col-lg-4">
					<div class="mb-4 footer-logo-wrap">

					</div>
				</div>
			</div>
				<div class="row g-5 mb-5">
					<div class="col-lg-4">
						<div class="mb-4 footer-logo-wrap"><a href="#" class="footer-logo">Manga Store<span>.</span></a></div>
						<p class="mb-4"></p>

						<ul class="list-unstyled custom-social">
							<li><a href="#"><span class="fa fa-brands fa-facebook-f"></span></a></li>
							<li><a href="#"><span class="fa fa-brands fa-twitter"></span></a></li>
							<li><a href="#"><span class="fa fa-brands fa-instagram"></span></a></li>
							<li><a href="#"><span class="fa fa-brands fa-linkedin"></span></a></li>
						</ul>
					</div>

					<div class="col-lg-8">
						<div class="row links-wrap">
							<div class="col-6 col-sm-6 col-md-3">
								<ul class="list-unstyled">
									<li><a href="#">About us</a></li>
									<li><a href="#">Services</a></li>
									<li><a href="#">Blog</a></li>
									<li><a href="#">Contact us</a></li>
								</ul>
							</div>

							<div class="col-6 col-sm-6 col-md-3">
								<ul class="list-unstyled">
									<li><a href="#">Support</a></li>
									<li><a href="#">Knowledge base</a></li>
									<li><a href="#">Live chat</a></li>
								</ul>
							</div>

							<div class="col-6 col-sm-6 col-md-3">
								<ul class="list-unstyled">
									<li><a href="#">Jobs</a></li>
									<li><a href="#">Our team</a></li>
									<li><a href="#">Leadership</a></li>
									<li><a href="#">Privacy Policy</a></li>
								</ul>
							</div>
						</div>
					</div>

				</div>
		</div>
	</footer>

</body>
</html>