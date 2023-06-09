package com.crud.controller;

import com.crud.model.StudentInfo;
import com.crud.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/create-view")
    public String create(Model model) {
        return "create";
    }

    @PostMapping("/create")
    public String create(@RequestParam(name = "name") String name,
                         @RequestParam(name = "email") String email,
                         HttpServletRequest servlet,
                         Model model) {
        log.info("name: {}, email: {}", name, email);
        return "create";
    }

    @GetMapping("/read")
    public String read(Long id, Model model) {
        System.out.println(id);
        model.addAttribute("student",
                studentService.findById(id)
        );
        return "read";
    }

    @PostMapping("/update")
    public String updateView() {

        return "home";
    }
}
