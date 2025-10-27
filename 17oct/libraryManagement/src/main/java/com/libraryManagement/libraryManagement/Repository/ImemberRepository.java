package com.libraryManagement.libraryManagement.Repository;

import com.libraryManagement.libraryManagement.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImemberRepository extends JpaRepository<Member, Integer> {
}
