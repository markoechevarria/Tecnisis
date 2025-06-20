package com.example.tecnisis.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "obras_arte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObraArte {
    
    @Id
    @Column(name = "id_obra")
    private String idObra;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artista", referencedColumnName = "id_artista")
    private Artista artista;
    
    @Column(name = "titulo", length = 200, nullable = false)
    private String titulo;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "dimensiones", length = 50)
    private String dimensiones;
    
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    
    @Column(name = "precio_estimado", precision = 10, scale = 2)
    private BigDecimal precioEstimado;
    
    @Column(name = "id_tecnica")
    private String idTecnica;
    
    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;
    
    @Column(name = "registrado_por_usuario")
    private String registradoPorUsuario;
    
    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;
    
    @Column(name = "estado")
    private Boolean estado = true;
} 