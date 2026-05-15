package com.student.repository;

import com.student.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
    @Query("SELECT c FROM Course c WHERE CONCAT(c.name, c.description) LIKE %?1%")
    Page<Course> findAll(String keyword, Pageable pageable);
}
