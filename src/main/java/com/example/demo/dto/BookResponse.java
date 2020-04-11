package com.example.demo.dto;

import com.example.demo.entities.BookEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class BookResponse {
    private final List<BookEntity> books;
}