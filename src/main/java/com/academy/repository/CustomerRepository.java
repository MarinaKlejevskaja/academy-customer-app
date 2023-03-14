package com.academy.repository;

import com.academy.entity.Customer;

import java.util.List;

public interface CustomerRepository {

    public List<Customer> findAll();

    public void insert(Customer customer);

    public void deleteById(String customerId);
}
