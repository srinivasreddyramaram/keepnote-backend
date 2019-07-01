package com.stackroute.keepnote.model;

import java.util.Date;

public class Reminder {
	
	/*
	 * This class should have six fields
	 * (reminderId,reminderName,reminderDescription,reminderType,
	 * reminderCreatedBy,reminderCreationDate).  This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of reminderCreationDate should not
	 * be accepted from the user but should be always initialized with the system
	 * date.
	 */
	
	private String reminderId;
	private String reminderName;
	private String reminderDescription;
	private String reminderType;
	private String reminderCreatedBy;
	private Date reminderCreationDate;

	public Reminder() {

	}

	public Reminder(String str, String string, String string1, String string2, String string3,
			Date date) {
		this.reminderId = str;
		this.reminderName = string;
		this.reminderDescription = string1;
		this.reminderType = string2;
		this.reminderCreatedBy = string3;
		this.reminderCreationDate = date;
	}

	public String getReminderId() {
		return reminderId;

	}

	public void setReminderId(String str) {
		this.reminderId = str;
	}

	public void setReminderName(String string) {
		this.reminderName = string;
	}

	public String getReminderName() {
		return reminderName;
	}

	public String getReminderDescription() {
		return reminderDescription;
	}

	public void setReminderDescription(String reminderDescription) {
		this.reminderDescription = reminderDescription;
	}

	public String getReminderType() {
		return reminderType;
	}

	public void setReminderType(String string) {
		this.reminderType = string;
	}

	public String getReminderCreatedBy() {
		return reminderCreatedBy;
	}

	public void setReminderCreatedBy(String reminderCreatedBy) {
		this.reminderCreatedBy = reminderCreatedBy;
	}

	public Date getReminderCreationDate() {
		return reminderCreationDate;
	}

	public void setReminderCreationDate(Date reminderCreationDate) {
		this.reminderCreationDate = reminderCreationDate;
	}

	@Override
	public String toString() {
		return "Reminder [reminderId=" + reminderId + ", reminderName=" + reminderName + ", reminderDescription="
				+ reminderDescription + ", reminderType=" + reminderType + ", reminderCreatedBy=" + reminderCreatedBy
				+ ", reminderCreationDate=" + reminderCreationDate + "]";
	}

}
