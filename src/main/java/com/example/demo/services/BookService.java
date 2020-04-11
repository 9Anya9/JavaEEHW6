package com.example.demo.services;


import com.example.demo.entities.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private BookInterfaceService bookInterfaceService;

    @Transactional
    public List<BookEntity> getBookByISBN(String isbn) {
        return bookInterfaceService.getBookByISBN('%' + isbn + '%');
    }

    @Transactional
    public List<BookEntity> getBookByAuthor(String author) {
        return bookInterfaceService.getBookByAuthor('%' + author + '%');
    }

    @Transactional
    public List<BookEntity> getBookByTitle(String title) {
        return bookInterfaceService.getBookByTitle('%' + title + '%');
    }

    @Transactional
    public BookEntity addBook(String title, String author, String isbn) {
        BookEntity book = new BookEntity();
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setTitle(title);
        return bookInterfaceService.saveAndFlush(book);
    }


    @Transactional
    public List<BookEntity> getAllBooks() {
        return bookInterfaceService.findAll();
    }

    @Transactional
    public List<BookEntity> getBookByTitleOrIsbn(String contains) {
        return bookInterfaceService.searchByTitleOrIsbn('%' + contains + '%');
    }

    @Transactional
    public BookEntity getBookById(int id) {
        Optional<BookEntity> optionalBook = bookInterfaceService.findById(id);
        return optionalBook.orElse(null);
    }
}