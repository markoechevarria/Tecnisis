package com.example.tecnisis.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionArtisticaDto {
    // Campos exactos de la tabla evaluaciones_artisticas
    private String idEvaluacionArtistica;    // id_evaluacion_artistica (varchar(9)) - 0EA00001
    
    private String idSolicitud;              // id_solicitud (varchar) - FK a solicitudes_evaluacion
    
    private String idEvaluadorArtistico;     // id_evaluador_artistico (varchar(6)) - FK a usuarios
    
    private Integer calificacion;            // calificacion (integer) - 0-100
    
    private String comentarios;              // comentarios (text)
    
    private Integer estado;                  // estado (integer) - 0=DESAPROBADA, 1=APROBADA, NULL=PENDIENTE
    private LocalDateTime fechaEvaluacion;   // fecha_evaluacion (timestamp)
    
    // Campos de referencia para facilitar uso
    private String nombreEvaluador;          // Para mostrar nombre del evaluador
    private String tituloObra;               // Para mostrar título de la obra
    
    // ===== CAMPOS DE CONVENIENCIA PARA EL FLUJO =====
    
    /**
     * Campo de conveniencia para manejar aprobación/desaprobación
     * "APROBADA", "DESAPROBADA", "PENDIENTE"
     */
    public String getEstadoTexto() {
        if (estado == null) return "PENDIENTE";
        return estado == 1 ? "APROBADA" : "DESAPROBADA";
    }
    
    /**
     * Setter de conveniencia para establecer estado desde texto
     */
    public void setEstadoTexto(String estadoTexto) {
        if (estadoTexto == null) {
            this.estado = null;
        } else {
            switch (estadoTexto.toUpperCase()) {
                case "APROBADA":
                    this.estado = 1;
                    break;
                case "DESAPROBADA":
                    this.estado = 0;
                    break;
                default:
                    this.estado = null;
                    break;
            }
        }
    }
    
    /**
     * Indica si la evaluación está aprobada
     */
    public boolean isAprobada() {
        return estado != null && estado == 1;
    }
    
    /**
     * Indica si la evaluación está desaprobada
     */
    public boolean isDesaprobada() {
        return estado != null && estado == 0;
    }
    
    /**
     * Indica si la evaluación está pendiente
     */
    public boolean isPendiente() {
        return estado == null;
    }
} 