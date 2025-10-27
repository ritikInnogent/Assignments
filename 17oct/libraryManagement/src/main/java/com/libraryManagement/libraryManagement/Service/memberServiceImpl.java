package com.libraryManagement.libraryManagement.Service;

import com.libraryManagement.libraryManagement.Entity.Member;
import com.libraryManagement.libraryManagement.Repository.ImemberRepository;
import com.libraryManagement.libraryManagement.Request.MemberRequest;
import com.libraryManagement.libraryManagement.Response.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class memberServiceImpl implements ImemberService {

        @Autowired
        private ImemberRepository imemberRepository;


        @Override
        public void insertMember(MemberRequest request) {
            Member member = new Member();
            member.setMemberName(request.getMemberName());
            member.setMemberEmail(request.getMemberEmail());

            imemberRepository.save(member);
        }

        @Override
        public List<MemberResponse> showAllMembers () {
        List<MemberResponse> memberResponses = new ArrayList<>();
        List<Member> Member = imemberRepository.findAll();

        for (Member member : Member) {
            MemberResponse memberResponse = new MemberResponse();
            memberResponse.setMemberName(member.getMemberName());
            memberResponse.setMemberEmail(member.getMemberEmail());

            memberResponses.add(memberResponse);
        }
        return memberResponses;
    }


        @Override
        public MemberResponse findMemberById (Integer id){
        Member member = imemberRepository.findById(id).get();
        MemberResponse MemberResponse = new MemberResponse();
        MemberResponse.setMemberName(member.getMemberName());
        MemberResponse.setMemberEmail(member.getMemberEmail());

        return MemberResponse;
    }

        @Override
        public MemberResponse updateMember (Integer id, MemberRequest request){
        Member member = imemberRepository.findById(id).get();
        member.setMemberName(request.getMemberName());
        member.setMemberEmail(request.getMemberEmail());

        imemberRepository.save(member);
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setId(member.getId());
        memberResponse.setMemberName(member.getMemberName());
        memberResponse.setMemberEmail(member.getMemberEmail());

        return memberResponse;
    }

        @Override
        public String deleteMember (Integer id){
        imemberRepository.deleteById(id);
        return "member deleted successfully";
    }

}

