package com.company.quiz.controller.swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AppController {
    @GetMapping
    public String swagger() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>REST API LIST</title>\n" +
                "    <meta http-equiv=\"refresh\" content=\"0; url=/swagger-ui.html\" />\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/swagger-ui.html\">REST API LIST</a>\n" +
                "</body>\n" +
                "</html>";
    }
}
