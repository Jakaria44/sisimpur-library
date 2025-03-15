package com.sisimpur.library.mappers;

import com.sisimpur.library.dto.AuthorDto;
import com.sisimpur.library.dto.BookSummaryDto;
import com.sisimpur.library.mapper.AuthorMapper;
import com.sisimpur.library.mapper.AuthorMapperImpl;
import com.sisimpur.library.mapper.BookSummaryMapper;
import com.sisimpur.library.mapper.BookSummaryMapperImpl;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.Book;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorMapperTest {

    private final AuthorMapper authorMapper = new AuthorMapperImpl();
    private final BookSummaryMapper bookSummaryMapper = new BookSummaryMapperImpl();

    @Test
    void shouldMapAuthorToAuthorDto() {
        // Given
        Author author = Author.builder()
                .id(1L)
                .name("John Doe")
                .bio("Author bio")
                .active(true)
                .build();

        Book book1 = Book.builder().id(1L).title("Book 1").genre("Fiction").publishedYear(2020).author(author).build();
        Book book2 = Book.builder().id(2L).title("Book 2").genre("Non-fiction").publishedYear(2021).author(author).build();

        Set<Book> books = new HashSet<>();
        books.add(book1);
        books.add(book2);
        author.setBooks(books);

        // When
        AuthorDto authorDto = authorMapper.toDto(author);

        // Then
        assertEquals(1L, authorDto.getId());
        assertEquals("John Doe", authorDto.getName());
        assertEquals("Author bio", authorDto.getBio());
        assertTrue(authorDto.isActive());
        assertEquals(2, authorDto.getBooks().size());

        for (BookSummaryDto bookDto : authorDto.getBooks()) {
            assertTrue(bookDto.getId() == 1L || bookDto.getId() == 2L);
            assertTrue(bookDto.getTitle().equals("Book 1") || bookDto.getTitle().equals("Book 2"));
            assertEquals("John Doe", bookDto.getAuthorName());
        }
    }

    @Test
    void shouldMapAuthorDtoToAuthor() {
        // Given
        BookSummaryDto bookDto1 = BookSummaryDto.builder()
                .id(1L)
                .title("Book 1")
                .authorName("John Doe")
                .build();

        BookSummaryDto bookDto2 = BookSummaryDto.builder()
                .id(2L)
                .title("Book 2")
                .authorName("John Doe")
                .build();

        Set<BookSummaryDto> bookDtos = new HashSet<>();
        bookDtos.add(bookDto1);
        bookDtos.add(bookDto2);

        AuthorDto authorDto = AuthorDto.builder()
                .id(1L)
                .name("John Doe")
                .active(true)
                .books(bookDtos)
                .build();

        // When
        Author author = authorMapper.toEntity(authorDto);

        // Then
        assertEquals(1L, author.getId());
        assertEquals("John Doe", author.getName());
        assertTrue(author.isActive());
        assertEquals(2, author.getBooks().size());

        for (Book book : author.getBooks()) {
            assertTrue(book.getId() == 1L || book.getId() == 2L);
            assertTrue(book.getTitle().equals("Book 1") || book.getTitle().equals("Book 2"));
        }
    }

    @Test
    void shouldHandleNullAuthor() {
        assertNull(authorMapper.toDto(null));
    }

    @Test
    void shouldHandleNullAuthorDto() {
        assertNull(authorMapper.toEntity(null));
    }

    @Test
    void shouldHandleAuthorWithNullBooks() {
        Author author = Author.builder().id(1L).name("John Doe").active(true).build();
        AuthorDto authorDto = authorMapper.toDto(author);

        assertEquals(1L, authorDto.getId());
        assertEquals("John Doe", authorDto.getName());
        assertTrue(authorDto.isActive());
        assertNull(authorDto.getBooks());
    }
}
