public class Student {
    static int counter = 1;
    int id;
    String name;
    int classId;
    int marks;
    String gender;
    int age;
    String result;
    int rank; 

    public Student(String name, int classId, int marks, String gender, int age) {
        this.id = counter++;
        this.name = name;
        this.classId = classId;
        this.marks = marks;
        this.gender = gender;
        this.age = age;
        this.result = (marks < 50) ? "Fail" : "Pass";
    }

    public String toString() {
        return String.format("Student{id=%d, name='%s', classId=%d, marks=%d, gender='%s', age=%d, result='%s', rank=%d}",
            id, name, classId, marks, gender, age, result, rank);
    }
}
