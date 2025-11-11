package com.example.demo.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity    //table created in database
@Table(name = "products")
@Data     //automatically provide methods - getter setter toString equals
@NoArgsConstructor    //create constructor with no args
@AllArgsConstructor   //create constructor with all class variable as arguments
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double price;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(length = 500)
    private String image;

    private Double rate;

    private Integer count;
}