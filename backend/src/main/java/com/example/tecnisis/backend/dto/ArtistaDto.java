package com.example.tecnisis.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistaDto {
    // Campos de la tabla artistas
    private String idArtista;        // id_artista (varchar(8)) - 0A00001
    private String idUsuario;        // id_usuario (varchar(6)) - 000001
    private String biografia;        // biografia (text)
    private LocalDateTime fechaRegistro; // fecha_registro (timestamp)
    
    // Campos del usuario asociado (para facilitar uso)
    private String nombre;           // usuarios.nombre
    private String dni;              // usuarios.dni
    private String telefono;         // usuarios.telefono
    private String correo;           // usuarios.correo
    private String tipoUsuario;      // usuarios.tipo_usuario
    private Boolean estado;          // usuarios.estado
    private String sexo;             // usuarios.sexo
    private String direccion;        // usuarios.direccion
} 