package com.sisimpur.library.service;

import org.springframework.stereotype.Service;

import com.sisimpur.library.model.Book;
import com.sisimpur.library.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

}
