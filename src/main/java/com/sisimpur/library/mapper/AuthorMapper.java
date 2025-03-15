package com.sisimpur.library.mapper;

import com.sisimpur.library.dto.AuthorDto;
import com.sisimpur.library.dto.BookSummaryDto;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(uses = {BookSummaryMapper.class})
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);


    AuthorDto toDto(Author author);

    Author toEntity(AuthorDto authorDto);

    Set<BookSummaryDto> mapBooks(Set<Book> books);
}
