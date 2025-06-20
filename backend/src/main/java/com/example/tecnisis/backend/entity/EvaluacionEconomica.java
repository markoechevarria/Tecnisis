package com.example.tecnisis.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "evaluaciones_economicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionEconomica {
    
    @Id
    @Column(name = "id_evaluacion_economica")
    private String id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud")
    private SolicitudEvaluacion solicitud;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluador_economico", referencedColumnName = "id_usuario")
    private Usuario evaluador;
    
    @Column(name = "valoracion_economica", precision = 10, scale = 2)
    private BigDecimal valoracionEconomica;
    
    @Column(name = "rentabilidad")
    private Boolean rentabilidad;
    
    @Column(name = "comentarios", columnDefinition = "TEXT")
    private String comentarios;
    
    @Column(name = "estado")
    private Integer estado;
    
    @Column(name = "fecha_evaluacion", insertable = false, updatable = false)
    private LocalDateTime fechaEvaluacion;
    
    // Métodos de compatibilidad para DTOs y mappers
    public String getIdEvaluacionEconomica() {
        return this.id;
    }
    
    public void setIdEvaluacionEconomica(String id) {
        this.id = id;
    }
    
    // Métodos de conveniencia para la compatibilidad con el servicio
    public BigDecimal getPresupuestoEstimado() {
        return valoracionEconomica;
    }
    
    public String getViabilidadEconomica() {
        return rentabilidad != null ? (rentabilidad ? "VIABLE" : "NO_VIABLE") : null;
    }
    
    public BigDecimal getRetornoInversion() {
        // Calcular retorno basado en valoración económica
        return valoracionEconomica != null ? valoracionEconomica.multiply(new BigDecimal("0.15")) : null;
    }
    
    public String getAnalisisCosto() {
        return comentarios;
    }
    
    public String getRecomendacionEconomica() {
        if (estado == null) return "PENDIENTE";
        return estado == 1 ? "APROBADA" : "RECHAZADA";
    }
    
    public ResultadoEvaluacion getResultado() {
        if (estado == null) return null;
        return estado == 1 ? ResultadoEvaluacion.APROBADA : ResultadoEvaluacion.RECHAZADA;
    }
    
    public Integer getPuntuacionTotal() {
        // Calcular puntuación basada en valoración económica
        if (valoracionEconomica == null) return null;
        return valoracionEconomica.intValue() / 100; // Normalizar a escala 0-100
    }
    
    public enum ResultadoEvaluacion {
        APROBADA, RECHAZADA
    }
} 