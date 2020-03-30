package com.example.demo.services;

import com.example.demo.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookService extends JpaRepository<BookEntity, Integer> {

    @Query("SELECT book FROM BookEntity book " +
            "WHERE book.isbn LIKE :contains " +
            "OR book.title LIKE :contains")
    List<BookEntity> searchByTitleOrIsbn(@Param("contains") String contains);
}