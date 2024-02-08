package aiwa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import aiwa.entity.Category;

public class CategoryModel extends BaseModel {

	public CategoryModel(ServletContext context) {
		super(context);
	}

	public Category findById(int categoryId) {
		try {
			Connection conn = super.connect();

			String sql = "select * from categories where categoryid=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("categoryid"));
				category.setCategoryName(rs.getString("categoryname"));
				conn.close();
				return category;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Category> findAll() {
		List<Category> result = new ArrayList<>();

		try {
			Connection conn = super.connect();

			String sql = "select * from categories order by categoryid";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("categoryid"));
				category.setCategoryName(rs.getString("categoryname"));
				result.add(category);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
