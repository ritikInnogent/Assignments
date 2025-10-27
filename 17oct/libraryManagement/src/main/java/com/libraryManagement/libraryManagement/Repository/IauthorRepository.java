package com.libraryManagement.libraryManagement.Repository;

import com.libraryManagement.libraryManagement.Entity.Author;
import com.libraryManagement.libraryManagement.Request.AuthorRequest;
import com.libraryManagement.libraryManagement.Response.AuthorResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IauthorRepository extends JpaRepository <Author, Integer>{

}
