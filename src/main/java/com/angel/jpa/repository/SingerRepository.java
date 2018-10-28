package com.angel.jpa.repository;

import com.angel.jpa.domain.Singer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends CrudRepository<Singer, Long> {
}
