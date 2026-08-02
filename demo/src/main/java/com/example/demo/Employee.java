package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private String department;
    private double salary;
    private List<String> skills;


    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1L, "Alice", "Engineering", 120000,
                        List.of("Java", "Spring")),
                new Employee(2L, "Bob", "Engineering", 140000,
                        List.of("Kafka", "AWS")),
                new Employee(3L, "Charlie", "HR", 90000,
                        List.of("Recruiting")),
                new Employee(4L, "David", "Engineering", 120000,
                        List.of("Java", "Docker"))
        );

        List<String> names = employees.stream().map(Employee::getName).toList();
        List<String> UpperNames = employees.stream().map(Employee::getName)
                .map(String::toUpperCase).toList();
        System.out.println(UpperNames.toString());

        // convert to DTO
        List<EmployeeDTO> conversion = employees.stream()
                .map(e -> new EmployeeDTO(e.getId(), e.getName())).toList();

        System.out.println(conversion.toString());

        //filter salary > 100K
        List<Employee> emps = employees.stream().filter(e ->
                e.getSalary() > 100000).toList();

        System.out.println(emps.toString());

        List<Employee> emps1 = employees.stream().filter(e ->
                "engineering".equals(e.getDepartment())).toList();

        System.out.println(emps1.toString());

        // flatmap skills
        List<String> skills = employees.stream().flatMap(e -> e.getSkills().stream()).toList();
        System.out.println(skills);

        // unique skills
        List<String> unique = employees.stream().flatMap(e -> e.getSkills().stream()).distinct().toList();
        System.out.println(unique);
        List<List<Integer>> list = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5, 6));
        List<Integer> list1 = list.stream().flatMap(List::stream).toList();
        System.out.println(list1);
        List<Employee> sort = employees.stream().sorted(Comparator.comparing(Employee::getSalary)
        ).toList();
        System.out.println(sort);
        List<Employee> reverse = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()
        ).toList();
        System.out.println(reverse);
        // multiple fields
        List<Employee> sortOrder = employees.stream().sorted(Comparator.comparing(Employee::getDepartment)
                .thenComparing(Employee::getSalary)).toList();
        System.out.println(sortOrder);

        List<Integer> nums =
                List.of(1, 2, 2, 3, 3, 4);
        List<Integer> dist = nums.stream().distinct().toList();
        System.out.println(dist);

        Map<Long, String> map = employees.stream().collect(Collectors.toMap(
                Employee::getId,
                Employee::getName));

        System.out.println(map);

        Map<String, Employee> map1 =
                employees.stream().collect(Collectors.toMap(
                        Employee::getDepartment,
                        Function.identity(),
                        (e1, e2) -> e1));
        System.out.println(map1);

        // Employees by department
        Map<String, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(grouped);

        // count Employees by department
        Map<String, Long> deptCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(deptCount);

        Map<String, Double> avgSalry = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalry);

        Map<String, Optional<Employee>> maxSalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println(maxSalary);

        // paritioning
        Map<Boolean, List<Employee>> split = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 100000));
        System.out.println(split);

        // mapping
        Map<String, List<String>> mapping = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(mapping);

        // total salary
        Double salary = employees.stream().map(Employee::getSalary).reduce(
                0.0,
                Double::sum);
        System.out.println(salary);

        // max salary
        Double maxEmpSal = employees.stream().map(Employee::getSalary).reduce(
                0.0,
                Double::max);
        System.out.println(maxEmpSal);

        // highest paid employee name
        Map<String, String> highestPaid = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(Collectors.maxBy(
                                        Comparator.comparing(Employee::getSalary)),
                                e -> e.get().getName())));

        System.out.println(highestPaid);


    }

    @Data
    @AllArgsConstructor
    static
    class EmployeeDTO {
        private Long id;
        private String name;

    }


}
