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
@Table(name = "academico")
public class Academico implements UserDetails{
    @Column(name = "id_academico")
    private int idAcademico;

    @Column(name = "nom_academico")
    private String nomAcademico;

    @Column(name = "correo_ubb")
    private String correoUbb;

    @Column(name = "contrasena_academico")
    private String contrasenaAcademico;


    private String departamento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdAcademico() {
        return idAcademico;
    }
    public void setIdAcademico(int idAcademico) {
        this.idAcademico = idAcademico;
    }
    public String getNomAcademico() {
        return nomAcademico;
    }
    public void setNomAcademico(String nomAcademico) {
        this.nomAcademico = nomAcademico;
    }
    public String getCorreoUbb() {
        return correoUbb;
    }
    public void setCorreoUbb(String correoUbb) {
        this.correoUbb = correoUbb;
    }
    public String getContrasenaAcademico() {
        return contrasenaAcademico;
    }
    public void setContrasenaAcademico(String contrasenaAcademico) {
        this.contrasenaAcademico = contrasenaAcademico;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ACADEMICO"));
    }

    @Override
    public String getPassword() {
        return this.contrasenaAcademico;
    }

    @Override
    public String getUsername() {
        return this.correoUbb;
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
