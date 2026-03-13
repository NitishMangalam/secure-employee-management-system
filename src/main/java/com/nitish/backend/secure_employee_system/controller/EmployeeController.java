package com.nitish.backend.secure_employee_system.controller;

import com.nitish.backend.secure_employee_system.dto.EmployeeRequestDTO;
import com.nitish.backend.secure_employee_system.dto.EmployeeResponseDTO;
import com.nitish.backend.secure_employee_system.response.ApiResponse;
import com.nitish.backend.secure_employee_system.service.EmployeeService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    public ApiResponse<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO dto){

        EmployeeResponseDTO employee = employeeService.createEmployee(dto);

        return new ApiResponse<>(
                true,
                "Employee created successfully",
                employee
        );
    }

    @GetMapping
    public ApiResponse<List<EmployeeResponseDTO>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        List<EmployeeResponseDTO> employees = employeeService.getAllEmployees(page, size);

        return new ApiResponse<>(
                true,
                "Employees fetched successfully",
                employees
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id){

        EmployeeResponseDTO employee = employeeService.getEmployeeById(id);

        return new ApiResponse<>(
                true,
                "Employee fetched successfully",
                employee
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRequestDTO dto){

        EmployeeResponseDTO employee = employeeService.updateEmployee(id, dto);

        return new ApiResponse<>(
                true,
                "Employee updated successfully",
                employee
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteEmployee(@PathVariable Long id){

        employeeService.deleteEmployee(id);

        return new ApiResponse<>(
                true,
                "Employee deleted successfully",
                null
        );
    }

    @GetMapping("/email/{email}")
    public ApiResponse<EmployeeResponseDTO> findByEmail(@PathVariable String email){

        EmployeeResponseDTO employee = employeeService.findByEmail(email);

        return new ApiResponse<>(
                true,
                "Employee fetched successfully",
                employee
        );
    }

    @GetMapping("/department/{department}")
    public ApiResponse<List<EmployeeResponseDTO>> findByDepartment(@PathVariable String department){

        List<EmployeeResponseDTO> employees = employeeService.findByDepartment(department);

        return new ApiResponse<>(
                true,
                "Employees fetched successfully",
                employees
        );
    }
}