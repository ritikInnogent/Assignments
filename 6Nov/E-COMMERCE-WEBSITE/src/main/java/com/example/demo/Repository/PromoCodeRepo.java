package com.example.demo.Repository;

import com.example.demo.Model.PromoCodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoCodeRepo extends  JpaRepository<PromoCodes, Long> {

}



