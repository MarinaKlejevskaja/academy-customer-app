package com.academy.validations;

import org.springframework.stereotype.Component;

@Component
abstract class Validator<T> {

    public abstract void validate(T attribute);
}
