package com.sisimpur.library.mappers;


import com.sisimpur.library.dto.BookSummaryDto;
import com.sisimpur.library.mapper.BookSummaryMapper;
import com.sisimpur.library.mapper.BookSummaryMapperImpl;
import com.sisimpur.library.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
public class BookSummaryMapperTest {

    private final BookSummaryMapper bookSummaryMapper = new BookSummaryMapperImpl();

    @Test
    void shouldMapBookToBookSummaryDto() {
        // Given
        Book book = Book.builder()
                .id(1L)
                .title("Book 1")
                .genre("Fiction")
                .publishedYear(2020)
                .build();

        // When
        BookSummaryDto bookSummaryDto = bookSummaryMapper.toDto(book);

        // Then
        assertEquals(1L, bookSummaryDto.getId());
        assertEquals("Book 1", bookSummaryDto.getTitle());
    }

    @Test
    void shouldMapBookSummaryDtoToBook() {
        // Given
        BookSummaryDto bookSummaryDto = BookSummaryDto.builder()
                .id(1L)
                .title("Book 1")
                .build();

        // When
        Book book = bookSummaryMapper.toEntity(bookSummaryDto);

        // Then
        assertEquals(1L, book.getId());
        assertEquals("Book 1", book.getTitle());
        assertNull(book.getGenre());
        assertEquals(0, book.getPublishedYear());
        assertNull(book.getAuthor());
    }

    @Test
    void shouldHandleNullBook() {
        assertNull(bookSummaryMapper.toDto(null));
    }

    @Test
    void shouldHandleNullBookSummaryDto() {
        assertNull(bookSummaryMapper.toEntity(null));
    }
}
