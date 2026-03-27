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
public class DepartmentDTO {
    private Long id;

    @NotBlank(message = "title of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "Number of character in title should be in the range 3-10")
    private String title;


    @AssertTrue(message = "Department should be active")
    @JsonProperty("isActive")
    private Boolean isActive;


    @PastOrPresent(message = "Date of creation should be past or present")
    private LocalDate createdAt;
}
