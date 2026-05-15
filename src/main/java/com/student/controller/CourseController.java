package com.student.controller;

import com.student.entity.Course;
import com.student.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String listCourses(Model model, 
                               @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "keyword", required = false) String keyword) {
        int pageSize = 10;
        Page<Course> page = courseService.findPaginated(pageNo, pageSize, keyword);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("courses", listCourses);
        
        return "courses";
    }

    @GetMapping("/new")
    public String createCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "create_course";
    }

    @PostMapping
    public String saveCourse(@Valid @ModelAttribute("course") Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create_course";
        }
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "edit_course";
    }

    @PostMapping("/{id}")
    public String updateCourse(@PathVariable Long id, @Valid @ModelAttribute("course") Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_course";
        }
        
        Course existingCourse = courseService.getCourseById(id);
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setCredits(course.getCredits());

        courseService.updateCourse(existingCourse);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }
}
