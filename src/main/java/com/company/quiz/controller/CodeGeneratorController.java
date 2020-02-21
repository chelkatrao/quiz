package com.company.quiz.controller;

import com.company.quiz.utils.CodeGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code-generator")
public class CodeGeneratorController {

    @PostMapping
    public String generateCode() {
        return CodeGenerator.generateCard(5);
    }

}
