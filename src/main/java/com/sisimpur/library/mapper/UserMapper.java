package com.sisimpur.library.mapper;

import com.sisimpur.library.dto.UserDto;
import com.sisimpur.library.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface UserMapper extends BaseMapper<User, UserDto> {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
