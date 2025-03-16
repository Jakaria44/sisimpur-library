package com.sisimpur.library.service;

import com.sisimpur.library.dto.BookCreateDto;
import com.sisimpur.library.dto.BookDto;
import com.sisimpur.library.model.Book;
import org.springframework.data.domain.Page;

public interface BookService extends GenericService<Book, BookDto, Long> {
    BookDto createBook(BookCreateDto bookCreateDto);

    BookDto assignBookToUser(Long bookId, Long userId);

    BookDto unassignBookFromUser(Long bookId, Long userId);
}
