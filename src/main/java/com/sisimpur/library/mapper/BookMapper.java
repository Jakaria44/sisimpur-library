package com.sisimpur.library.mapper;

import com.sisimpur.library.dto.BookDto;
import com.sisimpur.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AuthorSummaryMapper.class})
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(Book book);

    @Mapping(target="users", ignore=true)
    Book toEntity(BookDto bookDto);

}
