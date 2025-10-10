package com.example.demo.controller;
import com.example.demo.entity.Student;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //generate json request response
@RequestMapping("/controller") //generate base URL
public class StudentController {

       @Autowired
       private IStudentService studentService;

       @PostMapping("/newstudent")
       public ResponseEntity<Student> insert(@RequestBody Student student){
           return new ResponseEntity<Student>(studentService.insert(student), HttpStatus.CREATED);
       }

    @GetMapping("/showstudent")
    public ResponseEntity<List<Student>> show(){
        return new ResponseEntity<List<Student>>(studentService.show(), HttpStatus.OK);
    }
    @PutMapping("/updatestudent/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") Integer id, @RequestBody Student student){
        System.out.println("id"+id+" "+student);
        return new ResponseEntity<Student>(studentService.update(id,student), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
           return new ResponseEntity<String>(studentService.delete(id),HttpStatus.OK);
    }
}
