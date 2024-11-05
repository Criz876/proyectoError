package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ACADEMICO"))) {
            return "redirect:/dashboard/academico";
        } else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"))) {
            return "redirect:/dashboard/estudiante";
        } else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_POLO"))) {
            return "redirect:/dashboard/polo";
        } else {
            return "redirect:/";
        }
    }
}