package com.viktor.yurlov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.yurlov.domain.Author;
import com.viktor.yurlov.domain.AuthorInfo;
import com.viktor.yurlov.domain.Book;
import com.viktor.yurlov.domain.Reward;
import com.viktor.yurlov.service.AuthorService;
import com.viktor.yurlov.service.BookService;
import com.viktor.yurlov.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;
    @MockBean
    private BookService bookService;

    private final SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
    private final List<Book> books = asList(new Book("Witcher","1234567", Book.Genre.FANTASY)
            ,new Book("Conan","09876544", Book.Genre.DETECTIVE));
    private final List<Reward> set = Collections.singletonList(new Reward(2017, "Title"));
    private final Author author = new Author("Harry","Potter", Author.Sex.MALE,books,sm.parse("01-02-1992"),set);

    public AuthorIntegrationTest() throws ParseException {
    }

    @Test
    public void test_create_author_success() throws Exception {
        mockMvc.perform(post("/author/add").with(user("viktor").password("12345678"))
                .content(asJsonString(author))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    public void test_get_author_success() throws Exception{

        ResponseEntity<Author> responseEntity = new ResponseEntity<>(author, HttpStatus.OK);


        when(authorService.getAuthorById(1234L)).thenReturn(author);

        mockMvc.perform(get("/author/{id}",1234L).with(user("viktor").password("12345678")))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(responseEntity.getBody())));

        verify(authorService,times(1)).getAuthorById(1234L);

    }

    @Test
    public void test_update_author_success() throws Exception{

        Author newAuthor = new Author("John","Smith", Author.Sex.FEMALE,sm.parse("01-02-1992"));

        when(authorService.getAuthorById(1234L)).thenReturn(author);

        mockMvc.perform(put("/author/update").with(user("viktor").password("12345678"))
                .content(asJsonString(newAuthor))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void test_get_short_info_author() throws Exception {

        AuthorInfo response = new AuthorInfo(
                author.getFirstName(),
                author.getLastName(),
                Utils.getAge(author.getBirthDate()),
                Utils.getBookTitle(author.getBooks()));

        when(authorService.getAuthorById(1234L)).thenReturn(author);

        mockMvc.perform(get("/author/info/short/{author_id}",1234L).with(user("viktor").password("12345678")))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(response)));

        verify(authorService,times(1)).getAuthorById(1234L);
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
