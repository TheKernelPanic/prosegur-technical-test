package com.prosegur.technicaltest.technicaltest.repository;

import com.prosegur.technicaltest.technicaltest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
