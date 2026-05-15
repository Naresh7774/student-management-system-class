package com.student.service.impl;

import com.student.entity.Student;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // Constructor based Dependency Injection
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        // Find student by ID or throw exception (basic handling)
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize, String keyword) {
        Sort sort = Sort.by("id").descending(); // Sort by ID descending for newest first
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        
        if (keyword != null && !keyword.isEmpty()) {
            return studentRepository.findAll(keyword, pageable);
        }
        return studentRepository.findAll(pageable);
    }

    @Override
    public long getTotalStudents() {
        return studentRepository.count();
    }

    @Override
    public long getTotalCourses() {
        return studentRepository.countDistinctCourses();
    }

    @Override
    public long getTotalDepartments() {
        return studentRepository.countDistinctDepartments();
    }

    @Override
    public List<Student> getRecentStudents() {
        return studentRepository.findTop5ByOrderByIdDesc();
    }
}
