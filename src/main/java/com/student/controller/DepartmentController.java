package com.student.controller;

import com.student.entity.Department;
import com.student.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String listDepartments(Model model, 
                               @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "keyword", required = false) String keyword) {
        int pageSize = 10;
        Page<Department> page = departmentService.findPaginated(pageNo, pageSize, keyword);
        List<Department> listDepartments = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("departments", listDepartments);
        
        return "departments";
    }

    @GetMapping("/new")
    public String createDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "create_department";
    }

    @PostMapping
    public String saveDepartment(@Valid @ModelAttribute("department") Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create_department";
        }
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String editDepartmentForm(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentService.getDepartmentById(id));
        return "edit_department";
    }

    @PostMapping("/{id}")
    public String updateDepartment(@PathVariable Long id, @Valid @ModelAttribute("department") Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit_department";
        }
        
        Department existingDepartment = departmentService.getDepartmentById(id);
        existingDepartment.setName(department.getName());
        existingDepartment.setDescription(department.getDescription());
        existingDepartment.setHeadOfDepartment(department.getHeadOfDepartment());

        departmentService.updateDepartment(existingDepartment);
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }
}
