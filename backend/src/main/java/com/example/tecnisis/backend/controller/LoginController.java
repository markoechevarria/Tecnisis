package com.example.tecnisis.backend.controller;

import com.example.tecnisis.backend.dto.LoginDto;
import com.example.tecnisis.backend.entity.Usuario;
import com.example.tecnisis.backend.repository.UsuarioRepository;
import com.example.tecnisis.backend.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ApiResponse<LoginDto.Response> login(@RequestBody LoginDto.Request request) {
        try {
            log.info("Intento de login para usuario: {}", request.getEmail());
            
            // Buscar usuario por correo
            Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(request.getEmail());
            if (!usuarioOpt.isPresent()) {
                log.warn("Usuario no encontrado: {}", request.getEmail());
                return ApiResponse.error("Credenciales inválidas");
            }
            
            Usuario usuario = usuarioOpt.get();
            
            // Verificar si el usuario está activo
            if (usuario.getEstado() == null || !usuario.getEstado()) {
                log.warn("Usuario inactivo intenta hacer login: {}", request.getEmail());
                return ApiResponse.error("Usuario inactivo. Contacte al administrador.");
            }
            
            // Verificar contraseña (comparación simple)
            if (!request.getPassword().equals(usuario.getPasswordHash())) {
                log.warn("Contraseña incorrecta para usuario: {}", request.getEmail());
                return ApiResponse.error("Credenciales inválidas");
            }
            
            // Actualizar último acceso
            usuario.setUltimoAcceso(LocalDateTime.now());
            usuarioRepository.save(usuario);
            
            log.info("Login exitoso para usuario: {} ({})", request.getEmail(), usuario.getTipoUsuario());
            
            LoginDto.Response response = LoginDto.Response.success(
                usuario.getIdUsuario(),
                usuario.getCorreo(),
                usuario.getTipoUsuario()
            );
            
            return ApiResponse.success("Login exitoso", response);
            
        } catch (Exception e) {
            log.error("Error durante el login para {}: {}", request.getEmail(), e.getMessage(), e);
            return ApiResponse.error("Error interno del servidor");
        }
    }
    
    /**
     * Endpoint para verificar el estado de autenticación
     */
    @PostMapping("/verify")
    public ApiResponse<Usuario> verifyUser(@RequestParam String userId) {
        try {
            log.info("Verificando usuario: {}", userId);
            
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(userId);
            if (!usuarioOpt.isPresent()) {
                log.warn("Usuario no encontrado en verificación: {}", userId);
                return ApiResponse.error("Usuario no encontrado");
            }
            
            Usuario usuario = usuarioOpt.get();
            if (usuario.getEstado() == null || !usuario.getEstado()) {
                log.warn("Usuario inactivo en verificación: {}", userId);
                return ApiResponse.error("Usuario inactivo");
            }
            
            log.debug("Usuario verificado correctamente: {}", userId);
            return ApiResponse.success("Usuario verificado", usuario);
            
        } catch (Exception e) {
            log.error("Error en verificación de usuario {}: {}", userId, e.getMessage(), e);
            return ApiResponse.error("Error interno del servidor");
        }
    }
}