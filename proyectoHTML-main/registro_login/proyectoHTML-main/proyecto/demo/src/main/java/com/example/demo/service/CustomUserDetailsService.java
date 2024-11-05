package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Academico;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Polo;
import com.example.demo.repository.AcademicoRepository;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.repository.PoloRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AcademicoRepository academicoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PoloRepository poloRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Academico academico = academicoRepository.findByCorreoUbb(username);
        if (academico != null) {
            return academico;
        }

        Estudiante estudiante = estudianteRepository.findByCorreoEstudiante(username);
        if (estudiante != null) {
            return estudiante;
        }

        Polo polo = poloRepository.findByCorreoPolo(username);
        if (polo != null) {
            return polo;
        }

        throw new UsernameNotFoundException("Usuario no encontrado");
    }
}
