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

    @GetMapping("/")
    public String index() {

        return "booklist.html";
    }
}
