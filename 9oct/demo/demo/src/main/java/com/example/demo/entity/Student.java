package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data     //provide setter getter method automatically
@Entity   //bind java program into database
@Table(name="std_table")   //create table automatically in our database
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id   //through this we can explicitly make unique ID
    @GeneratedValue(strategy = GenerationType.AUTO)   //generate automatic uniwue id
    private Integer id;

    @Column(name="NAME")
    private String name;

    @Column(name="EMAIL")
    private String email;
}
