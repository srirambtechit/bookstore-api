package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class BookStoreController {

    private final BookStoreRepository repository;

    public BookStoreController(BookStoreRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return new ResponseEntity<>(repository.save(book), OK);
    }

    @GetMapping("/books")
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        return new ResponseEntity<>(repository.findAll(), OK);
    }

    @PutMapping("/book")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        boolean bookExists = repository.existsById(book.getId());
        return bookExists ? saveBook(book) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Book #" + id + " is deleted");
    }

}
