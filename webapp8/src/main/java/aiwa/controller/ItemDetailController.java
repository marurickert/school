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

@WebServlet("/ItemDetailController")
public class ItemDetailController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//1Parameter
		String itemid = (String) request.getParameter("itemid");

		String categoryid = request.getParameter("categoryid");
		if (categoryid == null) {
			categoryid = "0";
		}

		//2.Model
		ItemModel im = new ItemModel(getServletContext());
		Item item = im.findById(Integer.parseInt(itemid));

		CategoryModel cm = new CategoryModel(getServletContext());
		List<Category> categories = cm.findAll();
		Category category = cm.findById(Integer.parseInt(categoryid));

		//3.View
		request.setAttribute("item", item);
		request.setAttribute("categories", categories);
		request.setAttribute("categoryid", categoryid);
		request.getRequestDispatcher("/itemDetailView.jsp").forward(request, response);
	}

}
