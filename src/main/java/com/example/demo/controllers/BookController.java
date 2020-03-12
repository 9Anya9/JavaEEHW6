package com.example.demo.controllers;

import com.example.demo.repositories.BookRepository;
import com.example.demo.entities.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/save-book", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveBook(@ModelAttribute BookEntity book, Model model) {
        model.addAttribute("book", book);
        bookRepository.createBook(book.getTitle(), book.getAuthor(), book.getIsbn());
        return "redirect:/";
    }
}
