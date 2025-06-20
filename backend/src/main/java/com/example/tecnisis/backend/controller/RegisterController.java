package com.example.tecnisis.backend.controller;

import com.example.tecnisis.backend.common.ApiResponse;
import com.example.tecnisis.backend.dto.RegisterDto;
import com.example.tecnisis.backend.entity.Usuario;
import com.example.tecnisis.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegisterController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping("/register")
    public ApiResponse<RegisterDto.Response> register(@RequestBody RegisterDto.Request request) {
        try {
            // Verificar si el usuario ya existe
            if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
                return ApiResponse.error("El correo electrónico ya está registrado");
            }

            if (usuarioRepository.findByDni(request.getDni()).isPresent()) {
                return ApiResponse.error("El DNI ya está registrado");
            }

            // Crear nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(request.getNombre());
            nuevoUsuario.setDni(request.getDni());
            nuevoUsuario.setTelefono(request.getTelefono());
            nuevoUsuario.setCorreo(request.getCorreo());
            nuevoUsuario.setPasswordHash(request.getPassword()); // Sin encriptar por ahora
            nuevoUsuario.setTipoUsuario(request.getTipoUsuario() != null ? request.getTipoUsuario() : "ANFITRION");
            nuevoUsuario.setSexo(request.getSexo());
            nuevoUsuario.setDireccion(request.getDireccion());
            nuevoUsuario.setFechaRegistro(LocalDateTime.now());
            nuevoUsuario.setEstado(true);

            Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

            return ApiResponse.success(
                "Usuario registrado exitosamente",
                RegisterDto.Response.success("Usuario creado con ID: " + usuarioGuardado.getIdUsuario())
            );

        } catch (Exception e) {
            return ApiResponse.error("Error interno del servidor: " + e.getMessage());
        }
    }
    
    /**
     * Endpoint para verificar disponibilidad de correo
     */
    @GetMapping("/check-email/{email}")
    public ApiResponse<Boolean> checkEmailAvailability(@PathVariable String email) {
        try {
            // Verificar si el correo ya existe
            boolean disponible = !usuarioRepository.findByCorreo(email.toLowerCase().trim()).isPresent();
            
            return ApiResponse.success(
                disponible ? "Correo disponible" : "Correo no disponible", 
                disponible
            );
            
        } catch (Exception e) {
            return ApiResponse.error("Error al verificar disponibilidad de correo: " + e.getMessage());
        }
    }
    
    /**
     * Endpoint para verificar disponibilidad de DNI
     */
    @GetMapping("/check-dni/{dni}")
    public ApiResponse<Boolean> checkDniAvailability(@PathVariable String dni) {
        try {
            // Verificar si el DNI ya existe
            boolean disponible = !usuarioRepository.findByDni(dni.trim()).isPresent();
            
            return ApiResponse.success(
                disponible ? "DNI disponible" : "DNI no disponible", 
                disponible
            );
            
        } catch (Exception e) {
            return ApiResponse.error("Error al verificar disponibilidad de DNI: " + e.getMessage());
        }
    }
} 