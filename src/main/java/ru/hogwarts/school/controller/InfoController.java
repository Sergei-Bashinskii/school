/*package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("info")
@Tag(name = "API информации")
public class InfoController {

    @Autowired
    private String portValue;

    @GetMapping("/port")
    public String getServerPort() {
        return "Приложение запущено на порту: " + portValue;
    }
}*/