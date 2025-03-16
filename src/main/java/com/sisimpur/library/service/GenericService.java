package com.sisimpur.library.service;


import org.springframework.data.domain.Page;

public interface GenericService<E, D, ID> {
    D create(D dto);
    D getById(ID id);
    Page<D> getAll(int page, int limit);
    D update(ID id, D dto);
    void delete(ID id);
}
