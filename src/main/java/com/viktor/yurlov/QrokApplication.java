package com.viktor.yurlov;

import com.viktor.yurlov.service.AuthorService;
import com.viktor.yurlov.service.BookService;
import com.viktor.yurlov.util.Utils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.viktor.yurlov")
@SpringBootApplication
public class QrokApplication {
    public static void main(String[] args) {
        SpringApplication.run(QrokApplication.class, args);
    }

    @Bean
    public CommandLineRunner startUp(final BookService bookService, final AuthorService authorService) {
        return strings -> {
            Utils.fillDB().forEach(bookService::saveBook);
        };
    }
}