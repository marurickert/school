package aiwa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import aiwa.entity.User;

public class UserModel extends BaseModel {

	public UserModel(ServletContext context) {
		super(context);
	}

	public User findById(String userId) {

		//try with resource
		try (Connection conn = super.connect()) {
			String sql = "select * from users where userid = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setBirthday(rs.getString("birthday"));
				user.setManager(rs.getInt("manager"));
				user.setZipCode(rs.getString("zipcode"));
				user.setAddress(rs.getString("address"));
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User findByIdAndPassword(String userId, String password) {

		//try with resource
		try (Connection conn = super.connect()) {
			String sql = "select * from users where userid = ? and password = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setBirthday(rs.getString("birthday"));
				user.setManager(rs.getInt("manager"));
				user.setZipCode(rs.getString("zipcode"));
				user.setAddress(rs.getString("address"));
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insert(User user) {
		try (Connection conn = super.connect()) {

			String sql = "insert into users values(?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUserId());
			stmt.setString(2, user.getUserName());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getBirthday());
			stmt.setInt(5, user.getManager());
			stmt.setString(6, user.getZipCode());
			stmt.setString(7, user.getAddress());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(User user) {
		try (Connection conn = super.connect()) {

			String sql = "update users set "
					+ "username = ?, "
					+ "password = ?, "
					+ "birthday = ?, "
					+ "manager = ?, "
					+ "zipcode = ? , "
					+ "address = ? "
					+ "where userid = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getBirthday());
			stmt.setInt(4, user.getManager());
			stmt.setString(5, user.getZipCode());
			stmt.setString(6, user.getAddress());
			stmt.setString(7, user.getUserId());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
