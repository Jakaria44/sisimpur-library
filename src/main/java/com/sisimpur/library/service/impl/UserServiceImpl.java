package com.sisimpur.library.service.impl;

import com.sisimpur.library.dto.UserCreateDto;
import com.sisimpur.library.dto.UserDto;
import com.sisimpur.library.mapper.BaseMapper;
import com.sisimpur.library.mapper.UserMapper;
import com.sisimpur.library.model.User;
import com.sisimpur.library.repository.SearchableRepository;
import com.sisimpur.library.repository.UserRepository;
import com.sisimpur.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractCrudService<User, UserDto, Long> implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    protected SearchableRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    protected BaseMapper<User, UserDto> getMapper() {
        return userMapper;
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        try {
            User userEntity = userMapper.userCreateDtoToUser(userCreateDto);
            User savedUser = userRepository.save(userEntity);
            return userMapper.toDto(savedUser);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
