package aiwa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import aiwa.entity.Category;
import aiwa.entity.Item;
import aiwa.entity.Order;
import aiwa.entity.User;

public class OrderModel extends BaseModel {

	public OrderModel(ServletContext context) {
		super(context);
	}

	public List<Order> findByUserId(User u) {
		List<Order> result = new ArrayList<>();

		try (Connection conn = super.connect()) {

			String sql = "select * from orders o inner join items i on o.itemid = i.itemid "
					+ "inner join categories c on i.categoryid = c.categoryid "
					+ "inner join users u on o.userid=u.userid ";

			if (u.getManager() == 0) {
				sql += "where userid=? ";
			}
			sql += "order by orderdate desc, orderid";

			PreparedStatement stmt = conn.prepareStatement(sql);

			if (u.getManager() == 0) {
				stmt.setString(1, u.getUserId());
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("orderid"));
				order.setOrderDate(rs.getString("orderdate"));
				order.setAmount(rs.getInt("amount"));

				Item item = new Item();
				item.setItemId(rs.getInt("itemid"));
				item.setItemName(rs.getString("itemname"));
				item.setPrice(rs.getInt("price"));
				item.setDetail(rs.getString("detail"));
				item.setRating(rs.getInt("rating"));
				item.setImage(rs.getString("image"));
				item.setImage1(rs.getString("image1"));
				item.setImage2(rs.getString("image2"));

				Category category = new Category();
				category.setCategoryId(rs.getInt("categoryid"));
				category.setCategoryName(rs.getString("categoryname"));

				item.setCategory(category);

				User user = new User();
				user.setUserId(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setBirthday(rs.getString("birthday"));
				user.setManager(rs.getInt("manager"));
				user.setZipCode(rs.getString("zipcode"));
				user.setAddress(rs.getString("address"));

				order.setItem(item);
				order.setUser(user);

				result.add(order);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void insert(Order order) {
		try (Connection conn = super.connect()) {

			String sql = "insert into orders (orderdate, amount, itemid, userid) values (datetime('now', 'localtime'), ?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, order.getAmount());
			stmt.setInt(2, order.getItem().getItemId());
			stmt.setString(3, order.getUser().getUserId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
