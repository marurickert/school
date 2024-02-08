package aiwa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aiwa.entity.Item;
import aiwa.entity.Order;
import aiwa.entity.User;
import aiwa.model.OrderModel;

@WebServlet("/OrderInsertController")
public class OrderInsertController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}

		//parameter

		//model
		OrderModel om = new OrderModel(getServletContext());

		for (Item item : cart) {
			Order order = new Order();
			order.setAmount(item.getCartNum());
			order.setItem(item);
			order.setUser(user);

			om.insert(null);
		}

		cart.clear();
		session.setAttribute("cart", cart);

		//view
		response.sendRedirect("OrderListController");

	}

}
