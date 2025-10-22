package com.example.Student.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity   //consider this class into JPA entity and maps the table into the database
@Data      //automatically add getter setter methods and other common methods
@Table(name = "courseTable")         //created table having this given name

public class CourseEntity {
    @Id      //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //auto generate unique id
    private Long courseId;
    private String courseName;
    private String courseInstructor;

    @JsonIgnore    //prevent infinite loop when both table having reference each other
    @ManyToMany(mappedBy = "courses")
    @EqualsAndHashCode.Exclude    //excludes the fields from equals() to avoid recursion
    private Set<StudentEntity> students;
}
