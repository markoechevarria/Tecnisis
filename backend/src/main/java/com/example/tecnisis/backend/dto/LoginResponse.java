package com.example.tecnisis.backend.dto;

public class LoginResponse {
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