package com.libraryManagement.libraryManagement.Controller;

import com.libraryManagement.libraryManagement.Request.AuthorRequest;
import com.libraryManagement.libraryManagement.Response.AuthorResponse;
import com.libraryManagement.libraryManagement.Service.IauthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/author")
@RestController
public class authorController {


        @Autowired
        private IauthorService service;

        @PostMapping("/insertauthor")
        public ResponseEntity<String>create(@RequestBody AuthorRequest request){

             service.insertAuthor(request);
            return ResponseEntity.ok("Author Created");
        }

        @GetMapping("/showauthor")
        public ResponseEntity<List<AuthorResponse>> show(){
            return new ResponseEntity<List<AuthorResponse>>(service.showAllAuthor(),HttpStatus.OK);
        }

        @GetMapping("/getById/{id}")
        public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable("id")Integer id){
            return new ResponseEntity<AuthorResponse>(service.getAuthorById(id),HttpStatus.OK);
        }

        @PutMapping("/updateauthor/{id}")
        public ResponseEntity<AuthorResponse>update(@PathVariable("id")Integer id,@RequestBody AuthorRequest request){
            return new ResponseEntity<AuthorResponse>(service.updateAuthor(id,request),HttpStatus.OK);
        }

        @DeleteMapping("/deleteauthor/{id}")
        public ResponseEntity<String>delete(@PathVariable("id")Integer id){
            return new ResponseEntity<String>(service.deleteAuthor(id),HttpStatus.OK);
        }

}
