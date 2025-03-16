package com.sisimpur.library.controller;

import com.sisimpur.library.dto.ApiResponseDto;
import com.sisimpur.library.service.GenericService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public abstract class AbstractCrudController<D, ID> {

    protected abstract GenericService<?, D, ID> getService();

    @PostMapping
    public ResponseEntity<ApiResponseDto> create(@Valid  @RequestBody D dto) {
        D created = getService().create(dto);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(true);
        response.setData(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getById(@PathVariable ID id) {
        D data = getService().getById(id);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(true);
        response.setData(data);
        return ResponseEntity.ok(response);
    }



    @GetMapping
    public ResponseEntity<Page<D>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "searchBy", required = false) String searchBy,
            @RequestParam(value = "searchVal", required = false) String searchVal,
            @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "DESC", required = false) String sortDirection,
            @RequestParam(value = "isActive", defaultValue = "true", required = false) String isActive) {

        if (searchBy != null && searchVal == null) {
            throw new IllegalArgumentException("searchVal must be provided when searchBy is present.");
        }

        return ResponseEntity.ok(getService().getAll(pageSize, pageNumber, searchBy, searchVal, sortBy, sortDirection, isActive));

    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> update(@PathVariable ID id, @RequestBody D dto) {
        D updated = getService().update(id, dto);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(true);
        response.setData(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> delete(@PathVariable ID id) {
        getService().delete(id);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess(true);
        response.setData("Deleted successfully");
        return ResponseEntity.ok(response);
    }
}
