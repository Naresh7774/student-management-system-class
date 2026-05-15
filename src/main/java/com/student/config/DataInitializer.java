package com.student.config;

import com.student.entity.Student;
import com.student.entity.Course;
import com.student.entity.Department;
import com.student.repository.StudentRepository;
import com.student.repository.CourseRepository;
import com.student.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(StudentRepository studentRepository, CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        return args -> {
            if (studentRepository.count() == 0) {
                studentRepository.saveAll(Arrays.asList(
                    new Student(null, "John Doe", "john.doe@example.com", "Computer Science", "Engineering", "123-456-7890"),
                    new Student(null, "Jane Smith", "jane.smith@example.com", "Business Admin", "Business", "123-456-7891"),
                    new Student(null, "Alice Johnson", "alice.j@example.com", "Data Science", "Engineering", "123-456-7892"),
                    new Student(null, "Bob Williams", "bob.w@example.com", "Marketing", "Business", "123-456-7893"),
                    new Student(null, "Charlie Brown", "charlie.b@example.com", "Computer Science", "Engineering", "123-456-7894"),
                    new Student(null, "Diana Prince", "diana.p@example.com", "Psychology", "Arts", "123-456-7895"),
                    new Student(null, "Evan Davis", "evan.d@example.com", "Physics", "Science", "123-456-7896"),
                    new Student(null, "Fiona Garcia", "fiona.g@example.com", "Mathematics", "Science", "123-456-7897"),
                    new Student(null, "George Miller", "george.m@example.com", "History", "Arts", "123-456-7898"),
                    new Student(null, "Hannah Wilson", "hannah.w@example.com", "Data Science", "Engineering", "123-456-7899"),
                    new Student(null, "Ian Taylor", "ian.t@example.com", "Marketing", "Business", "123-456-7800"),
                    new Student(null, "Julia Anderson", "julia.a@example.com", "Computer Science", "Engineering", "123-456-7801")
                ));
            }
            
            if (courseRepository.count() == 0) {
                courseRepository.saveAll(Arrays.asList(
                    new Course(null, "Introduction to Programming", "Learn the basics of Java and Python.", 3),
                    new Course(null, "Calculus I", "Limits, derivatives, integrals, and the fundamental theorem.", 4),
                    new Course(null, "Database Systems", "SQL, relational models, and database design.", 3),
                    new Course(null, "Web Development", "HTML, CSS, JavaScript, and Spring Boot.", 3)
                ));
            }
            
            if (departmentRepository.count() == 0) {
                departmentRepository.saveAll(Arrays.asList(
                    new Department(null, "Engineering", "Computer Science, Software Engineering, IT", "Dr. Alan Turing"),
                    new Department(null, "Science", "Mathematics, Physics, Chemistry, Biology", "Dr. Marie Curie"),
                    new Department(null, "Business", "Administration, Accounting, Finance", "Mr. Warren Buffett"),
                    new Department(null, "Arts", "Literature, History, Philosophy", "Dr. William Shakespeare")
                ));
            }
        };
    }
}
