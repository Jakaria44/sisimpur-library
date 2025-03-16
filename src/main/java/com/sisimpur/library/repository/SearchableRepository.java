package com.sisimpur.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SearchableRepository<T, ID> extends JpaRepository<T, ID> {

    @Query("SELECT e FROM #{#entityName} e WHERE " +
            "(:searchBy = 'id' AND e.id = :searchVal) " +
            "AND (:isActive IS NULL OR e.isActive = :isActive)")
    Page<T> findAllBySearchValAndSearchByAndActive(
            @Param("searchVal") String searchVal,
            @Param("searchBy") String searchBy,
            @Param("isActive") Boolean isActive,
            PageRequest pageRequest);

    @Query("SELECT e FROM #{#entityName} e WHERE e.active = :isActive")
    Page<T> findAllByIsActive(@Param("isActive") Boolean isActive, PageRequest pageRequest);
}
