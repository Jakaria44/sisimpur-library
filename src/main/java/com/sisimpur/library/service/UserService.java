package com.sisimpur.library.service;

import com.sisimpur.library.dto.UserCreateDto;
import com.sisimpur.library.dto.UserDto;
import com.sisimpur.library.model.User;

public interface UserService extends GenericService<User, UserDto, Long> {
    UserDto createUser(UserCreateDto userCreateDto);
}
