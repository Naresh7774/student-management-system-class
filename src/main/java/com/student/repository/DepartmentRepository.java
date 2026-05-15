package com.student.repository;

import com.student.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    @Query("SELECT d FROM Department d WHERE CONCAT(d.name, d.headOfDepartment) LIKE %?1%")
    Page<Department> findAll(String keyword, Pageable pageable);
}
