package com.visma.warehouseApp.employee;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Transactional
    public void createNewEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee(employeeDTO.getFirstName(), employeeDTO.getLastName());
        employeeRepository.save(employee);

    }

    public List<Employee> getEmployees(){

        return employeeRepository.findAll();
    }


}
