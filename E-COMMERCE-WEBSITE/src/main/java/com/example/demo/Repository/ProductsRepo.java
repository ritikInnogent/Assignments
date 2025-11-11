package com.example.demo.Repository;

import com.example.demo.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// @Repository annotation batata hai ye database operations ke liye hai
@Repository

public interface ProductsRepo extends JpaRepository<Products, Long> {


    //List<Products> findByCategory(String category);

   // List<Products> findByTitleContainingIgnoreCase(String keyword);
    //containing mai wordwise searching and ignorecase means allow upper&lower cases
}

