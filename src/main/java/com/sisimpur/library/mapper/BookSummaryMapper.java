package com.sisimpur.library.mapper;

import com.sisimpur.library.dto.BookSummaryDto;
import com.sisimpur.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookSummaryMapper extends BaseMapper<Book, BookSummaryDto> {
    BookSummaryMapper INSTANCE = Mappers.getMapper(BookSummaryMapper.class);

    @Mapping(target = "authorName", source = "author.name")
    BookSummaryDto toDto(Book book);

    Book toEntity(BookSummaryDto bookSummaryDto);
}
