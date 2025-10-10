package com.example.demo.service;
import com.example.demo.entity.Student;
import java.util.List;

public interface IStudentService {
    public Student insert(Student student);
    public List<Student> show();
    public String delete(Integer id);
    public Student update(Integer id,Student student);
}
