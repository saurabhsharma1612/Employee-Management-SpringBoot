package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeID") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not Found with id: "+id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        //return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        return ResponseEntity.created(URI.create("/employees/" + savedEmployee.getId())).body(savedEmployee);
    }

    @PutMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,
                                                          @PathVariable Long employeeID){
        return ResponseEntity
                .ok(employeeService.updateEmployeeById(employeeID, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeID}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long employeeID){
        //return ResponseEntity.ok(employeeService.deleteEmployeeById(employeeID));
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeID);

        if(gotDeleted) return ResponseEntity.ok("Employee "+ employeeID + " deleted");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public boolean deleteAllEmployees(){
        return employeeService.deleteAllEmployees();
    }

    @PatchMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeID,
                                         @RequestBody Map<String, Object> updates){
        EmployeeDTO employeeDTO = employeeService.patchEmployeeById(employeeID, updates);
        if (employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
