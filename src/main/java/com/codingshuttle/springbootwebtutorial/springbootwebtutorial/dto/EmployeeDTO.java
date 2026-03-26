package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidation;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeSalaryValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "name of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "Number of character in name should be in the range 3-10")
    private String name;

    @NotBlank(message = "email of the employee cannot be blank")
    @Email(message = "Provide a Valid Email")
    private String email;

    @NotNull(message = "age of the employee cannot be null")
    @Max(value = 80, message = "Age of Employee cannot be more than 80")
    @Min(value = 18, message = "Age of Employee cannot be less than 18")
    private Integer age;

    @NotBlank(message = "Role of the employee cannot be blank")
    //@Pattern(regexp = "^(ADMIN|USER)$", message = "Role should be ADMIN or USER")
    @EmployeeRoleValidation // Here we use our custom annotation
    private String role;


    @NotNull(message = "Salary of the employee cannot be null")
    @Positive(message = "Salary of the employee should be positive")
    @Digits(integer = 6,fraction = 2, message = "The salary can be in the form XXXXXX.XX")
    @EmployeeSalaryValidation
    private Double salary;

    @PastOrPresent(message = "Date of joining should be past or present")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employees should be active")
    @JsonProperty("isActive")
    private Boolean isActive;
}
