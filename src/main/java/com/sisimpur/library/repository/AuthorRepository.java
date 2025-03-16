package com.sisimpur.library.repository;

import com.sisimpur.library.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends SearchableRepository<Author, Long> {

    @Override
    @Query(value = """
             SELECT e FROM Author e \
             WHERE ((:searchBy = 'id' AND e.id = :searchVal) OR \
             (:searchBy = 'name' AND e.name LIKE %:searchVal%) OR \
             (:searchBy = 'bio' AND e.bio LIKE %:searchVal%)) \
             AND (:isActive IS NULL OR e.active = :isActive ) \
            """)
    Page<Author> findAllBySearchValAndSearchByAndActive(
            @Param("searchVal") String searchVal,
            @Param("searchBy") String searchBy,
            @Param("isActive") Boolean isActive,
            PageRequest pageRequest);
}
