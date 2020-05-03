package com.example.demo.repositories;

import com.example.demo.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("SELECT book FROM BookEntity book " +
            "WHERE book.isbn LIKE :contains " +
            "OR book.title LIKE :contains")
    List<BookEntity> searchByTitleOrIsbn(@Param("contains") String contains);

    @Query("SELECT book FROM BookEntity book " +
            "WHERE book.isbn LIKE :isbn ")
    List<BookEntity> getBookByISBN(@Param("isbn") String isbn);

    @Query("SELECT book FROM BookEntity book " +
            "WHERE book.author LIKE :author ")
    List<BookEntity> getBookByAuthor(@Param("author") String author);

    @Query("SELECT book FROM BookEntity book " +
            "WHERE book.title LIKE :title ")
    List<BookEntity> getBookByTitle(@Param("title") String title);
}