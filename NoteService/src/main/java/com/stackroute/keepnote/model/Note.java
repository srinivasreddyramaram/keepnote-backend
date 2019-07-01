package com.stackroute.keepnote.model;

import java.util.Date;
import java.util.List;

public class Note {
	

	/*
	 * This class should have eight fields
	 * (noteId,noteTitle,noteContent,noteStatus,createdAt,
	 * category,reminder,createdBy). This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of createdAt should not be
	 * accepted from the user but should be always initialized with the system date.
	 * 
	 */
		private int noteId;
		private String noteTitle;
		private String noteContent;
		private String noteStatus;
		private Date createdAt;
		private Category category;
		private List<Reminder> reminders;
		private String createdBy;

	    // getters & setters

		public Note() {

		}
		
		public Note(int noteId, String noteTitle, String noteContent, String noteStatus, Date createdAt, Category category,
				List<Reminder> reminders, String createdBy) {
			super();
			this.noteId = noteId;
			this.noteTitle = noteTitle;
			this.noteContent = noteContent;
			this.noteStatus = noteStatus;
			this.createdAt = createdAt;
			this.category = category;
			this.reminders = reminders;
			this.createdBy = createdBy;
		}

		public int getNoteId() {
			return noteId;
		}

		public void setNoteId(int Int) {
			noteId=Int;
		}

		public String getNoteTitle() {
			return noteTitle;
		}

		public void setNoteTitle(String string) {
			noteTitle=string;
		}

		public String getNoteContent() {
			return noteContent;
		}

		public void setNoteContent(String string) {
			noteContent=string;
		}
		
		public String getNoteStatus() {
	        return noteStatus;
	    }

		public void setNoteStatus(String string) {
			noteStatus=string;
		}

		public Date getNoteCreationDate() {
	        return createdAt;
	    }

	    public void setNoteCreationDate(Date noteCreationDate) {
	        this.createdAt=noteCreationDate;
	    }

	    public String getNoteCreatedBy() {
	        return createdBy;
	    }

	    public void setNoteCreatedBy(String noteCreatedBy) {
	        this.createdBy = noteCreatedBy;
	    }

		public Category getCategory() {
			return category;
		}
		
		public void setCategory(Category category) {
			this.category = category;
		}
		
	    public List<Reminder> getReminders() {
	        return reminders;
	    }

	    public void setReminders(List<Reminder> reminders) {
	    	this.reminders = reminders;
	    }

		@Override
		public String toString() {
			return "Note [noteId=" + noteId + ", noteTitle=" + noteTitle + ", noteContent=" + noteContent
					+ ", noteStatus=" + noteStatus + ", createdAt=" + createdAt + ", category=" + category
					+ ", reminders=" + reminders + ", createdBy=" + createdBy + "]";
		}	
}
