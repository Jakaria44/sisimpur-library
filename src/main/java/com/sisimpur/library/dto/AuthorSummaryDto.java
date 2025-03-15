package com.sisimpur.library.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorSummaryDto {
    private Long id;
    private String name;
    private String bio;
}
