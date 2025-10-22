package com.example.Student.Service;

import com.example.Student.DTO.CountStudentDTO;
import com.example.Student.Entity.StudentEntity;
import com.example.Student.DTO.StudentDTO;
import com.example.Student.Entity.CourseEntity;
import com.example.Student.Repository.CourseRepository;
import com.example.Student.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService implements StudentServiceInterface
{
    @Autowired
    public StudentRepository studentRepository;
    @Autowired
    public CourseRepository courseRepository;


    @Override
    public List<StudentEntity> getAllStudents()
    {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity getStudentById(long id)
    {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public StudentEntity addStudent(StudentDTO dto)
    {
        StudentEntity student = new StudentEntity();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAddress(dto.getAddress());
        student.setCity(dto.getCity());


        Set<CourseEntity> courses = new HashSet<>(courseRepository.findAllById(dto.getCourseId()));

        if (courses.size() != dto.getCourseId().size()) {
            throw new RuntimeException("Some course IDs not found in database!");
        }

        student.setCourses(courses);

        studentRepository.save(student);
        return student;
    }

    @Override
    public  StudentEntity updateStudent(long id, StudentEntity student)
    {
        StudentEntity existingStudent = studentRepository.findById(id).orElse(null);
        if(existingStudent != null)
        {
            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setAddress(student.getAddress());
            existingStudent.setCourses(student.getCourses());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    @Override
    public void deleteStudent(long id)
    {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentEntity> getStudentByCourse(String courseName){
        return studentRepository.findByCourseName(courseName);
    }

    @Override
    public List<CountStudentDTO> getStudentCount() {
        return studentRepository.getStudentCountPerCourse();
    }

    @Override
    public List<StudentEntity> getStudentByCity(String city,String courseInstructor){
        return studentRepository.getStudentByCity(city,courseInstructor);
    }

    @Override
    public List<StudentEntity> getStudentWithoutCourse() {
        return studentRepository.getStudentWithoutCourse();
    }
}
