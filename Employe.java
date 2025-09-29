import java.util.*;

// it is the employee class with all details and way of sorting
class Employees implements Comparable<Employees> {
    int id;
    String name;
    String department;
    int salary;

    // constructor will all fields
    Employees(int id, String name, String department, int salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // This method lets Java sort Employees by department, then name, then salary
    @Override
    public int compareTo(Employees o) {
        int deptcomp = this.department.compareTo(o.department); // compare department strings
        if (deptcomp != 0)
            return deptcomp; // if departments are not same, sort by department

        int deptname = this.name.compareTo(o.name); // if department same, sort by name
        if (deptname != 0)
            return deptname;

        return Integer.compare(this.salary, o.salary); // if both same, sort by salary
    }
}

// Comparator class to sort Employees only by salary
class salarycompare implements Comparator<Employees> {
    @Override
    public int compare(Employees e1, Employees e2) {
        return Integer.compare(e1.salary, e2.salary); // sort by salary value
    }
}

// Comparator class to sort Employees only by department
class deptcompare implements Comparator<Employees> {
    @Override
    public int compare(Employees e1, Employees e2) {
        return e1.department.compareTo(e2.department); // sort by department string
    }
}

// Comparator class to sort Employees only by name
class namecompare implements Comparator<Employees> {
    @Override
    public int compare(Employees e1, Employees e2) {
        return e1.name.compareTo(e2.name); // sort by name string
    }
}

// Main class to define sorting
public class Employe {
    public static void main(String[] args) {
        // Create Employees objects to add fields
        Employees e1 = new Employees(1, "TKT", "HR", 50000);
        Employees e2 = new Employees(2, "Bumrah", "IT", 60000);
        Employees e3 = new Employees(3, "Tilak", "Finance", 55000);
        Employees e4 = new Employees(4, "Dubey", "IT", 70000);
        Employees e5 = new Employees(5, "Samson", "HR", 52000);

        // Store all Employees in a list
        List<Employees> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);

        // Sorting by Comparable (by department, name, salary)
        System.out.println("Sorting by natural order (department, name, salary):");
        Collections.sort(employees);
        Iterator<Employees> it = employees.iterator();
        while (it.hasNext()) {
            Employees e = it.next();
            System.out.println(e.department + " " + e.name + " " + e.salary);
        }

        // Sorting by salary using Comparator
        System.out.println("\nSorting by salary:");
        Collections.sort(employees, new salarycompare());
        Iterator<Employees> it1 = employees.iterator();
        while (it1.hasNext()) {
            Employees e = it1.next();
            System.out.println(e.department + " " + e.name + " " + e.salary);
        }

        // Sorting by department using Comparator
        System.out.println("\nSorting by department:");
        Collections.sort(employees, new deptcompare());
        Iterator<Employees> it2 = employees.iterator();
        while (it2.hasNext()) {
            Employees e = it2.next();
            System.out.println(e.department + " " + e.name + " " + e.salary);
        }

        // Sorting by name using Comparator
        System.out.println("\nSorting by name:");
        Collections.sort(employees, new namecompare());
        Iterator<Employees> it3 = employees.iterator();
        while (it3.hasNext()) {
            Employees e = it3.next();
            System.out.println(e.department + " " + e.name + " " + e.salary);
        }
    }
}
