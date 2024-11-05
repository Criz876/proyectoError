package com.example.demo.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EstudianteDashboardController {

    @GetMapping("/dashboard/estudiante")
    public String dashboardEstudiante() {
        return "dashboard_estudiante";
    }
}