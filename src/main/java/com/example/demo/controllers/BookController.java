package com.example.demo.controllers;

import com.example.demo.repositories.BookRepository;
import com.example.demo.entities.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping({ "/", "" })
    public String booklist(Model model) {
        List<BookEntity> books = bookRepository.getAllBooks();
        model.addAttribute("books", books);
        return "booklist";
    }

    @RequestMapping(value = "/book/{id}")
    public String bookInfo(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("book", bookRepository.getBookById(id));
        return "aboutbook";
    }

    @RequestMapping(value = "/redirsavebook", method = RequestMethod.GET)
    public String redirSaveBook() {
        return "savenew";
    }


    @RequestMapping(value = "/search-results", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchResOfTitleAndIsbn(@RequestParam String search, Model model) {
        List<BookEntity> books = bookRepository.getByTitleOrISBN(search);
        System.out.println(books);
        model.addAttribute("books", books);
        return "findTitleIsbn";
    }

    @RequestMapping(value = "/save-book", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveBook(@ModelAttribute BookEntity book, Model model) {
        model.addAttribute("book", book);
        bookRepository.createBook(book.getTitle(), book.getAuthor(), book.getIsbn());
        return "redirect:/";
    }

}
