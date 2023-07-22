package com.prosegur.technicaltest.technicaltest.repository;

import com.prosegur.technicaltest.technicaltest.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueRepository extends JpaRepository<Value, Long> {
}
