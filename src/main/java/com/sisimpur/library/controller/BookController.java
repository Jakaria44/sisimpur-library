package com.sisimpur.library.controller;

import com.sisimpur.library.dto.ApiResponseDto;
import com.sisimpur.library.dto.BookCreateDto;
import com.sisimpur.library.dto.BookDto;
import com.sisimpur.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController extends AbstractCrudController<BookDto, Long> {

    private final BookService bookService;

    @Override
    protected BookService getService() {
        return bookService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> create(@Valid @RequestBody BookCreateDto bookCreateDto) {
        BookDto created = bookService.createBook(bookCreateDto);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(true);
        response.setData(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{bookId}/assign/{userId}")
    public ResponseEntity<ApiResponseDto> assignBookToUser(@PathVariable Long bookId, @PathVariable Long userId) {
        BookDto bookDto = bookService.assignBookToUser(bookId, userId);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(true);
        response.setData(bookDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{bookId}/unassign/{userId}")
    public ResponseEntity<ApiResponseDto> unassignBookFromUser(@PathVariable Long bookId, @PathVariable Long userId) {
        BookDto bookDto = bookService.unassignBookFromUser(bookId, userId);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(true);
        response.setData(bookDto);
        return ResponseEntity.ok(response);
    }
}
