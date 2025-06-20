package com.example.tecnisis.backend.controller;

import com.example.tecnisis.backend.common.ApiResponse;
import com.example.tecnisis.backend.dto.SolicitudEvaluacionDto;
import com.example.tecnisis.backend.entity.SolicitudEvaluacion;
import com.example.tecnisis.backend.mapper.SolicitudEvaluacionMapper;
import com.example.tecnisis.backend.repository.SolicitudEvaluacionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
@RequiredArgsConstructor
public class SolicitudEvaluacionController {
    
    @Autowired
    private SolicitudEvaluacionRepository solicitudRepository;
    
    @Autowired
    private SolicitudEvaluacionMapper solicitudMapper;
    

    
    /**
     * Obtener todas las solicitudes (solo admin/gerente)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<SolicitudEvaluacionDto>>> getAllSolicitudes() {
        try {
            log.info("Obteniendo todas las solicitudes de evaluación");
            List<SolicitudEvaluacion> solicitudes = solicitudRepository.findAllWithDetails();
            List<SolicitudEvaluacionDto> solicitudesDto = solicitudMapper.toDtoList(solicitudes);
            
            log.info("Se obtuvieron {} solicitudes", solicitudesDto.size());
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Solicitudes obtenidas exitosamente",
                solicitudesDto
            ));
        } catch (Exception e) {
            log.error("Error al obtener solicitudes: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener solicitudes: " + e.getMessage(), null));
        }
    }
    
    /**
     * Obtener una solicitud por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SolicitudEvaluacionDto>> getSolicitudById(@PathVariable String id) {
        try {
            log.info("Obteniendo solicitud con ID: {}", id);
            Optional<SolicitudEvaluacion> solicitud = solicitudRepository.findByIdWithDetails(id);
            
            if (solicitud.isPresent()) {
                SolicitudEvaluacionDto solicitudDto = solicitudMapper.toDto(solicitud.get());
                return ResponseEntity.ok(new ApiResponse<>(
                    true,
                    "Solicitud encontrada exitosamente",
                    solicitudDto
                ));
            } else {
                log.warn("Solicitud no encontrada con ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Solicitud no encontrada", null));
            }
        } catch (Exception e) {
            log.error("Error al obtener solicitud: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener solicitud: " + e.getMessage(), null));
        }
    }
    
    /**
     * Obtener solicitudes por estado
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<ApiResponse<List<SolicitudEvaluacionDto>>> getSolicitudesByEstado(@PathVariable String estado) {
        try {
            log.info("Obteniendo solicitudes con estado: {}", estado);
            List<SolicitudEvaluacion> solicitudes = solicitudRepository.findByEstadoWithDetails(estado.toUpperCase());
            List<SolicitudEvaluacionDto> solicitudesDto = solicitudMapper.toDtoList(solicitudes);
            
            log.info("Se encontraron {} solicitudes con estado '{}'", solicitudesDto.size(), estado);
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Solicitudes obtenidas exitosamente",
                solicitudesDto
            ));
        } catch (Exception e) {
            log.error("Error al obtener solicitudes por estado: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener solicitudes: " + e.getMessage(), null));
        }
    }
    
    /**
     * Obtener solicitudes de un artista específico
     */
    @GetMapping("/artista/{artistaId}")
    public ResponseEntity<ApiResponse<List<SolicitudEvaluacionDto>>> getSolicitudesByArtista(@PathVariable String artistaId) {
        try {
            log.info("Obteniendo solicitudes para artista: {}", artistaId);
            List<SolicitudEvaluacion> solicitudes = solicitudRepository.findByArtistaIdWithDetails(artistaId);
            List<SolicitudEvaluacionDto> solicitudesDto = solicitudMapper.toDtoList(solicitudes);
            
            log.info("Se encontraron {} solicitudes para el artista '{}'", solicitudesDto.size(), artistaId);
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Solicitudes obtenidas exitosamente",
                solicitudesDto
            ));
        } catch (Exception e) {
            log.error("Error al obtener solicitudes por artista: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener solicitudes: " + e.getMessage(), null));
        }
    }
    
    /**
     * Obtener solicitudes para evaluador artístico
     */
    @GetMapping("/evaluador-artistico/{evaluadorId}")
    public ResponseEntity<ApiResponse<List<SolicitudEvaluacionDto>>> getSolicitudesForEvaluadorArtistico(@PathVariable String evaluadorId) {
        try {
            log.info("Obteniendo solicitudes para evaluador artístico: {}", evaluadorId);
            List<SolicitudEvaluacion> solicitudes = solicitudRepository.findForEvaluadorArtistico(evaluadorId);
            List<SolicitudEvaluacionDto> solicitudesDto = solicitudMapper.toDtoList(solicitudes);
            
            log.info("Se encontraron {} solicitudes para el evaluador artístico '{}'", solicitudesDto.size(), evaluadorId);
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Solicitudes obtenidas exitosamente",
                solicitudesDto
            ));
        } catch (Exception e) {
            log.error("Error al obtener solicitudes para evaluador artístico: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener solicitudes: " + e.getMessage(), null));
        }
    }
    
    /**
     * Obtener solicitudes para evaluador económico
     */
    @GetMapping("/evaluador-economico/{evaluadorId}")
    public ResponseEntity<ApiResponse<List<SolicitudEvaluacionDto>>> getSolicitudesForEvaluadorEconomico(@PathVariable String evaluadorId) {
        try {
            log.info("Obteniendo solicitudes para evaluador económico: {}", evaluadorId);
            List<SolicitudEvaluacion> solicitudes = solicitudRepository.findForEvaluadorEconomico(evaluadorId);
            List<SolicitudEvaluacionDto> solicitudesDto = solicitudMapper.toDtoList(solicitudes);
            
            log.info("Se encontraron {} solicitudes para el evaluador económico '{}'", solicitudesDto.size(), evaluadorId);
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Solicitudes obtenidas exitosamente",
                solicitudesDto
            ));
        } catch (Exception e) {
            log.error("Error al obtener solicitudes para evaluador económico: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener solicitudes: " + e.getMessage(), null));
        }
    }
    
    /**
     * Buscar solicitudes por texto
     */
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<SolicitudEvaluacionDto>>> searchSolicitudes(@RequestParam String query) {
        try {
            log.info("Buscando solicitudes con query: {}", query);
            List<SolicitudEvaluacion> solicitudes = solicitudRepository.findByTextoWithDetails(query);
            List<SolicitudEvaluacionDto> solicitudesDto = solicitudMapper.toDtoList(solicitudes);
            
            log.info("Se encontraron {} solicitudes con la búsqueda '{}'", solicitudesDto.size(), query);
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Búsqueda completada exitosamente",
                solicitudesDto
            ));
        } catch (Exception e) {
            log.error("Error en la búsqueda de solicitudes: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error en la búsqueda: " + e.getMessage(), null));
        }
    }
    
    /**
     * Obtener solicitudes con filtros combinados
     */
    @GetMapping("/filtros")
    public ResponseEntity<ApiResponse<List<SolicitudEvaluacionDto>>> getSolicitudesWithFilters(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String texto) {
        try {
            log.info("Obteniendo solicitudes con filtros - Estado: {}, Texto: {}", estado, texto);
            List<SolicitudEvaluacion> solicitudes = solicitudRepository.findWithFilters(estado, texto);
            List<SolicitudEvaluacionDto> solicitudesDto = solicitudMapper.toDtoList(solicitudes);
            
            log.info("Se encontraron {} solicitudes con los filtros aplicados", solicitudesDto.size());
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Solicitudes filtradas obtenidas exitosamente",
                solicitudesDto
            ));
        } catch (Exception e) {
            log.error("Error al obtener solicitudes con filtros: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al aplicar filtros: " + e.getMessage(), null));
        }
    }
    
    /**
     * Endpoint específico para el frontend móvil reactivo
     * Determina automáticamente las solicitudes según el rol del usuario
     */
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<ApiResponse<List<SolicitudEvaluacionDto>>> getSolicitudesByUser(
            @PathVariable String userId,
            @RequestParam String tipoUsuario) {
        try {
            log.info("Obteniendo solicitudes para usuario: {} con rol: {}", userId, tipoUsuario);
            
            List<SolicitudEvaluacion> solicitudes;
            
            switch (tipoUsuario.toUpperCase()) {
                case "ARTISTA":
                    // El artista ve solo sus propias solicitudes
                    solicitudes = solicitudRepository.findByArtistaIdWithDetails(userId);
                    break;
                    
                case "EVALUADOR_ARTISTICO":
                    // El evaluador artístico ve solicitudes asignadas para evaluación artística
                    solicitudes = solicitudRepository.findForEvaluadorArtistico(userId);
                    break;
                    
                case "EVALUADOR_ECONOMICO":
                    // El evaluador económico ve solicitudes asignadas para evaluación económica
                    solicitudes = solicitudRepository.findForEvaluadorEconomico(userId);
                    break;
                    
                case "ANFITRION":
                case "GERENTE":
                case "ADMIN":
                    // Anfitriones, gerentes y admins ven todas las solicitudes
                    solicitudes = solicitudRepository.findAllWithDetails();
                    break;
                    
                default:
                    log.warn("Tipo de usuario no reconocido: {}", tipoUsuario);
                    return ResponseEntity.badRequest()
                        .body(new ApiResponse<>(false, "Tipo de usuario no válido", null));
            }
            
            List<SolicitudEvaluacionDto> solicitudesDto = solicitudMapper.toDtoList(solicitudes);
            
            log.info("Se encontraron {} solicitudes para usuario {} ({})", 
                    solicitudesDto.size(), userId, tipoUsuario);
            
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Solicitudes obtenidas exitosamente",
                solicitudesDto
            ));
            
        } catch (Exception e) {
            log.error("Error al obtener solicitudes por usuario: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener solicitudes: " + e.getMessage(), null));
        }
    }

    /**
     * Crear nueva solicitud con asignación automática usando SOLO el mapper
     */
    @PostMapping
    public ResponseEntity<ApiResponse<SolicitudEvaluacionDto>> createSolicitud(@RequestBody SolicitudEvaluacionDto solicitudDto) {
        try {
            System.out.println("🚀 Creando nueva solicitud CON MAPPER (sin service)");
            
            // ✨ El mapper automáticamente asigna evaluadores usando @AfterMapping
            SolicitudEvaluacion solicitud = solicitudMapper.toEntity(solicitudDto);
            
            // Guardar solicitud (ya viene con evaluadores asignados por el mapper)
            SolicitudEvaluacion solicitudGuardada = solicitudRepository.save(solicitud);
            
            // Convertir a DTO y devolver
            SolicitudEvaluacionDto responseDto = solicitudMapper.toDto(solicitudGuardada);
            
            System.out.println("🎉 Solicitud creada exitosamente con ID: " + solicitudGuardada.getIdSolicitud());
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Solicitud creada y evaluadores asignados automáticamente por el mapper",
                responseDto
            ));
            
        } catch (Exception e) {
            System.err.println("❌ Error al crear solicitud: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al crear solicitud: " + e.getMessage(), null));
        }
    }

    /**
     * Verificar disponibilidad de un evaluador usando el mapper
     */
    @GetMapping("/evaluador/{evaluadorId}/disponibilidad")
    public ResponseEntity<ApiResponse<Boolean>> checkEvaluadorDisponibilidad(@PathVariable String evaluadorId) {
        try {
            System.out.println("🔍 Verificando disponibilidad del evaluador: " + evaluadorId);
            
            boolean disponible = solicitudMapper.evaluadorPuedeRecibirSolicitudes(evaluadorId);
            int solicitudesPendientes = solicitudRepository.countSolicitudesPendientesByEvaluador(evaluadorId);
            
            String mensaje = disponible 
                ? String.format("Evaluador disponible (%d/5 solicitudes)", solicitudesPendientes)
                : String.format("Evaluador no disponible (%d/5 solicitudes)", solicitudesPendientes);
            
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                mensaje,
                disponible
            ));
            
        } catch (Exception e) {
            System.err.println("❌ Error al verificar disponibilidad del evaluador: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al verificar disponibilidad: " + e.getMessage(), null));
        }
    }

    /**
     * Endpoint de redistribución (funcionalidad pendiente sin service)
     */
    @PostMapping("/redistribuir")
    public ResponseEntity<ApiResponse<String>> redistribuirSolicitudes() {
        try {
            System.out.println("📋 Redistribución de solicitudes solicitada");
            
            // TODO: Implementar redistribución si es necesaria
            // Por ahora, el mapper maneja automáticamente la asignación en nuevas solicitudes
            
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "La asignación automática ya se maneja en el mapper. Redistribución manual no implementada.",
                "Proceso completado"
            ));
            
        } catch (Exception e) {
            System.err.println("❌ Error en la redistribución: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error en la redistribución: " + e.getMessage(), null));
        }
    }

    /**
     * Actualizar una solicitud
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SolicitudEvaluacionDto>> updateSolicitud(@PathVariable String id, @RequestBody SolicitudEvaluacionDto solicitudDto) {
        try {
            log.info("Actualizando solicitud con ID: {}", id);
            
            Optional<SolicitudEvaluacion> solicitudExistente = solicitudRepository.findById(id);
            if (!solicitudExistente.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Solicitud no encontrada", null));
            }
            
            SolicitudEvaluacion solicitud = solicitudMapper.toEntity(solicitudDto);
            solicitud.setIdSolicitud(id);
            solicitud.setFechaUltimaActualizacion(LocalDateTime.now());
            
            SolicitudEvaluacion solicitudActualizada = solicitudRepository.save(solicitud);
            SolicitudEvaluacionDto responseDto = solicitudMapper.toDto(solicitudActualizada);
            
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Solicitud actualizada exitosamente",
                responseDto
            ));
        } catch (Exception e) {
            log.error("Error al actualizar solicitud: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al actualizar solicitud: " + e.getMessage(), null));
        }
    }

    /**
     * Eliminar una solicitud
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSolicitud(@PathVariable String id) {
        try {
            log.info("Eliminando solicitud con ID: {}", id);
            
            if (!solicitudRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Solicitud no encontrada", null));
            }
            
            solicitudRepository.deleteById(id);
            
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Solicitud eliminada exitosamente",
                null
            ));
        } catch (Exception e) {
            log.error("Error al eliminar solicitud: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al eliminar solicitud: " + e.getMessage(), null));
        }
    }
}