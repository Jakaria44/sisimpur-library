package com.sisimpur.library.exception;

import com.sisimpur.library.dto.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(false);
        response.setError(ex.getMessage());
        log.error("ResourceNotFoundException: {}", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class})
    public ResponseEntity<ApiResponseDto> handleDataIntegrityViolation(Exception ex) {
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(false);
        response.setError("Data integrity violation: " + ex.getMessage());
        log.error("Data integrity violation: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponseDto> handleServiceException(ServiceException ex) {
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(false);
        response.setError(ex.getMessage());
        log.error("ServiceException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto> handleGeneralException(Exception ex) {
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(false);
        response.setError("An unexpected error occurred: " + ex.getMessage());
        log.error("Unhandled Exception: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
