package com.example.demo.controllers;

import com.example.demo.dto.BookResponse;
import com.example.demo.dto.BookSearch;
import com.example.demo.entities.BookEntity;
import com.example.demo.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @ResponseBody
    @GetMapping("/booklist")
    public ResponseEntity<BookResponse> booklist() {
        return ResponseEntity.ok(BookResponse.of(bookService.getAllBooks()));
    }

    @ResponseBody
    @PostMapping("/addnewbook")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookEntity book) {
        bookService.addBook(book.getTitle(), book.getAuthor(), book.getIsbn());
        return ResponseEntity.ok(BookResponse.of(bookService.getAllBooks()));
    }

    @ResponseBody
    @PostMapping("/booksearch")
    public ResponseEntity<BookResponse> searchBook(@RequestBody BookSearch sDto) {
        String whatToSearch = sDto.getWhatToSearch();
        String category = sDto.getCategory();

        List<BookEntity> books;

        switch (category) {
            case "isbn":
                books = bookService.getBookByISBN(whatToSearch);
                break;
            case "author":
                books = bookService.getBookByAuthor(whatToSearch);
                break;
            case "title":
                books = bookService.getBookByTitle(whatToSearch);
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BookResponse.of(null));
        }

        return ResponseEntity.ok(BookResponse.of(books));
    }

}

