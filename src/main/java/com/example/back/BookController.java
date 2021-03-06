package com.example.back;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
class BookController {

  private final BookRepository theBook;

  BookController(BookRepository theBook) {
    this.theBook = theBook;
  }

  @GetMapping("/booksearch/{search}") 
  Map<String, Object> booksearch(@PathVariable("search") String search) {
    String toto = CurlQueries.SimpleSearch(search);
    JSONObject jsonObject = new JSONObject(toto);
    System.out.println(jsonObject);
    return jsonObject.toMap();
  }

  @GetMapping("/bookfieldsearch/{field}/{search}") 
  Map<String, Object> bookfieldsearch(@PathVariable("search") String search, @PathVariable("field") String field) {
    String toto = CurlQueries.FieldSearch(field, search);
    JSONObject jsonObject = new JSONObject(toto);
    System.out.println(jsonObject);
    return jsonObject.toMap();
  }

  @GetMapping("/bookregexpsearch/{field}/{search}") 
  Map<String, Object> bookregexpsearch(@PathVariable("search") String search, @PathVariable("field") String field) {
    String toto = CurlQueries.FieldSearch(field, search);
    JSONObject jsonObject = new JSONObject(toto);
    System.out.println(jsonObject);
    return jsonObject.toMap();
  }

  @GetMapping("/bookall/{id}") 
  Map<String, Object> bookall(@PathVariable("id") int id) {
    JSONObject jsonObject = new JSONObject(CurlQueries.GetAll(id));
    System.out.println(jsonObject);
    return jsonObject.toMap();
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/allbook")
  List<Book> all() {
    return theBook.findAll();
  }
  // end::get-aggregate-root[]


  @PostMapping("/book")
  Book newBook(@RequestBody Book newBook) {
    return theBook.save(newBook);
  }

  // Single item
  
  @GetMapping("/book/{id}")
  Book one(@PathVariable Long id) {
    
    return theBook.findById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @PutMapping("/book/{id}")
  Book replaceBook(@RequestBody Book newBook, @PathVariable Long id) {
    
    return theBook.findById(id)
      .map(book -> {
        book.setAuthor(newBook.getAuthor());
        return theBook.save(book);
      })
      .orElseGet(() -> {
        newBook.setId(id);
        return theBook.save(newBook);
      });
  }

  @DeleteMapping("/book/{id}")
  void deleteBook(@PathVariable Long id) {
    theBook.deleteById(id);
  }
}