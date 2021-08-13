package com.broadway.springdemo.controller;

import com.broadway.springdemo.model.Employee;
import com.broadway.springdemo.repository.EmployeeRepository;
import com.broadway.springdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public String getEmployeeForm(Model model, HttpSession session){

        //        check session/valid user

        if(session.getAttribute("user")==null){
            return "loginForm";
        }


        model.addAttribute("emodel",new Employee());

        return "employeeForm";

    }

    @PostMapping("/employee")
    public String postEmployeeForm(@ModelAttribute Employee e, HttpSession session){

        //        check session/valid user

        if(session.getAttribute("user")==null){
            return "loginForm";
        }

        employeeRepository.save(e);

        return "redirect:/employee";
    }

    @GetMapping("/list")
    public String getAllEmployee(Model model, HttpSession session){

        //        check session/valid user

        if(session.getAttribute("user")==null){
            return "loginForm";
        }


        List<Employee> emplist= employeeService.getAll();
        model.addAttribute("elist", emplist);

        return "employeeList";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id, HttpSession session){

        //        check session/valid user

        if(session.getAttribute("user")==null){
            return "loginForm";
        }

        employeeRepository.deleteById(id);

        return "redirect:/list";

    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, Model model,HttpSession session){

        //        check session/valid user

        if(session.getAttribute("user")==null){
            return "loginForm";
        }

        model.addAttribute("emodel", employeeRepository.getOne(id));

        return "editForm";

    }

    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee employee, HttpSession session){

        //        check session/valid user

        if(session.getAttribute("user")==null){
            return "loginForm";
        }


        employeeRepository.save(employee);

        return "redirect:/list";
    }

}
