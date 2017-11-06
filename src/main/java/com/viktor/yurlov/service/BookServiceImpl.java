package com.viktor.yurlov.service;


import com.viktor.yurlov.domain.Book;
import com.viktor.yurlov.exception.BookNotFoundException;
import com.viktor.yurlov.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
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
    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional
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
