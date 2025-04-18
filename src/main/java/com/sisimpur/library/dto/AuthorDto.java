package com.sisimpur.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AuthorDto {
    private Long id;

    @NotBlank(message = "Author name cannot be blank")
    @Size(min = 3, max = 50, message = "Author name must be between 3 and 50 characters")
    private String name;

    private boolean active;

    @Size(max = 500, message = "Bio cannot exceed 500 characters")
    private String bio;

    private Set<BookSummaryDto> books;
}
