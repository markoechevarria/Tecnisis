package com.example.tecnisis.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionEconomicaDto {
    // Campos exactos de la tabla evaluaciones_economicas
    private String idEvaluacionEconomica;    // id_evaluacion_economica (varchar(9)) - 0EE00001
    
    private String idSolicitud;              // id_solicitud (varchar) - FK a solicitudes_evaluacion
    
    private String idEvaluadorEconomico;     // id_evaluador_economico (varchar(6)) - FK a usuarios
    
    private BigDecimal valoracionEconomica;  // valoracion_economica (numeric(10,2))
    
    private Boolean rentabilidad;            // rentabilidad (boolean) - true=RENTABLE, false=NO_RENTABLE, null=NO_EVALUADA
    
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
     * Campo de conveniencia para rentabilidad
     * "RENTABLE", "NO_RENTABLE", "NO_EVALUADA"
     */
    public String getRentabilidadTexto() {
        if (rentabilidad == null) return "NO_EVALUADA";
        return rentabilidad ? "RENTABLE" : "NO_RENTABLE";
    }
    
    /**
     * Setter de conveniencia para establecer rentabilidad desde texto
     */
    public void setRentabilidadTexto(String rentabilidadTexto) {
        if (rentabilidadTexto == null) {
            this.rentabilidad = null;
        } else {
            switch (rentabilidadTexto.toUpperCase()) {
                case "RENTABLE":
                    this.rentabilidad = true;
                    break;
                case "NO_RENTABLE":
                    this.rentabilidad = false;
                    break;
                default:
                    this.rentabilidad = null;
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
    
    /**
     * Indica si es rentable
     */
    public boolean isRentable() {
        return rentabilidad != null && rentabilidad;
    }
    
    /**
     * Indica si no es rentable
     */
    public boolean isNoRentable() {
        return rentabilidad != null && !rentabilidad;
    }
} 