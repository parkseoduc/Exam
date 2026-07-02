package com.fpt.employees.controller;

import com.fpt.employees.model.Employee;
import com.fpt.employees.service.EmployeeNotFoundException;
import com.fpt.employees.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeWebController {

    private final EmployeeService employeeService;

    public EmployeeWebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String index(Model model) {
        prepareModel(model, new Employee(), new Employee());
        return "index";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@Valid @ModelAttribute("newEmployee") Employee employee,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareModel(model, employee, new Employee());
            return "index";
        }

        employeeService.addEmployees(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Employee added successfully.");
        return "redirect:/";
    }

    @PostMapping("/employees/update")
    public String updateEmployee(@Valid @ModelAttribute("updateEmployee") Employee employee,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareModel(model, new Employee(), employee);
            return "index";
        }

        try {
            employeeService.updateEmployee(employee);
            redirectAttributes.addFlashAttribute("successMessage", "Employee updated successfully.");
        } catch (EmployeeNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/";
    }

    private void prepareModel(Model model, Employee newEmployee, Employee updateEmployee) {
        model.addAttribute("employees", employeeService.getEmployees());
        model.addAttribute("newEmployee", newEmployee);
        model.addAttribute("updateEmployee", updateEmployee);
    }
}
