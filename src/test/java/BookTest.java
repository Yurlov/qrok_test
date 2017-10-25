import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.yurlov.controller.BookController;
import com.viktor.yurlov.entities.Author;
import com.viktor.yurlov.entities.Book;
import com.viktor.yurlov.entities.Response;
import com.viktor.yurlov.services.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookController.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class BookTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    private SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void test_add_book_successfully() throws Exception {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Bill","Murrey", Author.Sex.MALE,sm.parse("1845-05-05")));
        Book book = new Book("Witcher","123445", Book.Genre.FANTASY,authors);
        System.out.println(asJsonString(book));
        mockMvc.perform(post("/book/add").with(user("viktor").password("12345678"))
                .content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new Response("Book added successfully"))));

    }

    @Test
    public void test_get_book_successfully()throws Exception{
        List<Author> authors = new ArrayList<>();
        Author author = new Author("Bill","Murrey", Author.Sex.MALE,sm.parse("1845-05-05"));
        author.setId(1L);
        authors.add(author);
        Book book = new Book("Witcher","123445", Book.Genre.FANTASY,authors);
        book.setId(1234L);

        Response<Book> response = new Response<>();
        response.setBody(book);

        when(bookService.getBookById(1234L)).thenReturn(book);
        mockMvc.perform(get("/book/{id}",1234L)
                .with(user("viktor").password("12345678")))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(response)));

        verify(bookService,times(1)).getBookById(1234L);
    }
    @Test
    public void test_update_book_successfully()throws Exception{
        List<Author> authors = new ArrayList<>();
        Author oldAuthor = new Author("Bill","Murrey", Author.Sex.MALE,sm.parse("1845-05-05"));
        Author newAuthor = new Author("Francheska","Monkey", Author.Sex.FEMALE,sm.parse("1878-01-01"));
        authors.add(oldAuthor);

        Book oldBook = new Book("Witcher","123445", Book.Genre.FANTASY,authors);
        oldBook.setId(1L);

        authors.add(newAuthor);

        Book newBook = new Book("Witcher","87656464", Book.Genre.NOVEL,authors);
        newBook.setId(1L);

        when(bookService.getBookById(1L)).thenReturn(oldBook);

        mockMvc.perform(put("/book/update")
                .with(user("viktor").password("12345678"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newBook))
        ).andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new Response<>("Book changed successfully"))));

    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
