package com.example.tecnisis.backend.controller;

import com.example.tecnisis.backend.common.ApiResponse;
import com.example.tecnisis.backend.dto.EvaluacionArtisticaDto;
import com.example.tecnisis.backend.dto.EvaluacionEconomicaDto;
import com.example.tecnisis.backend.entity.EvaluacionArtistica;
import com.example.tecnisis.backend.entity.EvaluacionEconomica;
import com.example.tecnisis.backend.entity.SolicitudEvaluacion;
import com.example.tecnisis.backend.mapper.EvaluacionArtisticaMapper;
import com.example.tecnisis.backend.mapper.EvaluacionEconomicaMapper;
import com.example.tecnisis.backend.repository.EvaluacionArtisticaRepository;
import com.example.tecnisis.backend.repository.EvaluacionEconomicaRepository;
import com.example.tecnisis.backend.repository.SolicitudEvaluacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionArtisticaRepository evaluacionArtisticaRepository;
    private final EvaluacionEconomicaRepository evaluacionEconomicaRepository;
    private final SolicitudEvaluacionRepository solicitudRepository;
    private final EvaluacionArtisticaMapper evaluacionArtisticaMapper;
    private final EvaluacionEconomicaMapper evaluacionEconomicaMapper;

    // === EVALUACIONES ARTÍSTICAS ===

    /**
     * Realizar evaluación artística
     * Cambia automáticamente el estado de la solicitud según el resultado
     */
    @PostMapping("/artisticas")
    public ResponseEntity<ApiResponse<EvaluacionArtisticaDto>> realizarEvaluacionArtistica(
            @RequestBody EvaluacionArtisticaDto evaluacionDto) {
        try {
            System.out.println("Realizando evaluación artística para solicitud: " + evaluacionDto.getIdSolicitud());

            // Verificar que la solicitud existe y está en estado correcto
            Optional<SolicitudEvaluacion> solicitudOpt = solicitudRepository.findById(evaluacionDto.getIdSolicitud());
            if (!solicitudOpt.isPresent()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Solicitud no encontrada", null));
            }

            SolicitudEvaluacion solicitud = solicitudOpt.get();
            if (!"PENDIENTE_ARTISTICA".equals(solicitud.getEstado())) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "La solicitud no está en estado para evaluación artística", null));
            }

            // Crear y guardar evaluación artística
            EvaluacionArtistica evaluacion = evaluacionArtisticaMapper.toEntity(evaluacionDto);
            evaluacion.setFechaEvaluacion(LocalDateTime.now());
            
            EvaluacionArtistica evaluacionGuardada = evaluacionArtisticaRepository.save(evaluacion);

            // Actualizar estado de la solicitud según el resultado
            String nuevoEstado;
            if (evaluacion.getEstado() != null && evaluacion.getEstado() == 1) {
                nuevoEstado = "EN_EVALUACION_ECONOMICA";
                System.out.println("Evaluación artística APROBADA - Solicitud pasa a evaluación económica");
            } else {
                nuevoEstado = "RECHAZADA_ARTISTICA";
                System.out.println("Evaluación artística RECHAZADA - Solicitud finalizada");
            }

            // Actualizar solicitud
            solicitud.setEstado(nuevoEstado);
            solicitud.setFechaUltimaActualizacion(LocalDateTime.now());
            solicitudRepository.save(solicitud);

            EvaluacionArtisticaDto responseDto = evaluacionArtisticaMapper.toDto(evaluacionGuardada);

            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Evaluación artística registrada exitosamente",
                responseDto
            ));

        } catch (Exception e) {
            System.err.println("Error al realizar evaluación artística: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al realizar evaluación: " + e.getMessage(), null));
        }
    }

    /**
     * Obtener evaluaciones artísticas por evaluador
     */
    @GetMapping("/artisticas/evaluador/{evaluadorId}")
    public ResponseEntity<ApiResponse<List<EvaluacionArtisticaDto>>> getEvaluacionesArtisticasByEvaluador(
            @PathVariable String evaluadorId) {
        try {
            System.out.println("Obteniendo evaluaciones artísticas del evaluador: " + evaluadorId);

            List<EvaluacionArtistica> evaluaciones = evaluacionArtisticaRepository.findByEvaluadorId(evaluadorId);
            List<EvaluacionArtisticaDto> evaluacionesDto = evaluacionArtisticaMapper.toDtoList(evaluaciones);

            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Evaluaciones artísticas obtenidas exitosamente",
                evaluacionesDto
            ));

        } catch (Exception e) {
            System.err.println("Error al obtener evaluaciones artísticas: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener evaluaciones: " + e.getMessage(), null));
        }
    }

    // === EVALUACIONES ECONÓMICAS ===

    /**
     * Realizar evaluación económica
     * Cambia automáticamente el estado de la solicitud según el resultado
     */
    @PostMapping("/economicas")
    public ResponseEntity<ApiResponse<EvaluacionEconomicaDto>> realizarEvaluacionEconomica(
            @RequestBody EvaluacionEconomicaDto evaluacionDto) {
        try {
            System.out.println("Realizando evaluación económica para solicitud: " + evaluacionDto.getIdSolicitud());

            // Verificar que la solicitud existe y está en estado correcto
            Optional<SolicitudEvaluacion> solicitudOpt = solicitudRepository.findById(evaluacionDto.getIdSolicitud());
            if (!solicitudOpt.isPresent()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Solicitud no encontrada", null));
            }

            SolicitudEvaluacion solicitud = solicitudOpt.get();
            if (!"EN_EVALUACION_ECONOMICA".equals(solicitud.getEstado())) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "La solicitud no está en estado para evaluación económica", null));
            }

            // Crear y guardar evaluación económica
            EvaluacionEconomica evaluacion = evaluacionEconomicaMapper.toEntity(evaluacionDto);
            evaluacion.setFechaEvaluacion(LocalDateTime.now());
            
            EvaluacionEconomica evaluacionGuardada = evaluacionEconomicaRepository.save(evaluacion);

            // Actualizar estado de la solicitud según el resultado
            String nuevoEstado;
            if (evaluacion.getEstado() != null && evaluacion.getEstado() == 1) {
                nuevoEstado = "APROBADA_FINAL";
                System.out.println("Evaluación económica APROBADA - Solicitud APROBADA FINAL");
            } else {
                nuevoEstado = "RECHAZADA_ECONOMICA";
                System.out.println("Evaluación económica RECHAZADA - Solicitud finalizada");
            }

            // Actualizar solicitud con porcentaje de ganancia si fue aprobada
            solicitud.setEstado(nuevoEstado);
            // Aquí comentamos el porcentaje de ganancia por ahora hasta verificar el DTO
            // if (evaluacionDto.getPorcentajeGanancia() != null) {
            //     solicitud.setPorcentajeGanancia(evaluacionDto.getPorcentajeGanancia());
            // }
            solicitud.setFechaUltimaActualizacion(LocalDateTime.now());
            solicitudRepository.save(solicitud);

            EvaluacionEconomicaDto responseDto = evaluacionEconomicaMapper.toDto(evaluacionGuardada);

            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Evaluación económica registrada exitosamente",
                responseDto
            ));

        } catch (Exception e) {
            System.err.println("Error al realizar evaluación económica: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al realizar evaluación: " + e.getMessage(), null));
        }
    }

    /**
     * Obtener evaluaciones económicas por evaluador
     */
    @GetMapping("/economicas/evaluador/{evaluadorId}")
    public ResponseEntity<ApiResponse<List<EvaluacionEconomicaDto>>> getEvaluacionesEconomicasByEvaluador(
            @PathVariable String evaluadorId) {
        try {
            System.out.println("Obteniendo evaluaciones económicas del evaluador: " + evaluadorId);

            List<EvaluacionEconomica> evaluaciones = evaluacionEconomicaRepository.findByEvaluadorId(evaluadorId);
            List<EvaluacionEconomicaDto> evaluacionesDto = evaluacionEconomicaMapper.toDtoList(evaluaciones);

            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Evaluaciones económicas obtenidas exitosamente",
                evaluacionesDto
            ));

        } catch (Exception e) {
            System.err.println("Error al obtener evaluaciones económicas: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener evaluaciones: " + e.getMessage(), null));
        }
    }

    // === ENDPOINTS GENERALES ===

    /**
     * Obtener evaluación artística por ID de solicitud
     */
    @GetMapping("/artisticas/solicitud/{solicitudId}")
    public ResponseEntity<ApiResponse<EvaluacionArtisticaDto>> getEvaluacionArtisticaBySolicitud(
            @PathVariable String solicitudId) {
        try {
            System.out.println("Obteniendo evaluación artística para solicitud: " + solicitudId);

            List<EvaluacionArtistica> evaluaciones = evaluacionArtisticaRepository.findBySolicitudId(solicitudId);
            
            if (!evaluaciones.isEmpty()) {
                EvaluacionArtisticaDto evaluacionDto = evaluacionArtisticaMapper.toDto(evaluaciones.get(0));
                return ResponseEntity.ok(new ApiResponse<>(
                    true,
                    "Evaluación artística encontrada",
                    evaluacionDto
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Evaluación artística no encontrada", null));
            }

        } catch (Exception e) {
            System.err.println("Error al obtener evaluación artística por solicitud: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener evaluación: " + e.getMessage(), null));
        }
    }

    /**
     * Obtener evaluación económica por ID de solicitud
     */
    @GetMapping("/economicas/solicitud/{solicitudId}")
    public ResponseEntity<ApiResponse<EvaluacionEconomicaDto>> getEvaluacionEconomicaBySolicitud(
            @PathVariable String solicitudId) {
        try {
            System.out.println("Obteniendo evaluación económica para solicitud: " + solicitudId);

            List<EvaluacionEconomica> evaluaciones = evaluacionEconomicaRepository.findBySolicitudId(solicitudId);
            
            if (!evaluaciones.isEmpty()) {
                EvaluacionEconomicaDto evaluacionDto = evaluacionEconomicaMapper.toDto(evaluaciones.get(0));
                return ResponseEntity.ok(new ApiResponse<>(
                    true,
                    "Evaluación económica encontrada",
                    evaluacionDto
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Evaluación económica no encontrada", null));
            }

        } catch (Exception e) {
            System.err.println("Error al obtener evaluación económica por solicitud: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener evaluación: " + e.getMessage(), null));
        }
    }

    /**
     * Obtener todas las evaluaciones artísticas
     */
    @GetMapping("/artisticas")
    public ResponseEntity<ApiResponse<List<EvaluacionArtisticaDto>>> getAllEvaluacionesArtisticas() {
        try {
            System.out.println("Obteniendo todas las evaluaciones artísticas");

            List<EvaluacionArtistica> evaluaciones = evaluacionArtisticaRepository.findAllByOrderByFechaEvaluacionDesc();
            List<EvaluacionArtisticaDto> evaluacionesDto = evaluacionArtisticaMapper.toDtoList(evaluaciones);

            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Evaluaciones artísticas obtenidas exitosamente",
                evaluacionesDto
            ));

        } catch (Exception e) {
            System.err.println("Error al obtener todas las evaluaciones artísticas: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener evaluaciones: " + e.getMessage(), null));
        }
    }

    /**
     * Obtener todas las evaluaciones económicas
     */
    @GetMapping("/economicas")
    public ResponseEntity<ApiResponse<List<EvaluacionEconomicaDto>>> getAllEvaluacionesEconomicas() {
        try {
            System.out.println("Obteniendo todas las evaluaciones económicas");

            List<EvaluacionEconomica> evaluaciones = evaluacionEconomicaRepository.findAllByOrderByFechaEvaluacionDesc();
            List<EvaluacionEconomicaDto> evaluacionesDto = evaluacionEconomicaMapper.toDtoList(evaluaciones);

            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Evaluaciones económicas obtenidas exitosamente",
                evaluacionesDto
            ));

        } catch (Exception e) {
            System.err.println("Error al obtener todas las evaluaciones económicas: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener evaluaciones: " + e.getMessage(), null));
        }
    }
}