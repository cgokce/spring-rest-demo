package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController{

    private EmployeeService employeeService;

    // Inject Employee Service
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    // Expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        // return if found
        if (theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // Add a new Employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        // also just in case they pas an id in JSON, set it to 0
        // This is to force a save of new item instead of an update
        theEmployee.setId(0);

        employeeService.save(theEmployee);

        return theEmployee;
    }

    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        employeeService.save(theEmployee);

        return theEmployee;
    }

    // add mapping for DELETE /employee/{employeeId} - delete existing employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee theEmployee = employeeService.findById(employeeId);

        // Throw exception if null
        if (theEmployee == null){
            throw new RuntimeException("Employee does not exists: " + theEmployee.toString());
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }


}