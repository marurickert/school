package aiwa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiwa.entity.User;
import aiwa.model.UserModel;

@WebServlet("/UserEditController")
public class UserEditController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//1.PARAMETER
		request.setCharacterEncoding("UTF-8");

		String mode = request.getParameter("mode");

		String userId = request.getParameter("userid");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String birthday = request.getParameter("birthday");
		String zipCode = request.getParameter("zipcode");
		String address = request.getParameter("address");

		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setBirthday(birthday);
		user.setZipCode(zipCode);
		user.setAddress(address);
		user.setManager(0);

		//2.MODEL
		UserModel um = new UserModel(getServletContext());

		if (mode.equals("insert")) {
			if (um.findById(userId) != null) {
				request.setAttribute("msg", "そのユーザーIDはすでに登録されています");
				request.setAttribute("user", user);
				request.getRequestDispatcher("/userEditView.jsp").forward(request, response);
				return;
			}

			um.insert(user);
		} else {
			um.update(user);
			response.sendRedirect("ItemListController");
		}

		//3.VIEW
		response.sendRedirect("login.jsp");
	}

}
