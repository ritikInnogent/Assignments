package com.libraryManagement.libraryManagement.Controller;

import com.libraryManagement.libraryManagement.Request.MemberRequest;
import com.libraryManagement.libraryManagement.Response.MemberResponse;
import com.libraryManagement.libraryManagement.Service.ImemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class memberController {
@Qualifier("memberServiceImpl")
    @Autowired
        private ImemberService service;

        @PostMapping("/insert")
        public ResponseEntity<String> insert(@RequestBody MemberRequest request){
            service.insertMember(request);
            return ResponseEntity.ok("Author Created");
        }

        @GetMapping("/showmembers")
        public ResponseEntity<List<MemberResponse>>showMember(){
            return new ResponseEntity<List<MemberResponse>>(service.showAllMembers(), HttpStatus.OK);
        }

        @GetMapping("/getMemberById/{id}")
        public ResponseEntity<MemberResponse>getMemberById(@PathVariable("id")Integer id){
            return new ResponseEntity<MemberResponse>(service.findMemberById(id),HttpStatus.OK);
        }

        @PutMapping("/updatemember/{id}")
        public ResponseEntity<MemberResponse>update(@PathVariable("id")Integer id, @RequestBody MemberRequest request){
            return new ResponseEntity<MemberResponse>(service.updateMember(id,request),HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> delete(@PathVariable("id")Integer id){
            return new ResponseEntity<String>(service.deleteMember(id),HttpStatus.OK);
        }
}
