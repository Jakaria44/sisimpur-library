package com.sisimpur.library.repository;

import com.sisimpur.library.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends SearchableRepository<User, Long> {
}
