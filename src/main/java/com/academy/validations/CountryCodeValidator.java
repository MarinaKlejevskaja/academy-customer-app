package com.academy.validations;

import com.academy.entity.CountryCode;
import com.academy.entity.Customer;
import com.academy.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CountryCodeValidator extends Validator<Customer> {

    @Override
    public void validate(Customer customer) {
        if (!customer.getCountryCode().isEmpty()) {
            try {
                CountryCode.valueOf(customer.getCountryCode().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new ValidationException("Country code: %s, is not valid".formatted(customer.getCountryCode()));
            }
        }
    }
}