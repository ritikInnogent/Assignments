package com.libraryManagement.libraryManagement.Request;

import lombok.Data;

@Data
public class AuthorRequest {
    private Integer id;
    private String name;
    private String email;
}
