package com.example.tecnisis.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class LoginDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String email;
        private String password;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private boolean success;
        private String idUsuario;
        private String correo;
        private String tipoUsuario;
        private String mensaje;
        
        public static Response success(String idUsuario, String correo, String tipoUsuario) {
            return new Response(true, idUsuario, correo, tipoUsuario, "Login exitoso");
        }
        
        public static Response error(String mensaje) {
            return new Response(false, null, null, null, mensaje);
        }
        
        public static Response error() {
            return new Response(false, null, null, null, "Credenciales inválidas");
        }
    }
} 