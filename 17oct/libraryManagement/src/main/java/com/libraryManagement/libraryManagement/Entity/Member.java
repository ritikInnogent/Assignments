package com.libraryManagement.libraryManagement.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "memberTable")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "Member_Name")
    private String memberName;

    @Column (name = "Member_email")
    private String memberEmail;

//    @ManyToMany (cascade = {CascadeType.ALL})
//    @JoinTable (
//            name = "Borrowed_books",
//            joinColumns = @JoinColumn(name="member_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id")
//    )
//    @JsonIgnore  //remove circular dependency
//    private Set<Books> borrowedBooks = new HashSet<Books>();

    @ManyToMany
    @JoinTable(
            name = "member_borrowed_books",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Books> borrowedBooks = new HashSet<>();

    public void addBook(Books book){
        borrowedBooks.add(book);
    }
}
