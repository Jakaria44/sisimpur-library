package com.sisimpur.library.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private boolean active;
    private Set<BookSummaryDto> books;
}
