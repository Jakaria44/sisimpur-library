package com.sisimpur.library.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String genre;
    private int publishedYear;
    private boolean active;
    private AuthorSummaryDto author;
}
