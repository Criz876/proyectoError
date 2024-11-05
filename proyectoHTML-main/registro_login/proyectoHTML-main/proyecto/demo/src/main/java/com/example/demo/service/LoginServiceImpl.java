package com.example.demo.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Academico;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Polo;
import com.example.demo.repository.AcademicoRepository;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.repository.PoloRepository;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

    @Autowired
    private AcademicoRepository academicoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PoloRepository poloRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Academico academico = academicoRepository.findByCorreoUbb(username);
        if (academico != null) {
            return new org.springframework.security.core.userdetails.User(
                academico.getCorreoUbb(),
                academico.getContrasenaAcademico(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ACADEMICO"))
            );
        }

        Estudiante estudiante = estudianteRepository.findByCorreoEstudiante(username);
        if (estudiante != null) {
            return new org.springframework.security.core.userdetails.User(
                estudiante.getCorreoEstudiante(),
                estudiante.getContrasenaEstudiante(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"))
            );
        }

        Polo polo = poloRepository.findByCorreoPolo(username);
        if (polo != null) {
            return new org.springframework.security.core.userdetails.User(
                polo.getCorreoPolo(),
                polo.getContrasenaPolo(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_POLO"))
            );
        }

        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }

    @Override
    public String autenticarUsuario(String correo, String contrasena) throws Exception {
        UserDetails userDetails = loadUserByUsername(correo);
        if (passwordEncoder.matches(contrasena, userDetails.getPassword())) {
            return userDetails.getAuthorities().iterator().next().getAuthority().substring(5).toLowerCase();
        }
        throw new Exception("Credenciales inválidas");
    }
}