package com.sisimpur.library.repository;

import com.sisimpur.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends SearchableRepository<Book, Long> {

    @Override
    @Query(value= "SELECT b FROM Book b WHERE " +
            "((:searchBy = 'title' AND LOWER(b.title) LIKE LOWER(CONCAT('%', :searchVal, '%'))) OR " +
            "(:searchBy = 'genre' AND LOWER(b.genre) LIKE LOWER(CONCAT('%', :searchVal, '%'))) OR " +
            "(:searchBy = 'publishedYear' AND STR(b.publishedYear) = :searchVal)) " +
            "AND (:isActive IS NULL OR b.active = :isActive)")
    Page<Book> findAllBySearchValAndSearchByAndActive(
            @Param("searchVal") String searchVal,
            @Param("searchBy") String searchBy,
            @Param("isActive") Boolean isActive,
            PageRequest pageable);
}
