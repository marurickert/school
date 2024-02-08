<%@page import="aiwa.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="aiwa.entity.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート</title>
<%
List<Item> cart = (List<Item>) session.getAttribute("cart");
%>

</head>
<body>
<% if (cart==null || cart.size()==0) {%>
<h5>カートが空っぽです</h5>
<%} else { %>
<table border="1">
<tr><th>ID</th><th>商品名</th><th>価格</th><th>画像</th><th>数量</th><th>小計</th></tr>
<% int total = 0; %>
<%for (Item item:cart) {%>
<tr>
<td><%=item.getItemId() %></td>
<td><%=item.getItemName() %></td>
<td><%= StringUtil.toMoney(item.getPrice()) %></td>
<td><img src="<%=item.getImage()%>" style="height:100px"></td>
<td><%=item.getCartNum() %></td>
<td><%=StringUtil.toMoney(item.getSubTotal())%></td>
<td><a href="CartAddController?itemid=<%= item.getItemId() %>"><button>+</button></a>
<a href="CartRemoveController?itemid=<%= item.getItemId() %>"><button>-</button></a></td>
</tr>
<% total += item.getSubTotal(); %>
<%} %>
</table>
<div>
合計<%= StringUtil.toMoney(total) %>
</div>
<%} %>
<a href="ItemListController">買い物を続ける</a>
</body>
</html>