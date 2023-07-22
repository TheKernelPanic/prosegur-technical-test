package com.prosegur.technicaltest.technicaltest.repository;

import com.prosegur.technicaltest.technicaltest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByDni(String dni);
}
