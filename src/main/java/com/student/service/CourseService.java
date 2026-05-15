package com.student.service;

import com.student.entity.Course;
import com.student.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Page<Course> findPaginated(int pageNo, int pageSize, String keyword) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        
        if (keyword != null && !keyword.isEmpty()) {
            return courseRepository.findAll(keyword, pageable);
        }
        return courseRepository.findAll(pageable);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }
}
