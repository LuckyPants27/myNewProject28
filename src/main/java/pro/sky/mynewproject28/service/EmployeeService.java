package pro.sky.mynewproject28.service;

import pro.sky.mynewproject28.model.Employee;

import java.util.Collection;

public interface EmployeeService {

    Employee add(String firstName, String lastName, int salary, int departmentId);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> findAll();
}
