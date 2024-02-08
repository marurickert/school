package aiwa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import aiwa.entity.Category;
import aiwa.entity.Item;

public class ItemModel extends BaseModel {
	public ItemModel(ServletContext context) {
		super(context);
	}

	public void update(Item item) {
		try {
			Connection conn = super.connect();
			String sql = "update items set itemname=?, price=?, detail=?, rating=?, image=?, categoryid=? where itemid=? ";

			PreparedStatement stmt = conn.prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, item.getItemName());
			stmt.setInt(index++, item.getPrice());
			stmt.setString(index++, item.getDetail());
			stmt.setDouble(index++, item.getRating());
			stmt.setString(index++, item.getImage());
			stmt.setInt(index++, item.getCategory().getCategoryId());
			stmt.setInt(index++, item.getItemId());

			stmt.executeUpdate();

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(int itemid) {
		try {
			Connection conn = super.connect();
			String sql = "delete from items where itemid=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemid);
			stmt.executeUpdate();

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void insert(Item item) {
		try {
			Connection conn = super.connect();
			String sql = "insert into "
					+ "items (itemname, price, detail, rating, image, categoryid)"
					+ " values (?, ?, ?, ?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, item.getItemName());
			stmt.setInt(index++, item.getPrice());
			stmt.setString(index++, item.getDetail());
			stmt.setDouble(index++, item.getRating());
			stmt.setString(index++, item.getImage());
			stmt.setInt(index++, item.getCategory().getCategoryId());

			stmt.executeUpdate();

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Item findById(int itemId) {
		try {

			Connection conn = super.connect();
			String sql = "select "
					+ "	* "
					+ "from "
					+ "	items i "
					+ "inner join "
					+ "	categories c "
					+ "on "
					+ "	i.categoryid = c.categoryid "
					+ "where "
					+ "	itemid=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, itemId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
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

				conn.close();

				return item;
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public List<Item> findByCondition(String word, int categoryid) {
		List<Item> result = new ArrayList<>();
		try {

			Connection conn = super.connect();
			String sql = "select "
					+ "	* "
					+ "from "
					+ "	items i "
					+ "inner join "
					+ "	categories c "
					+ "on "
					+ "	i.categoryid = c.categoryid "
					+ "where "
					+ "(itemname like ? or detail like ?) ";
			if (categoryid > 0) {
				sql += "and i.categoryid=? ";
			}
			sql += "order by "
					+ "	itemid";

			PreparedStatement stmt = conn.prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, "%" + word + "%");
			stmt.setString(index++, "%" + word + "%");
			if (categoryid > 0) {
				stmt.setInt(index++, categoryid);
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
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

				result.add(item);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Item> findAll() {
		List<Item> result = new ArrayList<>();
		try {

			Connection conn = super.connect();
			String sql = "select "
					+ "	* "
					+ "from "
					+ "	items i "
					+ "inner join "
					+ "	categories c "
					+ "on "
					+ "	i.categoryid = c.categoryid "
					+ "order by "
					+ "	itemid";

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
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

				result.add(item);
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

}