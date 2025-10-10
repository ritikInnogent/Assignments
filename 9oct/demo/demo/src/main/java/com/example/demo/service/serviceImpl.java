package com.example.demo.service;
import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class serviceImpl implements IStudentService{

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student insert(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public List<Student> show() {
        return studentRepo.findAll();
    }

    @Override
    public String delete(Integer id) {
         studentRepo.deleteById(id);
        return null;
    }

    @Override
    public Student update(Integer id, Student student) {
        Student std = studentRepo.findById(id).get();
        if (std != null) {
            std.setName(student.getName());
            std.setEmail(student.getEmail());
        }
        return studentRepo.save(std);
    }
}
