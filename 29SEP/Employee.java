import java.util.*;

class EmployeeComparable implements Comparable<EmployeeComparable> {
	int id;
	String name;
	String department;
	double salary;

	EmployeeComparable(int id, String name, String department, double salary) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public double getSalary() {
		return salary;
	}

	public int compareTo(EmployeeComparable emp) {

		int dept = department.toLowerCase().compareTo(emp.getDepartment().toLowerCase());
		if (dept != 0)
			return dept;
		else {
			int nam = name.toLowerCase().compareTo(emp.getName().toLowerCase());
			if (nam != 0)
				return nam;
			else {
				int sal = Double.compare(emp.getSalary(), salary);
				if (sal != 0)
					return sal;
			}
		}
		return 0;
	}
  // meaning full output display
	public String toString() {
		return "[ id = " + id + ", name = " + name + ", department = " + department + ", salary =" + salary + " ]";
	}
}

class EmployeeComparator implements Comparator<EmployeeComparable> {
	@Override
	public int compare(EmployeeComparable e1, EmployeeComparable e2) {

		int deptCompare = e1.getDepartment().compareToIgnoreCase(e2.getDepartment());
		if (deptCompare != 0) {
			return deptCompare;
		}

		int nameCompare = e1.getName().compareToIgnoreCase(e2.getName());
		if (nameCompare != 0) {
			return nameCompare;
		}

		return Double.compare(e2.getSalary(), e1.getSalary());
	}
}

class Employee {
	// here we use Iterator for travering
	public static void displayEmployee(List<EmployeeComparable> EmpObj) {
		Iterator<EmployeeComparable> itr = EmpObj.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	public static void main(String args[]) {
		List<EmployeeComparable> EmpObj = new ArrayList<>();
		EmpObj.add(new EmployeeComparable(101, "Darshan", "CppDeveloper", 30000));
		EmpObj.add(new EmployeeComparable(102, "Tanishq", "ReactDeveloper", 5500));
		EmpObj.add(new EmployeeComparable(103, "Harshit", "ReactDeveloper", 25000));
		EmpObj.add(new EmployeeComparable(104, "Harshit", "ReactDeveloper", 12000));
		EmpObj.add(new EmployeeComparable(105, "Vijay", "JavaDeveloper", 45000));
		EmpObj.add(new EmployeeComparable(107, "sourabh", "JavaDeveloper", 30000));
		EmpObj.add(new EmployeeComparable(106, "Akshay", "JavaDeveloper", 40000));
		EmpObj.add(new EmployeeComparable(106, "Akshay", "JavaDeveloper", 50000));
		EmpObj.add(new EmployeeComparable(106, "ritik", "JavaDeveloper", 40000));
		EmpObj.add(new EmployeeComparable(106, "ritik", "JavaDeveloper", 80000));
		EmpObj.add(new EmployeeComparable(106, "ritik", "JavaDeveloper", 90000));

		System.out.println("List of EmployeeComparable before sorting =>>\n");
		displayEmployee(EmpObj);

		System.out.println("List of EmployeeComparable after sorting with comparable =>>\n");

		Collections.sort(EmpObj);
		displayEmployee(EmpObj);

		System.out.println("List of EmployeeComparable after sorting with Comparator =>>\n");
		Collections.sort(EmpObj,new EmployeeComparator());
		displayEmployee(EmpObj);

	}
}
