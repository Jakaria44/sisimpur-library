package com.sisimpur.library.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AuthorDto {
    private Long id;
    private String name;
    private boolean active;
    private String bio;
    private Set<BookSummaryDto> books;
}
