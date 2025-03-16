package com.sisimpur.library.repository;

import com.sisimpur.library.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends SearchableRepository<User, Long> {
    @Override
    @Query(value = "SELECT u FROM User u WHERE " +
            "((:searchBy = 'name' AND LOWER(u.name) LIKE LOWER(CONCAT('%', :searchVal, '%'))) OR " +
            "(:searchBy = 'id' AND LOWER(u.id) LIKE LOWER(CONCAT('%', :searchVal, '%'))) OR " +
            "(:searchBy = 'email' AND STR(u.email) = :searchVal)) " +
            "AND (:isActive IS NULL OR u.active = :isActive)")
    Page<User> findAllBySearchValAndSearchByAndActive(
            @Param("searchVal") String searchVal,
            @Param("searchBy") String searchBy,
            @Param("isActive") Boolean isActive,
            PageRequest pageable);
}
