package com.student.repository;

import com.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
    @Query("SELECT s FROM Student s WHERE CONCAT(s.name, s.email, s.course, s.department) LIKE %?1%")
    Page<Student> findAll(String keyword, Pageable pageable);

    @Query("SELECT COUNT(DISTINCT s.course) FROM Student s")
    long countDistinctCourses();

    @Query("SELECT COUNT(DISTINCT s.department) FROM Student s")
    long countDistinctDepartments();

    // Top 5 recently added students
    List<Student> findTop5ByOrderByIdDesc();
}
