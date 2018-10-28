package com.angel.jpa.repository;

import com.angel.jpa.domain.Instrument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends CrudRepository<Instrument, Long> {
}
