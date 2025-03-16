package com.sisimpur.library.service;

import com.sisimpur.library.dto.AuthorCreateDto;
import com.sisimpur.library.dto.AuthorDto;
import com.sisimpur.library.model.Author;

public interface AuthorService extends GenericService<Author, AuthorDto, Long> {
    AuthorDto createAuthor(AuthorCreateDto authorCreateDto);
}
