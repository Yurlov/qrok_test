package com.viktor.yurlov.services;


import com.viktor.yurlov.entities.Book;
import com.viktor.yurlov.exceptions.BookNotFoundException;
import com.viktor.yurlov.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override

    public Book getBookById(Long id) throws BookNotFoundException {
        if(bookRepository.findOne(id)==null){
            throw new BookNotFoundException("book not found");
        }
        return bookRepository.findOne(id);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override

    public void updateBook(Book update) {
        Book old = bookRepository.findOne(update.getId());
        if(update.getTitle()!=null){
            old.setTitle(update.getTitle());
        }
        if(update.getGenre()!=null){
            old.setGenre(update.getGenre());
        }
        if(update.getIsbn()!=null){
            old.setIsbn(update.getIsbn());
        }
        if(update.getAuthors()!=null){
            update.getAuthors().forEach(author -> old.getAuthors().add(author));
        }
        bookRepository.saveAndFlush(old);
    }
}
