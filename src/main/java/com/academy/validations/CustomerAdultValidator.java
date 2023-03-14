package com.academy.validations;

import com.academy.entity.Customer;
import com.academy.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CustomerAdultValidator extends Validator<Customer> {

    @Override
    public void validate(Customer customer) {
        if (customer.getAge() < 18) {
            throw new ValidationException("Customer is not adult, age is: " + customer.getAge());
        }
    }
}