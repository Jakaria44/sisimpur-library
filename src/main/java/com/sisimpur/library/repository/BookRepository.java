package com.sisimpur.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sisimpur.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}