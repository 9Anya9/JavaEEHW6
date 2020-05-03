package com.example.demo.controllers;

import com.example.demo.services.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllController {

    private final BookService bookService;

    public AllController(BookService bookService) {

        this.bookService = bookService;
    }

    @GetMapping("/login")
    public String login() {
        return "signin";
    }

    @GetMapping("/register")
    public String registration() {
        return "signup";
    }

    @GetMapping("/")
    public String mainView() {
        return "main";
    }

    @PreAuthorize("isFullyAuthenticated()")
    @GetMapping("/profile")
    public String myAccount() {
        return "myaccount";
    }


}
