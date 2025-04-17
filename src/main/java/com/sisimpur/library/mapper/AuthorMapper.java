package com.sisimpur.library.mapper;

import com.sisimpur.library.dto.AuthorCreateDto;
import com.sisimpur.library.dto.AuthorDto;
import com.sisimpur.library.dto.BookSummaryDto;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {BookSummaryMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthorMapper extends BaseMapper<Author, AuthorDto> {
    AuthorDto toDto(Author author);

    Author toEntity(AuthorDto authorDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Author authorCreateDtoToAuthor(AuthorCreateDto authorCreateDto);

    Set<BookSummaryDto> mapBooks(Set<Book> books);
}
