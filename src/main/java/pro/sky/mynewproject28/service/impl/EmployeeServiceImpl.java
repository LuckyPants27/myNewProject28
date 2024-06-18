package pro.sky.mynewproject28.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.mynewproject28.exception.EmployeeAlreadyAddedException;
import pro.sky.mynewproject28.exception.EmployeeNotFoundException;
import pro.sky.mynewproject28.exception.EmployeeStorageIsFullException;
import pro.sky.mynewproject28.model.Employee;
import pro.sky.mynewproject28.service.EmployeeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int maxEmployees = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    public Employee add(String firstName, String lastName, int salary, int departmentId) {
        String key = buildKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        employees.put(key, employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    public Employee find(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    private String buildKey(String firstName, String lastName) {
        return firstName + lastName;
    }
    public Collection<Employee> findAll() {
        return List.copyOf(employees.values());
    }
}
