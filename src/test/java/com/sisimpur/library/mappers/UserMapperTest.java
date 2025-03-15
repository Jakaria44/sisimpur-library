package com.sisimpur.library.mappers;

import com.sisimpur.library.dto.BookSummaryDto;
import com.sisimpur.library.dto.UserDto;
import com.sisimpur.library.mapper.UserMapper;
import com.sisimpur.library.mapper.UserMapperImpl;
import com.sisimpur.library.model.Book;
import com.sisimpur.library.model.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class UserMapperTest {

    private final UserMapper userMapper = new UserMapperImpl();

    @Test
    void shouldMapUserToUserDto() {
        // Given
        Book book1 = Book.builder().id(1L).title("Book 1").genre("Fiction").publishedYear(2020).build();
        Book book2 = Book.builder().id(2L).title("Book 2").genre("Non-fiction").publishedYear(2021).build();
        Set<Book> books = new HashSet<>();
        books.add(book1);
        books.add(book2);

        User user = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john@example.com")
                .active(true)
                .books(books)
                .build();

        // When
        UserDto userDto = userMapper.toDto(user);

        // Then
        assertEquals(1L, userDto.getId());
        assertEquals("John Doe", userDto.getName());
        assertEquals("john@example.com", userDto.getEmail());
        assertTrue(userDto.isActive());
        assertEquals(2, userDto.getBooks().size());

        for (BookSummaryDto bookDto : userDto.getBooks()) {
            assertTrue(bookDto.getId() == 1L || bookDto.getId() == 2L);
            assertTrue(bookDto.getTitle().equals("Book 1") || bookDto.getTitle().equals("Book 2"));
        }
    }

    @Test
    void shouldMapUserDtoToUser() {
        // Given
        BookSummaryDto bookDto1 = BookSummaryDto.builder().id(1L).title("Book 1").build();
        BookSummaryDto bookDto2 = BookSummaryDto.builder().id(2L).title("Book 2").build();
        Set<BookSummaryDto> bookDtos = new HashSet<>();
        bookDtos.add(bookDto1);
        bookDtos.add(bookDto2);

        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("John Doe")
                .email("john@example.com")
                .active(true)
                .books(bookDtos)
                .build();

        // When
        User user = userMapper.toEntity(userDto);

        // Then
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertTrue(user.isActive());
        assertEquals(2, user.getBooks().size());

        for (Book book : user.getBooks()) {
            assertTrue(book.getId() == 1L || book.getId() == 2L);
            assertTrue(book.getTitle().equals("Book 1") || book.getTitle().equals("Book 2"));
        }
    }

    @Test
    void shouldHandleNullUser() {
        assertNull(userMapper.toDto(null));
    }

    @Test
    void shouldHandleNullUserDto() {
        assertNull(userMapper.toEntity(null));
    }

    @Test
    void shouldHandleUserWithNullBooks() {
        User user = User.builder().id(1L).name("John Doe").email("john@example.com").active(true).build();
        UserDto userDto = userMapper.toDto(user);

        assertEquals(1L, userDto.getId());
        assertEquals("John Doe", userDto.getName());
        assertEquals("john@example.com", userDto.getEmail());
        assertTrue(userDto.isActive());
        assertNull(userDto.getBooks());
    }
}
