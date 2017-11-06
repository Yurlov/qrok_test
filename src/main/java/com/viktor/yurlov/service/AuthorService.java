package com.viktor.yurlov.service;

import com.viktor.yurlov.domain.Author;
import com.viktor.yurlov.exception.AuthorNotFoundException;


public interface AuthorService {
    Author getAuthorById(Long id) throws AuthorNotFoundException;
    void saveAuthor(Author author);
    void updateAuthor(Author newAuthor);
}
