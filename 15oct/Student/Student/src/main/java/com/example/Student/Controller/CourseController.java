package com.example.Student.Controller;


import com.example.Student.DTO.CourseStudentCountDTO;
import com.example.Student.Entity.CourseEntity;
import com.example.Student.Entity.StudentEntity;
import com.example.Student.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     //through this we can handle http request like get post put delete
@RequestMapping("/course")     //base url for all mapping

public class CourseController {

    private final CourseService courseService;
    //we use constructor based dependency injection
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseEntity> getCourses(){
        return courseService.getAllCourses();            //call service method to get all the courses
    }
    @PostMapping("/create")
    public CourseEntity createStudent(@RequestBody CourseEntity student) {
        System.out.println(student.toString());
        return courseService.addCourse(student);    //call service methods to save the data
    }
    @GetMapping("/{id}")
    public CourseEntity getStudentById(@PathVariable long id) {
        return courseService.getCourseById(id);
    }
    @PutMapping("/update/{id}")
    public CourseEntity updateStudent(@PathVariable long id, @RequestBody CourseEntity course) {
        return courseService.updateCourse(id, course);
    }
    @GetMapping("/delete/{id}")
    public void deleteStudent(@PathVariable int id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/withoutStudent")
    public List<CourseEntity> getCourseWithoutStudent(){
        return courseService.getCourseWithoutStudent();
    }


    //update only instructor name for specific course
    @PutMapping("/{courseId}/updateInstructor")
    public ResponseEntity<CourseEntity> updateInstructor(@PathVariable Long courseId, @RequestParam String courseInstructor) {

        return ResponseEntity.ok(courseService.updateCourseInstructor(courseId, courseInstructor));
    }

    @GetMapping("/withStudentCount")
    public ResponseEntity<List<CourseStudentCountDTO>> getCoursesWithStudentCount() {
        List<CourseStudentCountDTO> courses = courseService.getCoursesWithStudentCount();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/top")
    public ResponseEntity<List<CourseStudentCountDTO>> getTopCourses(@RequestParam int n) {
        return ResponseEntity.ok(courseService.getTopCourses(n));
    }

}
