package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toArray()[0].toString(); // Obtener el rol del usuario

        switch (role) {
            case "ROLE_ACADEMICO":
                return "dashboard_academico"; // Vista para académicos
            case "ROLE_ESTUDIANTE":
                return "dashboard_estudiante"; // Vista para estudiantes
            case "ROLE_POLO":
                return "dashboard_polo"; // Vista para polos
            default:
                return "error"; // O una página de error
        }
    }
}
