package com.viktor.yurlov.service;

import com.viktor.yurlov.domain.Book;
import com.viktor.yurlov.exception.BookNotFoundException;


public interface BookService {
    Book getBookById(Long id) throws BookNotFoundException;
    void saveBook(Book book);
    void updateBook(Book book);
}
