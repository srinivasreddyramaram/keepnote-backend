package com.stackroute.keepnote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.service.NoteService;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
@RequestMapping("/api/v1")
public class NoteController {

	/*
	 * Autowiring should be implemented for the NoteService. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword
	 */
	private NoteService noteService;

	@Autowired
	public NoteController(NoteService noteService) {
		super();
		this.noteService = noteService;
	}

	/*
	 * Define a handler method which will create a specific note by reading the
	 * Serialized object from request body and save the note details in the
	 * database.This handler method should return any one of the status messages
	 * basis on different situations: 1. 201(CREATED) - If the note created
	 * successfully. 2. 409(CONFLICT) - If the noteId conflicts with any existing
	 * user.
	 * 
	 * This handler method should map to the URL "/api/v1/note" using HTTP POST
	 * method
	 */
	@PostMapping("/note")
	public ResponseEntity<?> createNote(@RequestBody Note note) {
		boolean flag = false;

		flag = noteService.createNote(note);

		if (!flag)
			return new ResponseEntity<Note>(note, HttpStatus.CONFLICT);
		else
			return new ResponseEntity<Note>(note, HttpStatus.CREATED);
	}

	/*
	 * Define a handler method which will delete a note from a database. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the note deleted successfully from
	 * database. 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/note/{id}" using HTTP
	 * Delete method" where "id" should be replaced by a valid noteId without {}
	 */
	@DeleteMapping("/note/{userId}/{noteId}")
	public ResponseEntity<?> deleteNote(@PathVariable("userId") String userId, @PathVariable("noteId") String noteId) {
		boolean flag = false;
		flag = noteService.deleteNote(userId, Integer.parseInt(noteId));

		if (flag)
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/note/{userId}")
	public ResponseEntity<?> deleteAllNotesByUserId(@PathVariable("userId") String userId) {
		boolean flag = false;
		try {
			flag = noteService.deleteAllNotes(userId);
		} catch (NoteNotFoundExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (flag)
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

	/*
	 * Define a handler method which will update a specific note by reading the
	 * Serialized object from request body and save the updated note details in a
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 1. 200(OK) - If the note updated successfully.
	 * 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/note/{id}" using HTTP PUT
	 * method.
	 */
	@PutMapping("/note/{userId}/{noteId}")
	public ResponseEntity<?> updateNote(@RequestBody Note note, @PathVariable("userId") String userId,
			@PathVariable("noteId") String noteId) {
		Note foundNote = null;

		try {
			foundNote = noteService.updateNote(note, Integer.parseInt(noteId), userId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoteNotFoundExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (foundNote == null)
			return new ResponseEntity<Note>(note, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Note>(note, HttpStatus.OK);
	}

	/*
	 * Define a handler method which will get us the all notes by a userId. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the note found successfully.
	 * 
	 * This handler method should map to the URL "/api/v1/note" using HTTP GET
	 * method
	 */
	@GetMapping("/note/{userId}")
	public ResponseEntity<?> getAllNotesById(@PathVariable("userId") String userId) {
		List<Note> allNotes = noteService.getAllNoteByUserId(userId);

		return new ResponseEntity<List<Note>>(allNotes, HttpStatus.OK);
	}

	/*
	 * Define a handler method which will show details of a specific note created by
	 * specific user. This handler method should return any one of the status
	 * messages basis on different situations: 1. 200(OK) - If the note found
	 * successfully. 2. 404(NOT FOUND) - If the note with specified noteId is not
	 * found. This handler method should map to the URL
	 * "/api/v1/note/{userId}/{noteId}" using HTTP GET method where "id" should be
	 * replaced by a valid reminderId without {}
	 * 
	 */
	@GetMapping("/note/{userId}/{noteId}")
	public ResponseEntity<?> getNoteById(@PathVariable("userId") String userId, @PathVariable("noteId") String noteId) {
		Note foundNote = null;

		try {
			foundNote = noteService.getNoteByNoteId(userId, Integer.parseInt(noteId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoteNotFoundExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (foundNote == null)
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Note>(foundNote, HttpStatus.OK);
	}

}
