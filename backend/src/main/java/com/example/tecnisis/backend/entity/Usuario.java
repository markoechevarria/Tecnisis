package com.example.tecnisis.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "fecha_registro", insertable = false, updatable = false)
    private String id_usuario;

    private String nombre;
    private String dni;
    private String telefono;
    private String correo;
    private String password_hash;
    private String tipo_usuario;
    private LocalDateTime fecha_registro;
    private Boolean estado;

}