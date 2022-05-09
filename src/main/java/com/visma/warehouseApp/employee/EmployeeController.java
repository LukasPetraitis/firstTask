package com.visma.warehouseApp.employee;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/new")
    public void createNewEmployee(@RequestBody EmployeeDTO employeeDTO){employeeService.createNewEmployee(employeeDTO);
    }

    @GetMapping("/all")
    public List<Employee> getEmployees(){return employeeService.getEmployees();
    }
}
