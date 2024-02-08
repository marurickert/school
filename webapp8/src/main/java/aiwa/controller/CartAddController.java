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
import aiwa.model.ItemModel;

@WebServlet("/CartAddController")
public class CartAddController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//login check
		HttpSession session = request.getSession();
		//if (session.getAttribute("loginuser") == null) {
		//	response.sendRedirect("login.jsp");
		//	return;
		//}

		//parameter
		String itemId = request.getParameter("itemid");

		//model
		ItemModel im = new ItemModel(getServletContext());
		Item item = im.findById(Integer.parseInt(itemId));

		List<Item> cart = (List<Item>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}

		boolean hit = false;
		for (Item i : cart) {
			if (i.getItemId() == Integer.parseInt(itemId)) {
				i.setCartNum(i.getCartNum() + 1);
				hit = true;
			}
		}

		if (!hit) {
			cart.add(item);
		}

		session.setAttribute("cart", cart);

		//view
		response.sendRedirect("CartListController");

	}

}
