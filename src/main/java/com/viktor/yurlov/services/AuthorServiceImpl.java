package com.viktor.yurlov.services;

import com.viktor.yurlov.entities.Author;
import com.viktor.yurlov.exceptions.AuthorNotFoundException;
import com.viktor.yurlov.repositories.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
    public void saveAuthor(Author author) {
        authorRepository.saveAndFlush(author);
    }

    @Override
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
