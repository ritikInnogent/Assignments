import java.io.*;
import java.util.*;

// Custom exception for invalid age
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Custom exception for invalid marks
class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super("marks is not valid");
    }
}

// Custom exception for deletion not found
class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}

// enables serializable that convert java object into byte stream so that we can
// save it to the file
class ClassRoom implements Serializable {
    static int counter = 1;
    int id;
    String name;

    ClassRoom(String name) {
        this.id = counter++;
        this.name = name;
    }

    public String toString() {
        return String.format("ClassRoom{id=%d, name='%s'}", id, name);
    }
    // here we override the default toString() method so that formatted string
    // representation of this ClassRoom object in console
}

class Address implements Serializable {
    static int counter = 1;
    int id;
    String pinCode;
    String city;
    int studentId;

    Address(String pinCode, String city, int studentId) {
        this.id = counter++;
        this.pinCode = pinCode;
        this.city = city;
        this.studentId = studentId;
    }

    public String toString() {
        return String.format("Address{id=%d, pinCode='%s', city='%s', studentId=%d}", id, pinCode, city, studentId);
    }
}

class Student implements Serializable {
    static int counter = 1;
    int id;
    String name;
    int classId;
    int marks;
    String gender;
    int age;
    String result;
    int rank;

    Student(String name, int classId, int marks, String gender, int age)
            throws InvalidAgeException, InvalidMarksException {
        if (age > 20)
            throw new InvalidAgeException("Invalid age: " + age + ". Age must be <= 20.");
        if (marks < 0 || marks > 100)
            throw new InvalidMarksException("Invalid marks: " + marks + ". Marks must be between 0 and 100.");

        this.id = counter++;
        this.name = name;
        this.classId = classId;
        this.marks = marks;
        this.gender = gender;
        this.age = age;
        this.result = (marks < 50) ? "Fail" : "Pass";
    }

    public String toString() {
        return String.format(
                "Student{id=%d, name='%s', classId=%d, marks=%d, gender='%s', age=%d, result='%s', rank=%d}",
                id, name, classId, marks, gender, age, result, rank);
    }
}

public class FileHandling {
    // creating a list so that it can store the list data of all objects
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<ClassRoom> classes = new ArrayList<>();
    static ArrayList<Address> addresses = new ArrayList<>();

    // Save any collection to file and handled IOexception
    public static void saveToFile(Object obj, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) { // ObjectOutputStream  wraps the file stream to allow writing Java  objects in a serialized form.                                                                                
                                                                                                
            oos.writeObject(obj);
        } catch (IOException e) {
            System.out.println("IOException while saving to " + filename + ": " + e.getMessage());
        }
    }

    // it will read collection from file also handle if file is not available
    public static Object readFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println(filename + " not found. Initializing empty collection.");
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) { // FileInputStream for reading
                                                                                         // bytes
            return ois.readObject();
        } catch (IOException e) {
            System.out.println("IOException while reading " + filename + ": " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException while reading " + filename + ": " + e.getMessage());
            return null;
        }
    }

    // Assign ranks based on marks and initially highest marks is 1
    public static void assignRanks() {
        if (students == null)
            return; // safeguard
        students.sort((a, b) -> b.marks - a.marks);
        int rank = 1;
        for (int i = 0; i < students.size(); i++) {
            if (i > 0 && students.get(i).marks == students.get(i - 1).marks) {
                students.get(i).rank = students.get(i - 1).rank;
            } else {
                students.get(i).rank = rank;
            }
            rank++;
        }
    }

    // Write all students to students.txt i.e.,text file
    public static void writeAllStudentsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) { // Open file to write characters and if exist then it over write
                                                                               
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("IOException while writing students to " + filename + ": " + e.getMessage());
        }
    }

    // Write all classes to classes.txt (text file)
    public static void writeAllClassesToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (ClassRoom c : classes) {
                writer.write(c.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("IOException while writing classes to " + filename + ": " + e.getMessage());
        }
    }

    // Write all addresses to addresses.txt (text file)
    public static void writeAllAddressesToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Address a : addresses) {
                writer.write(a.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("IOException while writing addresses to " + filename + ": " + e.getMessage());
        }
    }

    // Write top 5 ranked students to text file
    public static void writeTop5ToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            assignRanks();
            students.sort(Comparator.comparingInt(s -> s.rank));
            for (int i = 0; i < Math.min(5, students.size()); i++) {
                writer.write(students.get(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("IOException while writing top 5 students to " + filename + ": " + e.getMessage());
        }
    }

    // here we handle the deletion scenerio and manage with exception handling

    public static void deleteStudent(int studentId) {
        try {
            boolean found = false;
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).id == studentId) {
                    students.remove(i);
                    System.out.println("Student with ID " + studentId + " deleted successfully.");
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new NotFoundException("Student with ID " + studentId + " not found.");
            }
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete class with exception handling
    public static void deleteClassRoom(int classId) {
        try {
            boolean found = false;
            for (int i = 0; i < classes.size(); i++) {
                if (classes.get(i).id == classId) {
                    classes.remove(i);
                    System.out.println("Class with ID " + classId + " deleted successfully.");
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new NotFoundException("Class with ID " + classId + " not found.");
            }
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        // Add sample data with exception handling
        try {
            classes.add(new ClassRoom("Math"));
            classes.add(new ClassRoom("Science"));
            students.add(new Student("harshit", 1, 95, "M", 18));
            students.add(new Student("Tanishq", 2, 88, "F", 19));
            students.add(new Student("anurag", 1, 60, "M", 17));
            students.add(new Student("badal", 2, 33, "F", 20));
            students.add(new Student("ritik", 1, 76, "M", 18));
            students.add(new Student("Mona", 2, 82, "F", 19));
        } catch (InvalidAgeException | InvalidMarksException e) {
            System.out.println("Error while adding student: " + e.getMessage());
        }

        addresses.add(new Address("452001", "Indore", 1));
        addresses.add(new Address("452002", "Indore", 2));
        addresses.add(new Address("486881", "Rewa", 3));
        addresses.add(new Address("452003", "Indore", 4));
        addresses.add(new Address("482001", "Jabalpur", 5));
        addresses.add(new Address("452004", "Indore", 6));

        // Save each collection in a separate file
        saveToFile(students, "students.txt");
        saveToFile(classes, "classes.txt");
        saveToFile(addresses, "addresses.txt");

        // Clear all collections
        students.clear();
        classes.clear();
        addresses.clear();

        // Read data back from files
        students = (ArrayList<Student>) readFromFile("students.txt");
        if (students == null)
            students = new ArrayList<>();

        classes = (ArrayList<ClassRoom>) readFromFile("classes.txt");
        if (classes == null)
            classes = new ArrayList<>();

        addresses = (ArrayList<Address>) readFromFile("addresses.txt");
        if (addresses == null)
            addresses = new ArrayList<>();

        // Assign ranks and write to files
        assignRanks();
        writeTop5ToFile("Top5students.txt");
        writeAllStudentsToFile("students.txt");
        writeAllClassesToFile("classes.txt");
        writeAllAddressesToFile("addresses.txt");

        // Print all loaded data
        System.out.println("---- Students Data ----");
        for (Student s : students)
            System.out.println(s);

        System.out.println("--- Classes Data ---");
        for (ClassRoom c : classes)
            System.out.println(c);

        System.out.println("--- Addresses Data ---");
        for (Address a : addresses)
            System.out.println(a);

        System.out.println("If you want to see the top five students data then check the Top5students.txt file.");

        deleteStudent(100); // here student ID is not available so it will through an exception and print mssge
        deleteStudent(1); // it will delete the student whose ID is 1

        deleteClassRoom(50); // class ID 50 does not exist so exception will through and print mss
        deleteClassRoom(1); // Deletes existing class with ID 1
    }
}
