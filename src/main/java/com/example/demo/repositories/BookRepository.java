package com.example.demo.repositories;

import com.example.demo.entities.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public BookEntity createBook(String title, String author, String isbn) {
        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        return entityManager.merge(book);
    }

    @Transactional
    public BookEntity getBookById(int id) {
        return entityManager.find(BookEntity.class, id);
    }

    @Transactional
    public List<BookEntity> getAllBooks() {
        return entityManager.createQuery("SELECT b FROM BookEntity b", BookEntity.class)
                .getResultList();
    }

}
