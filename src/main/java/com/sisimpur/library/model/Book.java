package com.sisimpur.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private Long id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "genre", length = 100)
    private String genre;

    @Column(name = "published_year")
    private int publishedYear;

    // Add more fields as needed
}
