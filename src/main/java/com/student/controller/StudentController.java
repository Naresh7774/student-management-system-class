package com.student.controller;

import com.student.entity.Student;
import com.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Handler method to handle list students and return mode and view
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; // returns students.html
    }

    @GetMapping("/new")
    public String createStudentForm(Model model) {
        // Create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student"; // returns create_student.html
    }

    @PostMapping
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create_student"; // Return to form if validation fails
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student"; // returns edit_student.html
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_student";
        }
        
        // Get student from database by id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setPhoneNumber(student.getPhoneNumber());

        // Save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    // Handler method to handle delete student request
    @GetMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
