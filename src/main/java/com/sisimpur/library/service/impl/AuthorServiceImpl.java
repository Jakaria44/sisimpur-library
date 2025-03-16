package com.sisimpur.library.service.impl;

import com.sisimpur.library.dto.AuthorCreateDto;
import com.sisimpur.library.dto.AuthorDto;
import com.sisimpur.library.mapper.AuthorMapper;
import com.sisimpur.library.mapper.BaseMapper;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.repository.AuthorRepository;
import com.sisimpur.library.repository.SearchableRepository;
import com.sisimpur.library.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends AbstractCrudService<Author, AuthorDto, Long> implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    protected SearchableRepository<Author, Long> getRepository() {
        return authorRepository;
    }

    @Override
    protected BaseMapper<Author, AuthorDto> getMapper() {
        return authorMapper;
    }

    @Override
    public AuthorDto createAuthor(AuthorCreateDto authorCreateDto) {
        Author authorEntity = authorMapper.authorCreateDtoToAuthor(authorCreateDto);
        Author savedAuthor = authorRepository.save(authorEntity);
        return authorMapper.toDto(savedAuthor);
    }
}
