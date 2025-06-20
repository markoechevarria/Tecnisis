package com.example.tecnisis.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad SolicitudEvaluacion que mapea exactamente la tabla 'solicitudes_evaluacion'
 */
@Entity
@Table(name = "solicitudes_evaluacion", indexes = {
    @Index(name = "idx_solicitudes_estado", columnList = "estado")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"evaluacionesArtisticas", "evaluacionesEconomicas"})
public class SolicitudEvaluacion {
    
    @Id
    @Column(name = "id_solicitud", length = 8)
    @EqualsAndHashCode.Include
    private String id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra")
    private ObraArte obra;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitante", referencedColumnName = "id_usuario")
    private Usuario solicitante;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluador_asignado", referencedColumnName = "id_usuario")
    private Usuario evaluadorAsignado;
    
    @Column(name = "estado", length = 50)
    private String estado;
    
    @Column(name = "fecha_solicitud", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_ultima_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluador_artistico", referencedColumnName = "id_usuario")
    private Usuario evaluadorArtistico;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluador_economico", referencedColumnName = "id_usuario")
    private Usuario evaluadorEconomico;
    
    @Column(name = "motivo_rechazo_artistico", columnDefinition = "TEXT")
    private String motivoRechazoArtistico;
    
    @Column(name = "motivo_rechazo_economico", columnDefinition = "TEXT")
    private String motivoRechazoEconomico;
    
    @Column(name = "porcentaje_ganancia", precision = 5, scale = 2)
    private BigDecimal porcentajeGanancia;
    
    // Relaciones con evaluaciones
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EvaluacionArtistica> evaluacionesArtisticas;
    
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EvaluacionEconomica> evaluacionesEconomicas;
    
    // === MÉTODOS DE COMPATIBILIDAD PARA DTOs ===
    
    public String getIdSolicitud() {
        return this.id;
    }
    
    public void setIdSolicitud(String id) {
        this.id = id;
    }
    
    public String getIdObra() {
        return obra != null ? obra.getIdObra() : null;
    }
    
    public LocalDateTime getFechaSolicitud() {
        return this.fechaCreacion;
    }
    
    public void setFechaSolicitud(LocalDateTime fecha) {
        this.fechaCreacion = fecha;
    }
    
    public LocalDateTime getFechaUltimaActualizacion() {
        return this.fechaActualizacion;
    }
    
    public void setFechaUltimaActualizacion(LocalDateTime fecha) {
        this.fechaActualizacion = fecha;
    }
    
    // === MÉTODOS PARA ASIGNACIÓN DE EVALUADORES ===
    
    public String getIdEvaluadorArtistico() {
        return evaluadorArtistico != null ? evaluadorArtistico.getIdUsuario() : null;
    }
    
    public void setIdEvaluadorArtistico(String idEvaluador) {
        if (idEvaluador != null) {
            Usuario evaluador = new Usuario();
            evaluador.setIdUsuario(idEvaluador);
            this.evaluadorArtistico = evaluador;
        } else {
            this.evaluadorArtistico = null;
        }
    }
    
    public String getIdEvaluadorEconomico() {
        return evaluadorEconomico != null ? evaluadorEconomico.getIdUsuario() : null;
    }
    
    public void setIdEvaluadorEconomico(String idEvaluador) {
        if (idEvaluador != null) {
            Usuario evaluador = new Usuario();
            evaluador.setIdUsuario(idEvaluador);
            this.evaluadorEconomico = evaluador;
        } else {
            this.evaluadorEconomico = null;
        }
    }
    
    // === CALLBACKS DE JPA ===
    
    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    @PrePersist
    protected void onCreate() {
        if (estado == null) {
            estado = "PENDIENTE_ARTISTICA";
        }
    }
} 