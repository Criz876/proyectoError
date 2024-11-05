package com.example.demo.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante")
public class Estudiante implements UserDetails{
    @Column(name = "id_estudiante")
    private int idEstudiante;

    @Column(name = "nombre_estudiante")
    private String nombreEstudiante;

    @Column(name = "correo_estudiante")
    private String correoEstudiante;

    @Column(name = "contrasena_estudiante")
    private String contrasenaEstudiante;

    @Column(name = "carrera_estudiante")
    private String carreraEstudiante;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdEstudiante() {
        return idEstudiante;
    }
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
    public String getNombreEstudiante() {
        return nombreEstudiante;
    }
    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }
    public String getCorreoEstudiante() {
        return correoEstudiante;
    }
    public void setCorreoEstudiante(String correoEstudiante) {
        this.correoEstudiante = correoEstudiante;
    }
    public String getCarreraEstudiante() {
        return carreraEstudiante;
    }
    public void setCarreraEstudiante(String carreraEstudiante) {
        this.carreraEstudiante = carreraEstudiante;
    }

    public String getContrasenaEstudiante() {
        return contrasenaEstudiante;
    }

    public void setContrasenaEstudiante(String contrasenaEstudiante) {
        this.contrasenaEstudiante = contrasenaEstudiante;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ACADEMICO"));
    }

    @Override
    public String getPassword() {
        return this.contrasenaEstudiante;
    }

    @Override
    public String getUsername() {
        return this.correoEstudiante;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
