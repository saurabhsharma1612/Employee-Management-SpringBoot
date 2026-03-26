package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeSalaryValidator implements ConstraintValidator<EmployeeSalaryValidation,Double> {


    @Override
    public boolean isValid(Double salary, ConstraintValidatorContext constraintValidatorContext) {
        return (salary%2 == 0);
    }
}
