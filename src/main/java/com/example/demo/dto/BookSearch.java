package com.example.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class BookSearch {
    private final String searchCriteria;
    private final String searchInput;
}
