package com.libraryManagement.libraryManagement.Controller;

import com.libraryManagement.libraryManagement.Request.AuthorRequest;
import com.libraryManagement.libraryManagement.Request.BookBorrowRequest;
import com.libraryManagement.libraryManagement.Request.BookRequest;
import com.libraryManagement.libraryManagement.Response.AuthorResponse;
import com.libraryManagement.libraryManagement.Response.BookResponse;
import com.libraryManagement.libraryManagement.Response.BorrowedMemberResponse;
import com.libraryManagement.libraryManagement.Service.IbooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class booksController {


        @Autowired
        private IbooksService service;

        @PostMapping("/insertbook")
        public ResponseEntity<String>create(@RequestBody BookRequest request){
            service.insertBook(request);
            return ResponseEntity.ok("Book Created");
        }

        @GetMapping("/showbook")
        public ResponseEntity<List<BookResponse>>show(){
            return new ResponseEntity<List<BookResponse>>(service.showAllBooks(),HttpStatus.OK);
        }

        @GetMapping("/getBookById/{id}")
        public ResponseEntity<BookResponse>getBookById(@PathVariable("id")Integer id){
            return new ResponseEntity<BookResponse>(service.findBookById(id),HttpStatus.OK);
        }

        @PutMapping("/updatebook/{id}")
        public ResponseEntity<BookResponse>update(@PathVariable("id")Integer id, @RequestBody BookRequest request){
            return new ResponseEntity<BookResponse>(service.updateBook(id,request),HttpStatus.OK);
        }

        @DeleteMapping("/deletebook/{id}")
        public ResponseEntity<String> deleteBooks(@PathVariable("id")Integer id){
            return new ResponseEntity<String>(service.deleteBook(id),HttpStatus.OK);
        }

        @PostMapping("/borrow")
        public ResponseEntity<String> borrowBook(@RequestBody BookBorrowRequest request){
            service.insertBookBorrow(request);
            return ResponseEntity.ok("Book Borrow Successfull");
        }

    @GetMapping("/GetBorrowedBook")
    public List<BorrowedMemberResponse> getBorrowedBook() {
        List<BorrowedMemberResponse> result = service.showBorrowBooks();
        return result;
        }
}
