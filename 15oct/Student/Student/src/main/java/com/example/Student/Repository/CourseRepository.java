package com.example.Student.Repository;

import com.example.Student.DTO.CourseStudentCountDTO;
import com.example.Student.Entity.CourseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    @Query("SELECT c FROM CourseEntity c LEFT JOIN c.students s WHERE s IS NULL")
    List<CourseEntity> getCourseWithoutStudent();

    @Modifying          //indicates that query changes the data(update and delete)
    @Transactional    //means the update happens as a part of transaction
    @Query("UPDATE CourseEntity c SET c.courseInstructor = :courseInstructor WHERE c.courseId = :courseId")
    int updateCourseInstructor(@Param("courseId") Long courseId,
                               @Param("courseInstructor") String courseInstructor);

//through this we get all courses along with their enrolled students
    @Query("SELECT new com.example.Student.DTO.CourseStudentCountDTO(" +
            "c.courseId, c.courseName, c.courseInstructor, COUNT(s)) " +
            "FROM CourseEntity c " +
            "LEFT JOIN c.students s " +
            "GROUP BY c.courseId, c.courseName, c.courseInstructor")
    List<CourseStudentCountDTO> findAllCoursesWithStudentCount();


//through this we also get the courses along with enrolled students but in sequence order
    @Query("SELECT new com.example.Student.DTO.CourseStudentCountDTO(" +
            "c.courseId, c.courseName, c.courseInstructor, COUNT(s)) " +
            "FROM CourseEntity c " +
            "LEFT JOIN c.students s " +
            "GROUP BY c.courseId, c.courseName, c.courseInstructor " +
            "ORDER BY COUNT(s) DESC")
    List<CourseStudentCountDTO> findTopCourses();


}
