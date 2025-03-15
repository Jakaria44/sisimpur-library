package com.sisimpur.library.mappers;

import com.sisimpur.library.dto.AuthorSummaryDto;
import com.sisimpur.library.mapper.AuthorSummaryMapper;
import com.sisimpur.library.mapper.AuthorSummaryMapperImpl;
import com.sisimpur.library.model.Author;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthorSummaryMapperTest {
    private final AuthorSummaryMapper authorSummaryMapper = new
            AuthorSummaryMapperImpl();

    @Test
    void shouldMapAuthorToAuthorSummaryDto() {
        // Given
        Author author = Author.builder()
                .id(1L)
                .name("John Doe")
                .bio("Biography")
                .active(true)
                .build();

        // When
        AuthorSummaryDto authorSummaryDto = authorSummaryMapper.toDto(author);

        // Then
        assertEquals(1L, authorSummaryDto.getId());
        assertEquals("John Doe", authorSummaryDto.getName());
    }

    @Test
    void shouldMapAuthorSummaryDtoToAuthor() {
        // Given
        AuthorSummaryDto authorSummaryDto = AuthorSummaryDto.builder()
                .id(1L)
                .name("John Doe")
                .build();

        // When
        Author author = authorSummaryMapper.toEntity(authorSummaryDto);

        // Then
        assertEquals(1L, author.getId());
        assertEquals("John Doe", author.getName());
        // Other fields should be null or default
        assertNull(author.getBooks());
    }

    @Test
    void shouldHandleNullAuthor() {
        assertNull(authorSummaryMapper.toDto(null));
    }

    @Test
    void shouldHandleNullAuthorSummaryDto() {
        assertNull(authorSummaryMapper.toEntity(null));
    }
}
