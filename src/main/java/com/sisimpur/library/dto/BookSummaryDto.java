package com.sisimpur.library.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookSummaryDto {
    private Long id;
    private String title;
    private String genre;
    private int publishedYear;
    private String authorName;
}
