package com.academy.validations;

import com.academy.entity.CountryCode;
import com.academy.entity.Customer;
import com.academy.exception.CustomerNotAdultException;
import com.academy.exception.InvalidCountryCodeException;
import com.academy.exception.MandatoryValueMissingException;
import org.springframework.stereotype.Component;

@Component
abstract class Validator<T> {

    public abstract void validate(T attribute);
}
