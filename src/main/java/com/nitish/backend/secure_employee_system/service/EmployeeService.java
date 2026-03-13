package com.nitish.backend.secure_employee_system.service;

import com.nitish.backend.secure_employee_system.dto.EmployeeRequestDTO;
import com.nitish.backend.secure_employee_system.dto.EmployeeResponseDTO;
import com.nitish.backend.secure_employee_system.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

    List<EmployeeResponseDTO> getAllEmployees(int page, int size);

    EmployeeResponseDTO getEmployeeById(Long id);

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto);

    void deleteEmployee(Long id);

    EmployeeResponseDTO findByEmail(String email);

    List<EmployeeResponseDTO> findByDepartment(String department);
}
