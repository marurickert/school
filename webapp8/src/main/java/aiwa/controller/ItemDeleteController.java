package aiwa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiwa.model.ItemModel;

@WebServlet("/ItemDeleteController")
public class ItemDeleteController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//1.Parameter
		String itemid = request.getParameter("itemid");

		//2.Model
		ItemModel im = new ItemModel(getServletContext());
		im.delete(Integer.parseInt(itemid));

		//3.View
		response.sendRedirect("ItemListController");

	}

}
