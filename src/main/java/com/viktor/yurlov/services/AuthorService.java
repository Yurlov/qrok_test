package com.viktor.yurlov.services;

import com.viktor.yurlov.entities.Author;
import com.viktor.yurlov.exceptions.AuthorNotFoundException;
import org.springframework.stereotype.Service;


public interface AuthorService {
    Author getAuthorById(Long id) throws AuthorNotFoundException;
    void saveAuthor(Author author);
    void updateAuthor(Author newAuthor);
}
