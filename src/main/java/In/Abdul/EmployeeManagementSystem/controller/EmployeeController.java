package In.Abdul.EmployeeManagementSystem.controller;

import In.Abdul.EmployeeManagementSystem.entity.Employee;
import In.Abdul.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin
public class EmployeeController {


    @Autowired
    private EmployeeRepository repository;

    @GetMapping(value = "/employees")
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    // get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = repository.findById(id);
        return ResponseEntity.ok(employee);
    }

    // update Employee by id
    @PostMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Integer id, @RequestBody Employee updateEmployee) {
        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee Obj is not found" + id));

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());

        Employee save = repository.save(employee);
        return ResponseEntity.ok(save);
    }


    // delete employee by id
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployeeById(@PathVariable Integer id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee Obj is not found" + id));

        repository.delete(employee);
        Map<String, Boolean> result = new HashMap<>();
        result.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(result);
    }


}
