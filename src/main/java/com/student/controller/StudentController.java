package com.student.controller;

import com.student.entity.Student;
import com.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Root mapping to dashboard
    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", studentService.getTotalStudents());
        model.addAttribute("totalCourses", studentService.getTotalCourses());
        model.addAttribute("totalDepartments", studentService.getTotalDepartments());
        model.addAttribute("recentStudents", studentService.getRecentStudents());
        return "dashboard";
    }

    // Handler method to handle list students with pagination and search
    @GetMapping("/students")
    public String listStudents(Model model, 
                               @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "keyword", required = false) String keyword) {
        int pageSize = 10;
        Page<Student> page = studentService.findPaginated(pageNo, pageSize, keyword);
        List<Student> listStudents = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("students", listStudents);
        
        return "students"; // returns students.html
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        // Create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student"; // returns create_student.html
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create_student"; // Return to form if validation fails
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student"; // returns edit_student.html
    }

    @PostMapping("/students/{id}")
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
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
