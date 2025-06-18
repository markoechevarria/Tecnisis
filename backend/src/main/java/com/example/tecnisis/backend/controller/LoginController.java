package com.example.tecnisis.backend.controller;

import com.example.tecnisis.backend.entity.Usuario;
import com.example.tecnisis.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Metodo para el login
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(request.getEmail());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword_hash().equals(request.getPassword())) {
                return new LoginResponse(true, usuario.getId_usuario(), usuario.getCorreo(), usuario.getTipo_usuario());
            }
        }
        return new LoginResponse(false, null, null, null);
    }

    //Clase para el request del login (LO QUE RECIVE DATOS PARA COMPROBARLOS CON LA BD)
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    //Clase para la respuesta del login
    public static class LoginResponse {
        private boolean success;
        private String id_usuario;
        private String correo;
        private String tipo_usuario;

        public LoginResponse(boolean success, String id_usuario, String correo, String tipo_usuario) {
            this.success = success;
            this.id_usuario = id_usuario;
            this.correo = correo;
            this.tipo_usuario = tipo_usuario;
        }

        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        public String getId_usuario() { return id_usuario; }
        public void setId_usuario(String id_usuario) { this.id_usuario = id_usuario; }
        public String getCorreo() { return correo; }
        public void setCorreo(String correo) { this.correo = correo; }
        public String getTipo_usuario() { return tipo_usuario; }
        public void setTipo_usuario(String tipo_usuario) { this.tipo_usuario = tipo_usuario; }
    }
}