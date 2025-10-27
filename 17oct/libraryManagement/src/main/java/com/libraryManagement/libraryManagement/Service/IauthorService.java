package com.libraryManagement.libraryManagement.Service;

import com.libraryManagement.libraryManagement.Request.AuthorRequest;
import com.libraryManagement.libraryManagement.Response.AuthorResponse;

import java.util.List;

public interface IauthorService {

    public void insertAuthor(AuthorRequest authorRequest);
    public AuthorResponse getAuthorById(Integer id);
    public List<AuthorResponse> showAllAuthor();
    public AuthorResponse updateAuthor(Integer id,AuthorRequest authorRequest);
    public String deleteAuthor(Integer id);
}
