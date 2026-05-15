package com.student.service;

import com.student.entity.Student;
import java.util.List;

public interface StudentService {
    
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
