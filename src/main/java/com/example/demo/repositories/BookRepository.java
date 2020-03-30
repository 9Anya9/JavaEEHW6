package com.example.demo.repositories;

import com.example.demo.entities.BookEntity;
import com.example.demo.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookRepository {

    @Autowired
    private BookService bookService;

    @Transactional
    public BookEntity createBook(String title, String author, String isbn) {
        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        return bookService.saveAndFlush(book);
    }

    @Transactional
    public List<BookEntity> getByTitleOrISBN(String contains) {
        return bookService.searchByTitleOrIsbn('%' + contains + '%');
    }
    @Transactional
    public BookEntity getBookById(int id) {
        Optional<BookEntity> optionalBook = bookService.findById(id);
        return optionalBook.orElse(null);
    }

    @Transactional
    public List<BookEntity> getAllBooks() {
        return bookService.findAll();
    }

}
