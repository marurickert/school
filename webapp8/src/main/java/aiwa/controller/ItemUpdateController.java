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

@WebServlet("/ItemUpdateController")
public class ItemUpdateController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.parameter
		String itemId = request.getParameter("itemid");

		//2.Model
		ItemModel im = new ItemModel(getServletContext());
		Item item = im.findById(Integer.parseInt(itemId));

		CategoryModel cm = new CategoryModel(getServletContext());
		List<Category> categories = cm.findAll();

		//3.View
		request.setAttribute("item", item);
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/ItemUpdateView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		//1.parammeter
		String itemId = (String) request.getParameter("itemid");
		String itemName = (String) request.getParameter("itemname");
		String price = (String) request.getParameter("price");
		String detail = (String) request.getParameter("detail");
		String rating = (String) request.getParameter("rating");
		String image = (String) request.getParameter("image");
		String categoryid = (String) request.getParameter("categoryid");

		//2.Model
		ItemModel im = new ItemModel(getServletContext());
		Item item = new Item();
		item.setItemId(Integer.parseInt(itemId));
		item.setItemName(itemName);
		item.setPrice(Integer.parseInt(price));
		item.setDetail(detail);
		item.setRating(Integer.parseInt(rating));
		item.setImage(image);
		Category category = new Category();
		category.setCategoryId(Integer.parseInt(categoryid));
		item.setCategory(category);

		im.update(item);
		//3.view
		response.sendRedirect("ItemListController?itemid=" + itemId);

	}

}
