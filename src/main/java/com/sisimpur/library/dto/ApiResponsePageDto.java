package com.sisimpur.library.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ApiResponsePageDto extends ApiResponseDto{
    private String totalPages;
    private String elements;
    private String limit;
    private String page;
}
