package com.sisimpur.library.mapper;

import com.sisimpur.library.dto.UserDto;
import com.sisimpur.library.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
