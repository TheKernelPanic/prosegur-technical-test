package com.prosegur.technicaltest.technicaltest.service;

import com.prosegur.technicaltest.technicaltest.dto.ScoreDto;
import com.prosegur.technicaltest.technicaltest.exception.CustomerNotFound;
import com.prosegur.technicaltest.technicaltest.model.Customer;
import com.prosegur.technicaltest.technicaltest.model.Value;
import com.prosegur.technicaltest.technicaltest.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public ScoreDto getScore(String dni) throws CustomerNotFound {

        Optional<Customer> customerOptional = this.customerRepository.findByDni(dni);
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFound("Client requested not found");
        }
        return new ScoreDto(
          this.calculateScore(customerOptional.get().getValues())
        );
    }

    private double calculateScore(Set<Value> values) {

        double accumulated = 0;
        for (Value value: values) {
            accumulated += value.getWeight() * ((double) value.getAttribute().getWeight() / 100);
        }
        return accumulated;
    }
}
