package org.kainos.ea.api;

import org.kainos.ea.cli.Employee;
import org.kainos.ea.cli.Ipayable;
import org.kainos.ea.cli.SalesEmployee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    public List<Ipayable> getEmployees() {
        Employee employee = new Employee(1, "Shaun", 20000);
        SalesEmployee salesEmployee = new SalesEmployee(1, "Shaun", 20000,
                1000, 0.01f);

        List<Ipayable> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(salesEmployee);

        for (Ipayable e: employees) {
            System.out.println(e.calcPay());
        }
        return employees;
    }
}
