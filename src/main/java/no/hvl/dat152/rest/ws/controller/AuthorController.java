/**
 * 
 */
package no.hvl.dat152.rest.ws.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.hvl.dat152.rest.ws.exceptions.AuthorNotFoundException;
import no.hvl.dat152.rest.ws.model.Author;
import no.hvl.dat152.rest.ws.model.Book;
import no.hvl.dat152.rest.ws.service.AuthorService;

/**
 * 
 */
@RestController
@RequestMapping("/elibrary/api/v1")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/authors")
	public ResponseEntity<Object> getAllAuthors() {
		List<Author> authors = authorService.findAll();

		if (authors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(authors, HttpStatus.OK);
	}

	@PostMapping("/authors")
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) {

		Author nauthor = authorService.saveAuthor(author);

		return new ResponseEntity<>(nauthor, HttpStatus.CREATED);

	}

	@GetMapping("/authors/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Author> getAuthor(@PathVariable("id") Long id) throws AuthorNotFoundException {
		
		Author author = authorService.findById(id);
		
		return new ResponseEntity<>(author, HttpStatus.OK);		
	}

	@PutMapping("/authors/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Object> updateAuthor(@RequestBody Author author, @PathVariable("id") long id) {

		Author nauthor;

		try {
			nauthor = authorService.updateAuthor(author);
		} catch (AuthorNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(nauthor, HttpStatus.OK);

	}
	
	@GetMapping("/authors/{id}/books")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Object> getAllBooksAuthor(@PathVariable("id") Long id) throws AuthorNotFoundException {
		
		Set<Book> books;
		
		try {
			books = authorService.getAllBooks(id);
		} catch (AuthorNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(books, HttpStatus.OK);		
	}

}
