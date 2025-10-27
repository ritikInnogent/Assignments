package com.libraryManagement.libraryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "booksTable")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "Book_Title")
    private String bookTitle;
    @Column (name = "Book_Description]")
    private String bookDesc;
    @Column (name = "Book_Stock")
    public Integer bookStock;
    @Column (name = "Book_Price")
    private Integer bookPrice;


    @ManyToOne
    @JoinColumn(name="author_id")
    @JsonIgnore  //remove circular dependency
    private Author author;

    @ManyToMany(mappedBy = "borrowedBooks",cascade = CascadeType.ALL)
    @JsonIgnore  //remove circular dependency
    private Set<Member> borrowedMembers = new HashSet<Member>();


}

