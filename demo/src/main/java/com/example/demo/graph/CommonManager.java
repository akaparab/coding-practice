package com.example.demo.graph;

import java.util.*;

public class CommonManager {
    Map<Employee, Employee> parentMap = new HashMap<>();

    public Employee findCommonManager(Employee emp1,
                                      Employee emp2) {
        Set<Employee> set = new HashSet<>();


        while (emp1 != null) {
            set.add(emp1);
            emp1 = parentMap.get(emp1);
        }


        while (emp2 != null) {
            if (set.contains(emp2)) {
                return emp2;
            }
            emp2 = parentMap.get(emp2);
        }

        return null;

    }


    public void buildParentMap(Employee emp) {
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(emp);
        while (!queue.isEmpty()) {
            Employee e = queue.poll();
            for (Employee e1 : e.reports) {
                parentMap.put(e1, e);
                queue.offer(e1);
            }
        }

    }

    public static void main(String[] args) {

        Employee ceo = new Employee("CEO");

        Employee a = new Employee("A");
        Employee b = new Employee("B");
        Employee c = new Employee("C");

        Employee d = new Employee("D");
        Employee e = new Employee("E");
        Employee f = new Employee("F");
        Employee g = new Employee("G");

        Employee h = new Employee("H");
        Employee i = new Employee("I");

        ceo.addReport(a);
        ceo.addReport(b);
        ceo.addReport(c);

        a.addReport(d);
        a.addReport(e);

        e.addReport(h);
        e.addReport(i);

        c.addReport(f);
        c.addReport(g);

        CommonManager cm = new CommonManager();

        cm.buildParentMap(ceo);

        Employee manager = cm.findCommonManager(h, i);

        System.out.println(manager.name); // E

        manager = cm.findCommonManager(d, h);

        System.out.println(manager.name); // A

        manager = cm.findCommonManager(d, g);

        System.out.println(manager.name); // CEO
    }
}

