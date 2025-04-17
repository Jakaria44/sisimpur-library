package com.sisimpur.library.mapper;

import com.sisimpur.library.dto.AuthorSummaryDto;
import com.sisimpur.library.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorSummaryMapper extends BaseMapper<Author, AuthorSummaryDto> {
    default AuthorSummaryDto toDto(Author author) {
        if(null == author) return null;
        return AuthorSummaryDto.builder()
                .name(author.getName())
                .bio(author.getBio())
                .id(author.getId())
                .build();
    }

    default Author toEntity(AuthorSummaryDto authorSummaryDto) {
        if(null == authorSummaryDto) return null;
        return Author.builder()
                .name(authorSummaryDto.getName())
                .bio(authorSummaryDto.getBio())
                .id(authorSummaryDto.getId())
                .build();
    }

}
