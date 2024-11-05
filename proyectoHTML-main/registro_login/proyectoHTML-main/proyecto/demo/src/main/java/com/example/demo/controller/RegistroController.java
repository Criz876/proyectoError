package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Academico;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Polo;
import com.example.demo.service.AcademicoService;
import com.example.demo.service.EstudianteService;
import com.example.demo.service.PoloService;

@Controller
public class RegistroController {

    @Autowired
    private AcademicoService academicoService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private PoloService poloService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    @ResponseBody
    public ResponseEntity<?> registrarUsuario(@RequestBody Map<String, String> datos) {
        String tipoUsuario = datos.get("tipoUsuario");
        String contrasenaEncriptada = passwordEncoder.encode(datos.get("contrasena"));
        
        try {
            switch (tipoUsuario) {
                case "academico":
                    Academico academico = new Academico();
                    academico.setNomAcademico(datos.get("nombre"));
                    academico.setCorreoUbb(datos.get("correo"));
                    academico.setContrasenaAcademico(contrasenaEncriptada);
                    academico.setDepartamento(datos.get("departamento"));
                    academicoService.registrarAcademico(academico);
                    break;
                case "estudiante":
                    Estudiante estudiante = new Estudiante();
                    estudiante.setNombreEstudiante(datos.get("nombre"));
                    estudiante.setCorreoEstudiante(datos.get("correo"));
                    estudiante.setContrasenaEstudiante(contrasenaEncriptada);
                    estudianteService.registrarEstudiante(estudiante);
                    break;
                case "polo":
                    Polo polo = new Polo();
                    polo.setNombrePolo(datos.get("nombre"));
                    polo.setCorreoPolo(datos.get("correo"));
                    polo.setContrasenaPolo(contrasenaEncriptada);
                    poloService.registrarPolo(polo);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Tipo de usuario no válido");
            }
            return ResponseEntity.ok("Registro exitoso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar usuario: " + e.getMessage());
        }
    }
}