package com.prosegur.technicaltest.technicaltest.service;

import com.prosegur.technicaltest.technicaltest.dto.HighestScoreDto;
import com.prosegur.technicaltest.technicaltest.dto.ScoreDto;
import com.prosegur.technicaltest.technicaltest.exception.CustomerNotFound;
import com.prosegur.technicaltest.technicaltest.exception.OriEntityHasNoCustomers;
import com.prosegur.technicaltest.technicaltest.model.Customer;
import com.prosegur.technicaltest.technicaltest.model.Value;
import com.prosegur.technicaltest.technicaltest.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
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
            throw new CustomerNotFound();
        }
        double score = this.calculateScore(customerOptional.get().getValues());

        /**
         * Según el documento de requerimientos, el formato que aparece en la tabla de resultados experados
         * muestra cifras numéricas con formato establecido, el uso de la coma como delimitador de decimales
         * y la omisión de los decimales en caso de que estos sean 0, es posible que se aplica un formato
         * y el tratamiento del dato como cadena en la solicitud HTTP.
         */
        return new ScoreDto(
          new DecimalFormat("#,##0.#").format(score)
        );
    }

    @Override
    public HighestScoreDto getHighestScore(String oriEntity) throws OriEntityHasNoCustomers {

        List<Customer> customers =  this.customerRepository.findAllByOriEntity(oriEntity);
        if (customers.isEmpty()) {
            throw new OriEntityHasNoCustomers();
        }

        /**
         * Realizamos el cálculo con el primer elemento de la lista.
         * Eliminamos el primer elemento e iteramos el resto de la lista.
         *
         * Posible problemática: Pueden existir dos o más clientes con similar puntuación.
         */
        double highest = this.calculateScore(customers.get(0).getValues());
        HighestScoreDto highestScoreDto = new HighestScoreDto(customers.get(0).getDni());
        customers.remove(0);

        for (Customer customer: customers) {
            double calculated = this.calculateScore(customer.getValues());
            if (calculated > highest) {
                highest = calculated;
                highestScoreDto = new HighestScoreDto(customer.getDni());
            }
        }
        return highestScoreDto;
    }

    /**
     * Cálculo de la puntuación en base a la fórmula del requerimiento.
     */
    private double calculateScore(Set<Value> values) {

        double accumulated = 0;
        for (Value value: values) {
            accumulated += value.getWeight() * ((double) value.getAttribute().getWeight() / 100);
        }
        return accumulated;
    }
}
