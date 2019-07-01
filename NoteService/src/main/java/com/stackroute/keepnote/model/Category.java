package com.stackroute.keepnote.model;

import java.util.Date;

public class Category {

	/*
	 * This class should have five fields
	 * (categoryId,categoryName,categoryDescription,
	 * categoryCreatedBy,categoryCreationDate). This class should also contain the
	 * getters and setters for the fields along with the toString method. The value
	 * of categoryCreationDate should not be accepted from the user but should be
	 * always initialized with the system date.
	 */
	private String categoryId;
	private String categoryName;
	private String categoryDescription;
	private String categoryCreatedBy;
	private Date categoryCreationDate;

	public Category() {

	}

	public Category(String Int, String string, String string1, Date date, String string2) {
		categoryId = Int;
		categoryName = string;
		categoryDescription = string1;
		categoryCreatedBy = string2;
		categoryCreationDate = date;
	}

	public void setCategoryId(String Int) {
		categoryId = Int;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String string) {
		categoryName = string;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String string) {
		categoryDescription = string;
	}

	public void setCategoryCreationDate(Date date) {
		categoryCreationDate = date;
	}

	public void setCategoryCreatedBy(String string) {
		categoryCreatedBy = string;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDescription="
				+ categoryDescription + ", categoryCreatedBy=" + categoryCreatedBy + ", categoryCreationDate="
				+ categoryCreationDate + "]";
	}

}
