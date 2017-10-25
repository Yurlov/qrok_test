package com.viktor.yurlov.repositories;

import com.viktor.yurlov.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
