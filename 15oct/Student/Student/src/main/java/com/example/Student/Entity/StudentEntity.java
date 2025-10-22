package com.example.Student.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity        //marks class into JPA entity and map table into the database
@Table(name = "student_table")    //mannually set table name
@Data

public class StudentEntity {
    @Id    //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)    //generate auto id
    private Long studentId;

    private String name;
    private String email;
    private String address;
    private String city;


    @ManyToMany          //defines the relationship between student and course
    @EqualsAndHashCode.Exclude          //prevent infinite recursion from equals and hashmap
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<CourseEntity> courses;
}
