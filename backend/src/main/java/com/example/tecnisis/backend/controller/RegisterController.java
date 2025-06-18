package com.example.tecnisis.backend.controller;

import com.example.tecnisis.backend.dto.RegisterRequest;
import com.example.tecnisis.backend.dto.RegisterResponse;
import com.example.tecnisis.backend.entity.Usuario;
import com.example.tecnisis.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
//Esto de aqui es para que encripte la contraseña
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        // Validar correo único
        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            return new RegisterResponse(false, "El correo ya está registrado");
        }
        // Validar dni único
        // (Agrega un método findByDni si no existe)
        if (usuarioRepository.findByDni(request.getDni()).isPresent()) {
            return new RegisterResponse(false, "El DNI ya está registrado");
        }
        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setDni(request.getDni());
        usuario.setTelefono(request.getTelefono());
        usuario.setCorreo(request.getCorreo());
        usuario.setPassword_hash(passwordEncoder.encode(request.getPassword()));
        usuario.setTipo_usuario("ARTISTA");
        usuario.setEstado(true);
        usuarioRepository.save(usuario);
        return new RegisterResponse(true, "Usuario registrado exitosamente");
    }
} 