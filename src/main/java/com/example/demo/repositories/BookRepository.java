package com.example.demo.repositories;

import com.example.demo.entities.BookEntity;
import com.example.demo.services.BookInterfaceService;
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
    private BookInterfaceService bookInterfaceService;

    @Transactional
    public BookEntity createBook(String title, String author, String isbn) {
        BookEntity book = new BookEntity();
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setTitle(title);

        return bookInterfaceService.saveAndFlush(book);
    }

    @Transactional
    public List<BookEntity> getByTitleOrISBN(String contains) {
        return bookInterfaceService.searchByTitleOrIsbn('%' + contains + '%');
    }
    @Transactional
    public BookEntity getBookById(int id) {
        Optional<BookEntity> optionalBook = bookInterfaceService.findById(id);
        return optionalBook.orElse(null);
    }

    @Transactional
    public List<BookEntity> getAllBooks() {

        return bookInterfaceService.findAll();
    }

}
