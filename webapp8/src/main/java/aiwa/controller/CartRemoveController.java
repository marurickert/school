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

/**
 * Servlet implementation class CartRemoveController
 */
@WebServlet("/CartRemoveController")
public class CartRemoveController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//session
		HttpSession session = request.getSession();

		//parameter
		String itemId = request.getParameter("itemid");

		//model
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}

		for (Item item : cart) {
			if (item.getItemId() == Integer.parseInt(itemId)) {
				item.setCartNum(item.getCartNum() - 1);
				if (item.getCartNum() == 0) {
					cart.remove(item);
					break;
				}
			}
		}

		//view
		response.sendRedirect("CartListController");
	}

}
