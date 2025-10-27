package com.libraryManagement.libraryManagement.Service;

import com.libraryManagement.libraryManagement.Request.AuthorRequest;
import com.libraryManagement.libraryManagement.Request.BookBorrowRequest;
import com.libraryManagement.libraryManagement.Request.BookRequest;
import com.libraryManagement.libraryManagement.Response.AuthorResponse;
import com.libraryManagement.libraryManagement.Response.BookResponse;
import com.libraryManagement.libraryManagement.Response.BorrowedMemberResponse;

import java.util.List;

public interface IbooksService {

    public void insertBook(BookRequest request);
    public List<BookResponse> showAllBooks();
    public BookResponse findBookById(Integer id);
    public String deleteBook(Integer id);
    public BookResponse updateBook(Integer id, BookRequest bookRequest);
    public void insertBookBorrow(BookBorrowRequest request);
    public List<BorrowedMemberResponse> showBorrowBooks();
}
