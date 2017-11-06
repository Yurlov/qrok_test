package com.viktor.yurlov.service;

import com.viktor.yurlov.domain.Author;
import com.viktor.yurlov.exception.AuthorNotFoundException;
import com.viktor.yurlov.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthorById(Long id) throws AuthorNotFoundException {
        if(authorRepository.findOne(id)==null){
            throw new AuthorNotFoundException("author not found");
        }

        return authorRepository.findOne(id);
    }

    @Override
    @Transactional
    public void saveAuthor(Author author) {
        authorRepository.saveAndFlush(author);
    }

    @Override
    @Transactional
    public void updateAuthor(Author newAuthor) {
        Author old = authorRepository.findOne(newAuthor.getId());
        if(newAuthor.getFirstName()!=null){
            old.setFirstName(newAuthor.getFirstName());
        }
        if(newAuthor.getLastName()!=null){
            old.setLastName(newAuthor.getLastName());
        }
        if(newAuthor.getBirthDate()!=null){
            old.setBirthDate(newAuthor.getBirthDate());
        }
        if(newAuthor.getSex()!=null){
            old.setSex(newAuthor.getSex());
        }
        if(newAuthor.getRewards()!=null){
            newAuthor.getRewards()
                    .forEach(reward -> old.getRewards().add(reward));
        }
        authorRepository.saveAndFlush(old);
    }


}
