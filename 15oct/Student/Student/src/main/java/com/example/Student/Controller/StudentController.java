package com.example.Student.Controller;
import com.example.Student.Service.StudentService;
import com.example.Student.DTO.CountStudentDTO;
import com.example.Student.Entity.StudentEntity;
import com.example.Student.DTO.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")

public class StudentController {


    private final StudentService studentService;
//    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        if(studentService.getAllStudents().isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/getByCourse")
    public ResponseEntity<List<StudentEntity>> getStudentByCourse(@RequestParam String courseName) {
        return ResponseEntity.ok(studentService.getStudentByCourse(courseName));
    }

    @GetMapping("/getStudentCount")
    public ResponseEntity<List<CountStudentDTO>> getStudentCount() {
        return ResponseEntity.ok(studentService.getStudentCount());
    }

    @GetMapping("/getStudentByCity")
    public ResponseEntity<List<StudentEntity>> getStudentByCity(@RequestParam String city, @RequestParam String courseInstructor) {
        return ResponseEntity.ok(studentService.getStudentByCity(city,courseInstructor));
    }

    @PostMapping("/create")

    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentEntity student = studentService.addStudent(studentDTO);

        return ResponseEntity.ok(student);
    }
    @GetMapping("/{id}")
    public StudentEntity getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }
    @PutMapping("/update/{id}")
    public StudentEntity updateStudent(@PathVariable int id, @RequestBody StudentEntity student) {
        return studentService.updateStudent(id, student);
    }
    @GetMapping("/delete/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/withoutCourse")
    public ResponseEntity<List<StudentEntity>> getStudentWithoutCourse() {
        return ResponseEntity.ok(studentService.getStudentWithoutCourse());
    }
}
