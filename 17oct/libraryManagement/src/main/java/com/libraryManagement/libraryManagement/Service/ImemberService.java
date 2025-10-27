package com.libraryManagement.libraryManagement.Service;
import com.libraryManagement.libraryManagement.Request.MemberRequest;
import com.libraryManagement.libraryManagement.Response.MemberResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImemberService {

    public void insertMember(MemberRequest request);
    public List<MemberResponse> showAllMembers();
    public MemberResponse findMemberById(Integer id);
    public MemberResponse updateMember(Integer id, MemberRequest memberRequest);
    public String deleteMember(Integer id);
}
