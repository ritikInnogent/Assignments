package com.libraryManagement.libraryManagement.Response;

import lombok.Data;

import java.util.List;

@Data
public class BookResponse {
    private Integer bookId;
    private String bookTitle;
    private Integer bookPrice;
    private String bookDesc;
    private Integer bookStock;
    private List<String> authorName;
}
