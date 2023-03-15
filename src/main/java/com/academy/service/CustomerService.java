package com.academy.service;

import com.academy.entity.Customer;
import com.academy.repository.CustomerRepository;
import com.academy.validations.CountryCodeValidator;
import com.academy.validations.CustomerAdultValidator;
import com.academy.validations.MandatoryCustomerValuesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MandatoryCustomerValuesValidator mandatoryCustomerValuesValidator;

    @Autowired
    private CustomerAdultValidator customerAdultValidator;
    @Autowired
    private CountryCodeValidator countryCodeValidator;


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void insert(Customer customer) {
        mandatoryCustomerValuesValidator.validate(customer);
        customerAdultValidator.validate(customer);
        countryCodeValidator.validate(customer.getCountryCode());

        Customer formattedCustomer = new Customer.Builder(
                capitalizeFirstLetter(customer.getFirstName()),
                capitalizeFirstLetter(customer.getLastName()),
                formatPersonalNumber(customer))
                .age(customer.getAge())
                .countryCode(customer.getCountryCode().toUpperCase())
                .martialStatus(customer.getMaritalStatus())
                .middleName(customer.getMiddleName())
                .employer(customer.getEmployer())
                .monthlyIncome(customer.getMonthlyIncome())
                .city(customer.getCity())
                .gender(customer.getGender())
                .build();

        customerRepository.insert(formattedCustomer);
    }

    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }

    private String capitalizeFirstLetter(String string) {
        String trimmedString = string.trim();
        if (trimmedString.isEmpty()) {
            return string;
        } else {
            return StringUtils.capitalize(trimmedString);
        }
    }

    private String formatPersonalNumber(Customer customer) {
        String trimmedPersonalNumber = customer.getPersonalNumber().trim();
        if (trimmedPersonalNumber.length() < 5) {
            return trimmedPersonalNumber;
        } else {
            return trimmedPersonalNumber.substring(0, 4) + "-" + trimmedPersonalNumber.substring(4);
        }
    }
}
