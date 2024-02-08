<%@page import="aiwa.entity.User"%>
<%@page import="aiwa.entity.Order"%>
<%@page import="aiwa.util.StringUtil"%>
<%@page import="aiwa.entity.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>購入履歴</title>
<%
List<Order> orders = (List<Order>) session.getAttribute("orders");
User user = (User) session.getAttribute("loginuser");
%>

</head>
<body>
<% if (orders == null || orders.size()==0) {%>
<h5>購入履歴がありません</h5>
<%} else { %>
<table border="1">
<tr><th>購入日</th>
<%if(user !=null && user.getManager()==1) {%>
<th>購入者</th>
<%} %>
<th>価格</th><th>画像</th><th>数量</th><th>小計</th></tr>
<% int total = 0; %>
<%for (Order order : orders) {%>
<tr>
<td><%=StringUtil.toDate(order.getOrderDate()) %></td>
<td><%=order.getUser().getUserName() %></td>
<td><%= StringUtil.toMoney(order.getItem().getPrice()) %></td>
<td><img src="<%=order.getItem().getImage()%>" style="height:100px"></td>
<td><%=order.getAmount() %></td>
<td><%=StringUtil.toMoney(order.getItem().getPrice())%></td>
<td><%=StringUtil.toMoney(order.getItem().getPrice() * order.getAmount()) %></td>
</tr>
<%} %>
</table>
<%} %>
<div>
<a href="ItemListController">買い物を続ける</a>
</div>
</body>
</html>