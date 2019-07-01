package com.stackroute.keepnote.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.NoteUser;
import com.stackroute.keepnote.repository.NoteRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */

@Service
public class NoteServiceImpl implements NoteService {

	/*
	 * Autowiring should be implemented for the NoteRepository and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	private NoteRepository noteRepository;

	@Autowired
	public NoteServiceImpl(NoteRepository noteRepository) {
		super();
		this.noteRepository = noteRepository;
	}

	/*
	 * This method should be used to save a new note.
	 */
	public boolean createNote(Note note) {

		List<Note> notes = new ArrayList<Note>();
		notes.add(note);
		NoteUser noteUser = new NoteUser();
		noteUser.setNotes(notes);
		NoteUser newNote = noteRepository.insert(noteUser);

		if (newNote != null)
			return true;
		else
			return false;
	}

	/* This method should be used to delete an existing note. */

	public boolean deleteNote(String userId, int noteId) {
		NoteUser noteUser = noteRepository.findById(userId).get();
		List<Note> notes = noteUser.getNotes();
		boolean flag = false;
		
		for (Note note : notes) {
			if(note.getNoteId() == noteId)
			{
				flag = notes.remove(note);
				break;
			}
		}
		
		if(flag) {
			noteRepository.save(noteUser);
			return true;
		}
		
		return false;	
	}

	/* This method should be used to delete all notes with specific userId. */

	public boolean deleteAllNotes(String userId) {
		NoteUser noteUser = noteRepository.findById(userId).get();
		List<Note> notes = noteUser.getNotes();
		notes.clear();
		NoteUser changedUserNote = noteRepository.save(noteUser);
		if(notes.isEmpty() && changedUserNote!=null)
			return true;
		else
			return false;
	}

	/*
	 * This method should be used to update a existing note.
	 */
	public Note updateNote(Note note, int id, String userId) throws NoteNotFoundExeption {		
	
		Note updatedNote = null;
		try {
			getNoteByNoteId(userId, id);				
		}
		catch(NoSuchElementException e)
		{
			throw new NoteNotFoundExeption("No note found to update");	
		}
		NoteUser noteUser = noteRepository.findById(userId).get();	
		List<Note> notes = noteUser.getNotes();
		for (Note fetchedNote : notes) {
			if(fetchedNote.getNoteId() == id)
			{
				updatedNote = fetchedNote;
				updatedNote.setCategory(note.getCategory());
				updatedNote.setNoteContent(note.getNoteContent());
				updatedNote.setNoteCreatedBy(userId);
				updatedNote.setNoteStatus(note.getNoteStatus());
				updatedNote.setNoteTitle(note.getNoteTitle());
				updatedNote.setReminders(note.getReminders());
				break;
			}
		}
		noteRepository.save(noteUser);
		return updatedNote;
	}

	/*
	 * This method should be used to get a note by noteId created by specific user
	 */
	public Note getNoteByNoteId(String userId, int noteId) throws NoteNotFoundExeption {
		NoteUser noteUser = null;
		try {
			noteUser = noteRepository.findById(userId).get();
		}
		catch(NoSuchElementException e)
		{
			throw new NoteNotFoundExeption("No note found");
		}
		List<Note> allNotesOfUser = noteUser.getNotes();
		Note fetchedNote = null;

		for (Note note : allNotesOfUser) {
			if (note.getNoteId() == noteId)
				fetchedNote = note;
		}

		return fetchedNote;
	}

	/*
	 * This method should be used to get all notes with specific userId.
	 */
	public List<Note> getAllNoteByUserId(String userId) {
		return noteRepository.findById(userId).get().getNotes();
	}

}
