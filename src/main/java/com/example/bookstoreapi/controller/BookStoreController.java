package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookStoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
public class BookStoreController {

    private final BookStoreRepository repository;

    public BookStoreController(BookStoreRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@NotNull @Valid @RequestBody Book book) {
        return repository.save(book);
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Iterable<Book>> getAllBooks() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/book")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) {
        boolean bookExists = repository.existsById(book.getId());
        return bookExists ? ResponseEntity.ok(saveBook(book)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteBook(@PathVariable @Min(1) Integer id) {
        repository.deleteById(id);
        return "Book #" + id + " is deleted";
    }

}
