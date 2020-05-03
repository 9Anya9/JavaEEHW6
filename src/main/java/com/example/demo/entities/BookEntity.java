package com.example.demo.entities;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;
}