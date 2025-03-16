package com.sisimpur.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorCreateDto {
    @NotBlank(message = "Author name cannot be blank")
    @Size(min = 3, max = 50, message = "Author name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Bio cannot exceed 500 characters")
    private String bio;

    @NotNull(message = "Active flag is required")
    private Boolean active;
}
