package com.angel.jpa.security.repository;

import com.angel.jpa.security.domain.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
