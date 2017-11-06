package com.viktor.yurlov.controller;


import com.viktor.yurlov.domain.Author;
import com.viktor.yurlov.domain.AuthorInfo;
import com.viktor.yurlov.exception.AuthorNotFoundException;
import com.viktor.yurlov.service.AuthorService;
import com.viktor.yurlov.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping(value = "author/add")
    public ResponseEntity addAuthor(@RequestBody Author author){
        authorService.saveAuthor(author);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(value = "author/{id}")
    public ResponseEntity<Author> getById(@PathVariable("id") Long id) throws AuthorNotFoundException {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PutMapping(value = "author/update")
    public ResponseEntity updateAuthor(@RequestBody Author newAuthor) throws AuthorNotFoundException {
        authorService.updateAuthor(newAuthor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "author/info/short/{author_id}")
    public ResponseEntity<AuthorInfo> getEndpoint(@PathVariable("author_id") Long id) throws AuthorNotFoundException {
        Author inDb =  authorService.getAuthorById(id);
        return new ResponseEntity<>(new AuthorInfo(inDb.getFirstName(),inDb.getLastName(),
                Utils.getAge(inDb.getBirthDate()),Utils.getBookTitle(inDb.getBooks())),HttpStatus.OK) ;
    }



}
