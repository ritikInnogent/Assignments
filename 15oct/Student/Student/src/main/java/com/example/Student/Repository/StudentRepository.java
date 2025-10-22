package com.example.Student.Repository;

import com.example.Student.DTO.CountStudentDTO;
import com.example.Student.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;    //custom jpql queries
import org.springframework.data.repository.query.Param;  //pass parameters in queries

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    //find students who enrolled in specific course name
    @Query("select s from StudentEntity s JOIN s.courses c where c.courseName = :courseName")
    public List<StudentEntity> findByCourseName(@Param("courseName") String courseName);


     //through this we get total no of students enrolled in specific course
    @Query("SELECT c.courseId, c.courseName, COUNT(s.studentId) " +
            "FROM StudentEntity s " +
            "JOIN s.courses c " +
            "GROUP BY c.courseId")
    List<CountStudentDTO> getStudentCountPerCourse();


    //we get students from specific city who enrolled in course which taught by instructor
    @Query("select s from StudentEntity s JOIN s.courses c where c.courseInstructor = :courseInstructor AND s.city =:city")
    List<StudentEntity> getStudentByCity(@Param("city") String city, @Param("courseInstructor") String courseInstructor);

    //we get the students who not enrolled in any course
    @Query("SELECT s FROM StudentEntity s LEFT JOIN s.courses c WHERE c IS NULL")
    List<StudentEntity> getStudentWithoutCourse();
}
