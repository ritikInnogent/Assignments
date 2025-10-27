package com.libraryManagement.libraryManagement.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BorrowedMemberResponse {
    private Integer borrowedMemberId;
    private Integer borrowedBookId;
    private String borrowedBookName;
    private String borrowedMemberName;
}
