package com.example.tecnisis.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudEvaluacionDto {
    // Campos exactos de la tabla solicitudes_evaluacion
    private String idSolicitud;                     // id_solicitud (varchar(8)) - 0S00001
    private String idObra;                          // id_obra (varchar) - FK a obras_arte
    private String idSolicitante;                   // id_solicitante (varchar(6)) - FK a usuarios
    private String idEvaluadorAsignado;             // id_evaluador_asignado (varchar(6)) - FK a usuarios
    private String estado;                          // estado (varchar(50)) - PENDIENTE_ARTISTICA, etc.
    private LocalDateTime fechaSolicitud;           // fecha_solicitud (timestamp)
    private LocalDateTime fechaUltimaActualizacion; // fecha_ultima_actualizacion (timestamp)
    private String idEvaluadorArtistico;            // id_evaluador_artistico (varchar(6)) - FK a usuarios
    private String idEvaluadorEconomico;            // id_evaluador_economico (varchar(6)) - FK a usuarios
    private String motivoRechazoArtistico;          // motivo_rechazo_artistico (text)
    private String motivoRechazoEconomico;          // motivo_rechazo_economico (text)
    private BigDecimal porcentajeGanancia;          // porcentaje_ganancia (numeric(5,2))
} 