package com.sisimpur.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name="bio")
    private String bio;

    @Column(name="is_active", nullable = false)
    private boolean active ;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books;
}
