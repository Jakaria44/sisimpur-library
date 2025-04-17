package com.sisimpur.library.mapper;

import com.sisimpur.library.dto.BookDto;
import com.sisimpur.library.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {AuthorSummaryMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper extends BaseMapper<Book, BookDto> {

    BookDto toDto(Book book);

    @Mapping(target="users", ignore=true)
    Book toEntity(BookDto bookDto);

    @Mapping(target="id", ignore=true)
    @Mapping(target="users", ignore=true)
    Book bookCreateDtoToBook(BookDto bookDto);
}
