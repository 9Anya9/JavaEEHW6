package com.example.demo.services;

import com.example.demo.entities.BookEntity;
import com.example.demo.repositories.BookRepository;
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
    private BookRepository bookRepository;

    @Transactional
    public List<BookEntity> getBookByISBN(String isbn) {
        return bookRepository.getBookByISBN('%' + isbn + '%');
    }

    @Transactional
    public List<BookEntity> getBookByTitle(String title) {
        return bookRepository.getBookByTitle('%' + title + '%');
    }

    @Transactional
    public List<BookEntity> getBookByAuthor(String author) {
        return bookRepository.getBookByAuthor('%' + author + '%');
    }


    @Transactional
    public BookEntity addBook(String isbn, String author, String title) {
        BookEntity book = new BookEntity();
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setTitle(title);
        return bookRepository.saveAndFlush(book);
    }

    @Transactional
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public List<BookEntity> getBookByTitleOrIsbn(String contains) {
        return bookRepository.searchByTitleOrIsbn('%' + contains + '%');
    }

    @Transactional
    public BookEntity getBookById(int id) {
        Optional<BookEntity> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }
}