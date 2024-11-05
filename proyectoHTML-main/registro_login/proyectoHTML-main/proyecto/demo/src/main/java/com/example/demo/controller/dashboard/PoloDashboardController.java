package com.example.demo.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoloDashboardController {

    @GetMapping("/dashboard/polo")
    public String dashboardPolo() {
        return "dashboard_polo";
    }
}