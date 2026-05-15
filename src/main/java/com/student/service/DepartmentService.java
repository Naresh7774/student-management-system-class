package com.student.service;

import com.student.entity.Department;
import com.student.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Page<Department> findPaginated(int pageNo, int pageSize, String keyword) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        
        if (keyword != null && !keyword.isEmpty()) {
            return departmentRepository.findAll(keyword, pageable);
        }
        return departmentRepository.findAll(pageable);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
