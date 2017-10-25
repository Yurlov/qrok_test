package com.viktor.yurlov.services;

import com.viktor.yurlov.entities.Book;
import com.viktor.yurlov.exceptions.BookNotFoundException;


public interface BookService {
    Book getBookById(Long id) throws BookNotFoundException;
    void saveBook(Book book);
    void updateBook(Book book);
}
