package com.libraryManagement.libraryManagement.Request;


import lombok.Data;

@Data
public class BookBorrowRequest {
    private Integer member_id;
    private Integer book_id;
}
