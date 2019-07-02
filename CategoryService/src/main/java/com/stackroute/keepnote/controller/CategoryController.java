package com.stackroute.keepnote.controller;

import java.util.List;

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

import com.stackroute.keepnote.exception.CategoryDoesNoteExistsException;
import com.stackroute.keepnote.exception.CategoryNotCreatedException;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.service.CategoryService;

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
public class CategoryController {

	/*
	 * Autowiring should be implemented for the CategoryService. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword
	 */
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	
	/*
	 * Define a handler method which will create a category by reading the
	 * Serialized category object from request body and save the category in
	 * database. Please note that the careatorId has to be unique.This
	 * handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 201(CREATED - In case of successful creation of the category
	 * 2. 409(CONFLICT) - In case of duplicate categoryId
	 *
	 * 
	 * This handler method should map to the URL "/api/v1/category" using HTTP POST
	 * method".
	 */
	@PostMapping("/category")
	public ResponseEntity<?> createCategory(@RequestBody Category category) {

		Category newCategory = null;
		try {
			newCategory = categoryService.createCategory(category);
		} catch (CategoryNotCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (newCategory!=null)
			return new ResponseEntity<Category>(category, HttpStatus.CREATED);
		else
			return new ResponseEntity<Category>(category, HttpStatus.CONFLICT);
	}

	
	
	/*
	 * Define a handler method which will delete a category from a database.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the category deleted successfully from
	 * database. 2. 404(NOT FOUND) - If the category with specified categoryId is
	 * not found. 
	 * 
	 * This handler method should map to the URL "/api/v1/category/{id}" using HTTP Delete
	 * method" where "id" should be replaced by a valid categoryId without {}
	 */

	@DeleteMapping("/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
		try {
			categoryService.deleteCategory(id);
		} catch (CategoryDoesNoteExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/*
	 * Define a handler method which will update a specific category by reading the
	 * Serialized object from request body and save the updated category details in
	 * database. This handler method should return any one of the status
	 * messages basis on different situations: 1. 200(OK) - If the category updated
	 * successfully. 2. 404(NOT FOUND) - If the category with specified categoryId
	 * is not found. 
	 * This handler method should map to the URL "/api/v1/category/{id}" using HTTP PUT
	 * method.
	 */
	@PutMapping("/category/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody Category category,@PathVariable("id") String categoryId){
		Category foundCategory = null;
		foundCategory= categoryService.updateCategory(category, categoryId);

		if(foundCategory == null)
			return new ResponseEntity<Category>(HttpStatus.CONFLICT);
		else
			return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	/*
	 * Define a handler method which will get us the category by a userId.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the category found successfully. 
	 * 
	 * 
	 * This handler method should map to the URL "/api/v1/category" using HTTP GET method
	 */
	/* @GetMapping("/category/{userId}")
	public ResponseEntity<?> getCategoriesByUserId(@PathVariable("userId") String userId) {
		List<Category> categories= categoryService.getAllCategoryByUserId(userId);
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);

	}*/

	@GetMapping("/category/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") String id) {
		Category categorie = null;
		try {
			categorie = categoryService.getCategoryById(id);
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}

			return new ResponseEntity<Category>(categorie, HttpStatus.OK);

	}
	


}
