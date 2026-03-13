package com.nitish.backend.secure_employee_system.util;

import com.nitish.backend.secure_employee_system.dto.EmployeeRequestDTO;
import com.nitish.backend.secure_employee_system.dto.EmployeeResponseDTO;
import com.nitish.backend.secure_employee_system.entity.Employee;

public class MapperUtil {
    public static Employee toEntity(EmployeeRequestDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        employee.setJoiningDate(dto.getJoiningDate());
        employee.setStatus(dto.getStatus());
        return employee;
    }
    public static EmployeeResponseDTO toDTO(Employee employee) {
        EmployeeResponseDTO dto=new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setSalary(employee.getSalary());
        dto.setJoiningDate(employee.getJoiningDate());
        dto.setStatus(employee.getStatus());

        return dto;
    }
}
