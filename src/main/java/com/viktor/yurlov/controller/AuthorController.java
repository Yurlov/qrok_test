package com.viktor.yurlov.controller;

import com.viktor.yurlov.entities.*;
import com.viktor.yurlov.exceptions.AuthorNotFoundException;
import com.viktor.yurlov.services.AuthorService;
import com.viktor.yurlov.util.Utils;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping(value = "author/add")
    public Response addAuthor(@RequestBody Author author){
        authorService.saveAuthor(author);
        return new Response("Author added successfully");
    }


    @GetMapping(value = "author/{id}")
    public Response<Author> getById(@PathVariable("id") Long id) throws AuthorNotFoundException {
        return new Response<>(authorService.getAuthorById(id));
    }

    @PutMapping(value = "author/update")
    public Response updateAuthor(@RequestBody Author newAuthor) throws AuthorNotFoundException {
        authorService.updateAuthor(newAuthor);
        return new Response<>("Author changed successfully");
    }

    @GetMapping(value = "author/info/short/{author_id}")
    public ResponseEndPoint getEndpoint(@PathVariable("author_id") Long id) throws AuthorNotFoundException {
        Author inDb =  authorService.getAuthorById(id);
        return new ResponseEndPoint(inDb.getFirstName(),inDb.getLastName(),
                Utils.getAge(inDb.getBirthDate()),Utils.getBookTitle(inDb.getBooks()));
    }



}
