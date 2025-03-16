package com.sisimpur.library.controller;

import com.sisimpur.library.dto.ApiResponseDto;
import com.sisimpur.library.dto.ApiResponsePageDto;
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
        response.setSuccess("true");
        response.setData(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getById(@PathVariable ID id) {
        D data = getService().getById(id);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess("true");
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponsePageDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {
        Page<D> dataPage = getService().getAll(page, limit);
        ApiResponsePageDto response = new ApiResponsePageDto();
        response.setSuccess("true");
        response.setData(dataPage.getContent());
        response.setPage(String.valueOf(dataPage.getNumber()));
        response.setElements(String.valueOf(dataPage.getNumberOfElements()));
        response.setLimit(String.valueOf(dataPage.getSize()));
        response.setTotalPages(String.valueOf(dataPage.getTotalPages()));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> update(@PathVariable ID id, @RequestBody D dto) {
        D updated = getService().update(id, dto);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess("true");
        response.setData(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> delete(@PathVariable ID id) {
        getService().delete(id);
        ApiResponseDto response = new ApiResponseDto();
        response.setSuccess("true");
        response.setData("Deleted successfully");
        return ResponseEntity.ok(response);
    }
}
