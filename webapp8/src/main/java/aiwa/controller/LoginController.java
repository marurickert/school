package aiwa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aiwa.entity.User;
import aiwa.model.UserModel;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		//parameter
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");

		//model
		UserModel um = new UserModel(getServletContext());
		User user = um.findByIdAndPassword(userId, password);

		//view
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginuser", user);
			response.sendRedirect("ItemListController");

		} else {
			request.setAttribute("message", "ログインIDまたパスワードが違います");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
