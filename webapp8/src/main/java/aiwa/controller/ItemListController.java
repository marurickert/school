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

@WebServlet("/ItemListController")
public class ItemListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.PARAMETER
		String word = request.getParameter("keyword");
		if (word == null) {
			word = "";
		}

		String categoryid = request.getParameter("categoryid");
		if (categoryid == null) {
			categoryid = "0";
		}

		//2.MODEL
		ItemModel im = new ItemModel(getServletContext());
		List<Item> items = im.findByCondition(word, Integer.parseInt(categoryid));

		CategoryModel cm = new CategoryModel(getServletContext());
		List<Category> categories = cm.findAll();
		Category category = cm.findById(Integer.parseInt(categoryid));

		//3.VIEW
		request.setAttribute("items", items);
		request.setAttribute("word", word);
		request.setAttribute("categories", categories);
		request.setAttribute("categoryid", categoryid);
		request.setAttribute("category", category);
		request.getRequestDispatcher("/itemListView.jsp").forward(request, response);
	}

}
