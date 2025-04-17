package com.sisimpur.library.mappers;


import com.sisimpur.library.dto.AuthorSummaryDto;
import com.sisimpur.library.dto.BookDto;
import com.sisimpur.library.mapper.AuthorSummaryMapper;
import com.sisimpur.library.mapper.AuthorSummaryMapperImpl;
import com.sisimpur.library.mapper.BookMapper;
import com.sisimpur.library.mapper.BookMapperImpl;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class BookMapperTest {

    private final AuthorSummaryMapper authorSummaryMapper = new AuthorSummaryMapperImpl();
    private final BookMapper bookMapper = new BookMapperImpl(authorSummaryMapper);

    @Test
    void shouldMapBookToBookDto() {
        // Given
        Author author = Author.builder().id(1L).bio("Biography").name("John Doe").active(true).build();
        Book book = Book.builder()
                .id(1L)
                .title("Book 1")
                .genre("Fiction")
                .publishedYear(2020)
                .active(true)
                .author(author)
                .build();

        // When
        BookDto bookDto = bookMapper.toDto(book);

        // Then
        assertEquals(1L, bookDto.getId());
        assertEquals("Book 1", bookDto.getTitle());
        assertEquals("Fiction", bookDto.getGenre());
        assertEquals(2020, bookDto.getPublishedYear());
        assertTrue(bookDto.isActive());
        assertEquals(1L, bookDto.getAuthor().getId());
        assertEquals("John Doe", bookDto.getAuthor().getName());
    }

    @Test
    void shouldMapBookDtoToBook() {
        // Given
        AuthorSummaryDto authorSummaryDto = AuthorSummaryDto.builder().id(1L).name("John Doe").build();
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .title("Book 1")
                .genre("Fiction")
                .publishedYear(2020)
                .active(true)
                .author(authorSummaryDto)
                .build();

        // When
        Book book = bookMapper.toEntity(bookDto);

        // Then
        assertEquals(1L, book.getId());
        assertEquals("Book 1", book.getTitle());
        assertEquals("Fiction", book.getGenre());
        assertEquals(2020, book.getPublishedYear());
        assertTrue(book.isActive());
        assertEquals(1L, book.getAuthor().getId());
        assertEquals("John Doe", book.getAuthor().getName());
    }

    @Test
    void shouldHandleNullBook() {
        assertNull(bookMapper.toDto(null));
    }

    @Test
    void shouldHandleNullBookDto() {
        assertNull(bookMapper.toEntity(null));
    }

    @Test
    void shouldHandleBookWithNullAuthor() {
        Book book = Book.builder().id(1L).title("Book 1").genre("Fiction").publishedYear(2020).build();
        BookDto bookDto = bookMapper.toDto(book);

        assertEquals(1L, bookDto.getId());
        assertEquals("Book 1", bookDto.getTitle());
        assertEquals("Fiction", bookDto.getGenre());
        assertEquals(2020, bookDto.getPublishedYear());
        assertNull(bookDto.getAuthor());
    }
}
