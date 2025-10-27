package com.libraryManagement.libraryManagement.Repository;

import com.libraryManagement.libraryManagement.Entity.Books;
import com.libraryManagement.libraryManagement.Response.BorrowedMemberResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IbooksRepository extends JpaRepository<Books,Integer> {

//    @Query
//            ("Select b from Books b join b.borrowedMembers m")
//    List<Books> showBorrowBook();

    @Query("""
        SELECT BorrowedMemberResponse(
            m.id,
            b.id,
            b.bookTitle,
            m.memberName
        )
        FROM Books b
        JOIN b.borrowedMembers m
    """)
    List<BorrowedMemberResponse> showBorrowBook();

}
