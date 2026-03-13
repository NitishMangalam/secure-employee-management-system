package com.nitish.backend.secure_employee_system.service.impl;

import com.nitish.backend.secure_employee_system.dto.EmployeeRequestDTO;
import com.nitish.backend.secure_employee_system.dto.EmployeeResponseDTO;
import com.nitish.backend.secure_employee_system.entity.Employee;
import com.nitish.backend.secure_employee_system.exception.ResourceNotFoundException;
import com.nitish.backend.secure_employee_system.repository.EmployeeRepository;
import com.nitish.backend.secure_employee_system.service.EmployeeService;
import com.nitish.backend.secure_employee_system.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {

        logger.info("Creating employee with email: {}", dto.getEmail());

        Employee employee = MapperUtil.toEntity(dto);
        Employee savedEmployee = employeeRepository.save(employee);

        logger.info("Employee created successfully with id: {}", savedEmployee.getId());

        return MapperUtil.toDTO(savedEmployee);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees(int page, int size) {

        logger.info("Fetching employees with pagination - page: {}, size: {}", page, size);

        Pageable pageable = PageRequest.of(page, size);

        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        List<Employee> employees = employeePage.getContent();

        logger.info("Total employees fetched in this page: {}", employees.size());

        return employees
                .stream()
                .map(MapperUtil::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {

        logger.info("Fetching employee with id: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee not found with id: {}", id);
                    return new ResourceNotFoundException("Employee not found");
                });

        return MapperUtil.toDTO(employee);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {

        logger.info("Updating employee with id: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee not found for update with id: {}", id);
                    return new ResourceNotFoundException("Employee not found");
                });

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        employee.setJoiningDate(dto.getJoiningDate());
        employee.setStatus(dto.getStatus());

        Employee updatedEmployee = employeeRepository.save(employee);

        logger.info("Employee updated successfully with id: {}", updatedEmployee.getId());

        return MapperUtil.toDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        logger.info("Deleting employee with id: {}", id);

        if (!employeeRepository.existsById(id)) {
            logger.error("Employee not found for deletion with id: {}", id);
            throw new ResourceNotFoundException("Employee not found");
        }

        employeeRepository.deleteById(id);

        logger.info("Employee deleted successfully with id: {}", id);
    }

    @Override
    public EmployeeResponseDTO findByEmail(String email) {

        logger.info("Searching employee by email: {}", email);

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("Employee not found with email: {}", email);
                    return new RuntimeException("Employee not found");
                });

        return MapperUtil.toDTO(employee);
    }

    @Override
    public List<EmployeeResponseDTO> findByDepartment(String department) {

        logger.info("Fetching employees from department: {}", department);

        List<Employee> employees = employeeRepository.findByDepartment(department);

        logger.info("Total employees found in department {} : {}", department, employees.size());

        return employees
                .stream()
                .map(MapperUtil::toDTO)
                .collect(Collectors.toList());
    }
}