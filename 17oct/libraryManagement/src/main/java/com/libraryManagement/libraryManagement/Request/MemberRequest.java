package com.libraryManagement.libraryManagement.Request;

import lombok.Data;

@Data
public class MemberRequest {
    private Integer id;
    private String memberEmail;
    private String memberName;
}
