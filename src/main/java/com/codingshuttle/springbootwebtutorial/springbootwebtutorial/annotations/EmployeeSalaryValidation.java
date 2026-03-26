package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeSalaryValidator.class})
public @interface EmployeeSalaryValidation {

    String message() default "Salary of Employee should be an EVEN number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
