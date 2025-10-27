package com.libraryManagement.libraryManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="authorTable")
public class Author {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Author_Name")
    private String name;
    @Column (name= "Email_Name")
    private String email;


    @OneToMany(mappedBy = "author" ,cascade = CascadeType.ALL)
    private List<Books> book;
}
