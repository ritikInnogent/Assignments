import java.util.*;

public class StudentAssignment {
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<ClassRoom> classes = new ArrayList<>();
    static ArrayList<Address> addresses = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Add a new class room
    public static void addClassRoom() {
        System.out.print("Enter class name: ");
        String cname = sc.next();
        classes.add(new ClassRoom(cname));
        System.out.println("Class added!\n");
    }

    // Add student only if age < 20 and class exists
    public static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.next();
        System.out.print("Enter classId: ");
        int classId = sc.nextInt();
        System.out.print("Enter marks: ");
        int marks = sc.nextInt();
        System.out.print("Enter gender (M/F): ");
        String gender = sc.next();
        System.out.print("Enter age: ");
        int age = sc.nextInt();

        if (age > 20) {
            System.out.println("Age should be <= 20\n");
            return;
        }
        boolean classExists = classes.stream().anyMatch(c -> c.id == classId);
        if (!classExists) {
            System.out.println("Class not found, add class first!\n");
            return;
        }
        students.add(new Student(name, classId, marks, gender, age));
        System.out.println("Student added!\n");
    }

    // Add address to student
    public static void addAddress() {
        System.out.print("Enter pin code: ");
        String pin = sc.next();
        System.out.print("Enter city: ");
        String city = sc.next();
        System.out.print("Enter studentId: ");
        int sid = sc.nextInt();

        boolean studentExists = students.stream().anyMatch(s -> s.id == sid);
        if (!studentExists) {
            System.out.println("Student not found!\n");
            return;
        }
        addresses.add(new Address(pin, city, sid));
        System.out.println("Address added!\n");
    }

    // Display students
    public static void showStudents() {
        System.out.println("--- Students ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Display addresses
    public static void showAddresses() {
        System.out.println("--- Addresses ---");
        for (Address a : addresses) {
            System.out.println(a);
        }
    }

    // Display classes
    public static void showClasses() {
        System.out.println("--- Classes ---");
        for (ClassRoom c : classes) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {
        // here i make Simple menu loop
        while (true) {
            System.out.println("1. Add Class");
            System.out.println("2. Add Student");
            System.out.println("3. Add Address");
            System.out.println("4. Show Classes");
            System.out.println("5. Show Students");
            System.out.println("6. Show Addresses");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addClassRoom(); break;
                case 2: addStudent(); break;
                case 3: addAddress(); break;
                case 4: showClasses(); break;
                case 5: showStudents(); break;
                case 6: showAddresses(); break;
                case 7: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }
}
