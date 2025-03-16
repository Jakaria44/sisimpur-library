package com.sisimpur.library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookCreateDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Genre is required")
    private String genre;

    @Min(value = 0, message = "Published year must be positive")
    private int publishedYear;

    @NotNull(message = "Active flag is required")
    private Boolean active;

    @NotNull(message = "Author id is required")
    private Long authorId;
}
