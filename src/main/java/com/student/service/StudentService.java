package com.student.service;

import com.student.entity.Student;
import java.util.List;
import org.springframework.data.domain.Page;

public interface StudentService {
    
    // Method to get paginated students with optional search
    Page<Student> findPaginated(int pageNo, int pageSize, String keyword);
    
    // Dashboard analytics
    long getTotalStudents();
    long getTotalCourses();
    long getTotalDepartments();
    List<Student> getRecentStudents();
    
    // Method to get all students
    List<Student> getAllStudents();
    
    // Method to save a student
    Student saveStudent(Student student);
    
    // Method to get student by id
    Student getStudentById(Long id);
    
    // Method to update a student
    Student updateStudent(Student student);
    
    // Method to delete a student by id
    void deleteStudentById(Long id);
}
