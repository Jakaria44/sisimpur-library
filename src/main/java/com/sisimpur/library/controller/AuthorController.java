package com.sisimpur.library.controller;

import com.sisimpur.library.dto.ApiResponseDto;
import com.sisimpur.library.dto.AuthorCreateDto;
import com.sisimpur.library.dto.AuthorDto;
import com.sisimpur.library.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authors")
@RequiredArgsConstructor
public class AuthorController extends AbstractCrudController<AuthorDto, Long> {

    private final AuthorService authorService;

    @Override
    protected AuthorService getService() {
        return authorService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> create(@Valid @RequestBody AuthorCreateDto authorCreateDto) {
        AuthorDto created = authorService.createAuthor(authorCreateDto);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(true);
        response.setData(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
