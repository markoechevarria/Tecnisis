package com.example.tecnisis.backend.controller;

import com.example.tecnisis.backend.dto.LoginRequest;
import com.example.tecnisis.backend.dto.LoginResponse;
import com.example.tecnisis.backend.entity.Usuario;
import com.example.tecnisis.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Metodo para el login
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(request.getEmail());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            logger.info("Intento de login para correo: {}", request.getEmail());
            logger.debug("Contraseña recibida: {}", request.getPassword());
            logger.debug("Hash en BD: {}", usuario.getPassword_hash());
            boolean matches = passwordEncoder.matches(request.getPassword(), usuario.getPassword_hash());
            logger.info("¿Coincide la contraseña?: {}", matches);
            //AQUI SE RETORNAN ESTOS DATOS PARA EL USO DE LA PANTALLA INICIO DINAMICA
            if (matches) {
                return new LoginResponse(
                    true, 
                    usuario.getId_usuario(), 
                    usuario.getCorreo(), 
                    usuario.getTipo_usuario()
                );
            }
        } else {
            logger.info("Intento de login fallido: usuario no encontrado para correo {}", request.getEmail());
        }
        return new LoginResponse(false, null, null, null);
    }
}