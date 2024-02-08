package aiwa.entity;

public class Category {

	private int categoryId;
	private String categoryName;

	/**
	 * カテゴリーIDを取得する
	 * @return
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * カテゴリーIDをセットする
	 * @param categoryId
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
