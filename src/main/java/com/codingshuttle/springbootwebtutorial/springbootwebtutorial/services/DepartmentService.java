package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    public final DepartmentRepository departmentRepository;
    public final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class))
                .toList();
    }

    public Optional<DepartmentDTO> getDepartmentById(long id) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(id);
        return departmentEntity.map(x -> modelMapper.map(x, DepartmentDTO.class));

    }

    public DepartmentDTO createNewDepartment(@Valid DepartmentDTO inputDepartment) {
        DepartmentEntity toSaveDepartment = modelMapper.map(inputDepartment,DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(toSaveDepartment);
        return modelMapper.map(savedDepartment,DepartmentDTO.class);
    }

    public boolean deleteDepartmentById(long id) {
        isExistsByDepartmentId(id);
        departmentRepository.deleteById(id);
        return true;
    }

    public ResponseEntity<Boolean> deleteAllDepartments() {
        departmentRepository.deleteAll();
        return ResponseEntity.ok(true);
    }

    public void isExistsByDepartmentId(Long departmentId){
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists) throw new ResourceNotFoundException("Department not found with id: "+departmentId);
    }
}
