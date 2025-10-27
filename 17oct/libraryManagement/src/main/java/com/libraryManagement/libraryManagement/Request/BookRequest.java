package com.libraryManagement.libraryManagement.Request;

import lombok.Data;

import java.util.List;

@Data
public class BookRequest {
    private Integer bookPrice;
    private Integer bookStock;
    private String bookDesc;
    private String bookTitle;
    private List<Integer> authorId;
}
