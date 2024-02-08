package aiwa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiwa.entity.Category;
import aiwa.entity.Item;
import aiwa.model.CategoryModel;
import aiwa.model.ItemModel;

@WebServlet("/ItemInsertController")
public class ItemInsertController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.Parameter

		//2.Model
		CategoryModel cm = new CategoryModel(getServletContext());
		List<Category> categories = cm.findAll();

		//3.View
		request.setAttribute("categories", categories);

		request.getRequestDispatcher("/ItemInsertView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		//1 Parameter
		String itemName = (String) request.getParameter("itemname");
		String price = (String) request.getParameter("price");
		String detail = (String) request.getParameter("detail");
		String rating = (String) request.getParameter("rating");
		String image = (String) request.getParameter("image");
		String categoryid = (String) request.getParameter("categoryid");

		//2.Model
		ItemModel im = new ItemModel(getServletContext());
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(Integer.parseInt(price));
		item.setDetail(detail);
		item.setRating(Integer.parseInt(rating));
		item.setImage(image);
		Category category = new Category();
		category.setCategoryId(Integer.parseInt(categoryid));
		item.setCategory(category);

		im.insert(item);

		//3.View
		response.sendRedirect("ItemListController");

	}

}
