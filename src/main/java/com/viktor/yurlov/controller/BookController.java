package com.viktor.yurlov.controller;

import com.viktor.yurlov.domain.Book;
import com.viktor.yurlov.exception.BookNotFoundException;
import com.viktor.yurlov.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "book/add")
    public ResponseEntity addNewBook(@Valid @RequestBody Book book){
        bookService.saveBook(book);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(value = "book/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id) throws BookNotFoundException {
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }

    @PutMapping(value = "book/update")
    public ResponseEntity updateBook(@RequestBody Book newBook) throws BookNotFoundException {
        bookService.updateBook(newBook);
        return new ResponseEntity(HttpStatus.OK);
    }

}
