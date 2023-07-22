package com.prosegur.technicaltest.technicaltest.repository;

import com.prosegur.technicaltest.technicaltest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
