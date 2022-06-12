package com.sudarshan.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sudarshan.app.model.Customer;

/**
 * repository for customer
 * @author sudarshanpradhan
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByEmail(String email);
}
