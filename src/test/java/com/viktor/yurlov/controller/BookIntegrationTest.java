package com.viktor.yurlov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.yurlov.domain.Author;
import com.viktor.yurlov.domain.Book;
import com.viktor.yurlov.service.AuthorService;
import com.viktor.yurlov.service.BookService;
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

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;

    private final SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

    private final List<Author> authors = Collections.singletonList(new Author("Bill", "Murrey", Author.Sex.MALE, sm.parse("1845-05-05")));
    private final Book book = new Book("Witcher","123445", Book.Genre.FANTASY,authors);

    public BookIntegrationTest() throws ParseException {
    }


    @Test
    public void test_add_book_successfully() throws Exception {
        mockMvc.perform(post("/book/add").with(user("viktor").password("12345678"))
                .content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    public void test_get_book_successfully()throws Exception{

        ResponseEntity<Book> responseEntity = new ResponseEntity<>(book, HttpStatus.OK);
        when(bookService.getBookById(1234L)).thenReturn(book);

        mockMvc.perform(get("/book/{id}",1234L)
                .with(user("viktor").password("12345678")))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(responseEntity.getBody())));

        verify(bookService,times(1)).getBookById(1234L);
    }
    @Test
    public void test_update_book_successfully()throws Exception{
        Book newBook = new Book("Witcher","87656464", Book.Genre.NOVEL,authors);
        newBook.setId(1234L);

        mockMvc.perform(put("/book/update")
                .with(user("viktor").password("12345678"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newBook)))
                .andExpect(status().isOk());


    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
