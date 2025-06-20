package com.example.tecnisis.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluaciones_artisticas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionArtistica {
    
    @Id
    @Column(name = "id_evaluacion_artistica")
    private String id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud")
    private SolicitudEvaluacion solicitud;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluador_artistico", referencedColumnName = "id_usuario")
    private Usuario evaluador;
    
    @Column(name = "calificacion")
    private Integer calificacion;
    
    @Column(name = "comentarios", columnDefinition = "TEXT")
    private String comentarios;
    
    @Column(name = "estado")
    private Integer estado;
    
    @Column(name = "fecha_evaluacion", insertable = false, updatable = false)
    private LocalDateTime fechaEvaluacion;
    
    // Métodos de compatibilidad para DTOs y mappers
    public String getIdEvaluacionArtistica() {
        return this.id;
    }
    
    public void setIdEvaluacionArtistica(String id) {
        this.id = id;
    }
    
    // Métodos de conveniencia para la compatibilidad con el servicio
    public Integer getPuntuacionTecnica() {
        // Dividir la calificación en componentes (simulación)
        return calificacion != null ? (calificacion * 30 / 100) : null;
    }
    
    public Integer getPuntuacionCreatividad() {
        return calificacion != null ? (calificacion * 35 / 100) : null;
    }
    
    public Integer getPuntuacionOriginalidad() {
        return calificacion != null ? (calificacion * 35 / 100) : null;
    }
    
    public Integer getPuntuacionTotal() {
        return calificacion;
    }
    
    public ResultadoEvaluacion getResultado() {
        if (estado == null) return null;
        return estado == 1 ? ResultadoEvaluacion.APROBADA : ResultadoEvaluacion.RECHAZADA;
    }
    
    public enum ResultadoEvaluacion {
        APROBADA, RECHAZADA
    }
} 