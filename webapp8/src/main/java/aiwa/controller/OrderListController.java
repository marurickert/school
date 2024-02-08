package aiwa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aiwa.entity.Order;
import aiwa.entity.User;
import aiwa.model.OrderModel;

@WebServlet("/OrderListController")
public class OrderListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		//parameter

		//model
		OrderModel om = new OrderModel(getServletContext());
		List<Order> orders = om.findByUserId(user);

		//view
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/orderListView.jsp").forward(request, response);

	}

}
