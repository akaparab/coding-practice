package com.example.demo.graph;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    String name;
    List<Employee> reports = new ArrayList<>();

    Employee(String name) {
        this.name = name;
    }

    public void addReport(Employee employee) {
        reports.add(employee);
    }
}
