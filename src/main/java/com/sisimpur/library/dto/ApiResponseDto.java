package com.sisimpur.library.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ApiResponseDto {

    private String success;
    private String error;
    private Object data;
}
