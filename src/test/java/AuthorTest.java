import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.yurlov.SecurityConfig;
import com.viktor.yurlov.controller.AuthorController;
import com.viktor.yurlov.entities.*;
import com.viktor.yurlov.services.AuthorService;
import com.viktor.yurlov.util.Utils;
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
@SpringBootTest(classes = AuthorController.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class AuthorTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorService authorService;

    private SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");

    @Test
    public void test_create_author_success() throws Exception {
        List<Reward> set = new ArrayList<>();
        set.add(new Reward(2017,"Title"));
        Author author = new Author("Harry","Potter", Author.Sex.MALE,sm.parse("01-02-1992"),set);

        mockMvc.perform(post("/author/add").with(user("viktor").password("12345678"))
                .content(asJsonString(author))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new Response("Author added successfully"))));


    }

    @Test
    public void test_get_author_success() throws Exception{
        List<Reward> set = new ArrayList<>();
        set.add(new Reward(2017,"Title"));
        Author author = new Author("Harry","Potter", Author.Sex.MALE,sm.parse("01-02-1992"),set);

        Response<Author> response = new Response<>();
        response.setBody(author);

        when(authorService.getAuthorById(1234L)).thenReturn(author);
        mockMvc.perform(get("/author/{id}",1234L).with(user("viktor").password("12345678")))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(response)));

        verify(authorService,times(1)).getAuthorById(1234L);

    }

    @Test
    public void test_update_author_success() throws Exception{
        List<Reward> rewardsOld= new ArrayList<>();
        rewardsOld.add(new Reward(2017,"The Worst Author of the Year"));
        Author oldAuthor = new Author("Harry","Potter", Author.Sex.MALE,sm.parse("01-02-1992"),rewardsOld);


        rewardsOld.add(new Reward(2012,"The Best Author of the Year"));
        Author newAuthor = new Author("John","Smith", Author.Sex.FEMALE,sm.parse("01-02-1992"),rewardsOld);

        when(authorService.getAuthorById(1234L)).thenReturn(oldAuthor);
        mockMvc.perform(put("/author/update").with(user("viktor").password("12345678"))
                .content(asJsonString(newAuthor))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content()
                        .json(asJsonString(new Response("Author changed successfully"))));

    }

    @Test
    public void test_get_short_info_author() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Witcher","1234567", Book.Genre.FANTASY));
        books.add(new Book("Conan","09876544", Book.Genre.DETECTIVE));
        Author oldAuthor = new Author("Harry","Potter", Author.Sex.MALE,books,sm.parse("01-02-1992"));

        ResponseEndPoint response = new ResponseEndPoint(
                oldAuthor.getFirstName(),
                oldAuthor.getLastName(),
                Utils.getAge(oldAuthor.getBirthDate()),
                Utils.getBookTitle(oldAuthor.getBooks()));
        when(authorService.getAuthorById(1234L)).thenReturn(oldAuthor);
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
