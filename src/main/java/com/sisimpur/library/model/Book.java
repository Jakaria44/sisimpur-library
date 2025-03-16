package com.sisimpur.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book  implements Activatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "genre", length = 100)
    private String genre;

    @Column(name = "published_year")
    private int publishedYear;

    @Column(name="is_active", nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToMany(mappedBy = "books")
    private Set<User> users;
}
