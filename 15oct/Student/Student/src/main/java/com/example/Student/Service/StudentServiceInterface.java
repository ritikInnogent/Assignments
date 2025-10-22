package com.example.Student.Service;

import com.example.Student.DTO.CountStudentDTO;
import com.example.Student.DTO.StudentDTO;
import com.example.Student.Entity.StudentEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentServiceInterface {
    List<StudentEntity> getAllStudents();
    StudentEntity getStudentById(long id);
    StudentEntity addStudent(StudentDTO dto);
    StudentEntity updateStudent(long id, StudentEntity student);
    void deleteStudent(long id);


    List<StudentEntity> getStudentByCourse(String name);
    List<CountStudentDTO> getStudentCount();
    List<StudentEntity> getStudentByCity(String city,String courseInstructor);
    List<StudentEntity> getStudentWithoutCourse();

}
