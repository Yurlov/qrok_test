package com.viktor.yurlov.controller;

import com.viktor.yurlov.entities.Book;
import com.viktor.yurlov.entities.Response;
import com.viktor.yurlov.exceptions.BookNotFoundException;
import com.viktor.yurlov.services.BookService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "book/add")
    public Response addNewBook(@Valid @RequestBody Book book){
        bookService.saveBook(book);
        return new Response("Book added successfully");
    }
    @GetMapping(value = "book/{id}")
    public Response<Book> getById(@PathVariable("id") Long id) throws BookNotFoundException {
        return new Response<>(bookService.getBookById(id));
    }

    @PutMapping(value = "book/update")
    public Response updateBook(@RequestBody Book newBook) throws BookNotFoundException {
        bookService.updateBook(newBook);
        return new Response("Book changed successfully");
    }

}
