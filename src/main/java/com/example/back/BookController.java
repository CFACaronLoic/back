package com.example.back;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BookController {

  private final BookRepository theBook;

  BookController(BookRepository theBook) {
    this.theBook = theBook;
  }

  @GetMapping("/booksearch") 
  String booksearch() {
    CurlQueries querie = new CurlQueries();
    String toto = querie.SimpleSearch("test");
    return toto;
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