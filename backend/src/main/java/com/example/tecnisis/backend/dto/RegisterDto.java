package com.example.tecnisis.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class RegisterDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String nombre;
        private String dni;
        private String telefono;
        private String correo;
        private String password;
        private String tipoUsuario;
        private String sexo;
        private String direccion;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private boolean success;
        private String message;
        
        public static Response success(String message) {
            return new Response(true, message);
        }

        public static Response error(String message) {
            return new Response(false, message);
        }
    }
} 