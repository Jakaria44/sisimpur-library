package com.sisimpur.library.controller;

import com.sisimpur.library.dto.ApiResponseDto;
import com.sisimpur.library.dto.UserCreateDto;
import com.sisimpur.library.dto.UserDto;
import com.sisimpur.library.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController extends AbstractCrudController<UserDto, Long> {

    private final UserService userService;

    @Override
    protected UserService getService() {
        return userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> create(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserDto created = userService.createUser(userCreateDto);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(true);
        response.setData(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
