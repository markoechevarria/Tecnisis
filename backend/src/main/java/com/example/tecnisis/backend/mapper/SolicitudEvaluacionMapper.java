package com.example.tecnisis.backend.mapper;

import com.example.tecnisis.backend.dto.SolicitudEvaluacionDto;
import com.example.tecnisis.backend.entity.SolicitudEvaluacion;
import com.example.tecnisis.backend.repository.SolicitudEvaluacionRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class SolicitudEvaluacionMapper {

    @Autowired
    private SolicitudEvaluacionRepository solicitudRepository;

    // === MAPEOS BÁSICOS ===
    
    @Mapping(source = "id", target = "idSolicitud")
    @Mapping(source = "obra.idObra", target = "idObra")
    @Mapping(source = "solicitante.idUsuario", target = "idSolicitante")
    @Mapping(source = "evaluadorAsignado.idUsuario", target = "idEvaluadorAsignado")
    @Mapping(source = "evaluadorArtistico.idUsuario", target = "idEvaluadorArtistico")
    @Mapping(source = "evaluadorEconomico.idUsuario", target = "idEvaluadorEconomico")
    @Mapping(source = "fechaCreacion", target = "fechaSolicitud")
    @Mapping(source = "fechaActualizacion", target = "fechaUltimaActualizacion")
    public abstract SolicitudEvaluacionDto toDto(SolicitudEvaluacion entity);

    @Mapping(source = "idSolicitud", target = "id")
    @Mapping(target = "obra", ignore = true)
    @Mapping(target = "solicitante", ignore = true)
    @Mapping(target = "evaluadorAsignado", ignore = true)
    @Mapping(target = "evaluadorArtistico", ignore = true)
    @Mapping(target = "evaluadorEconomico", ignore = true)
    @Mapping(target = "evaluacionesArtisticas", ignore = true)
    @Mapping(target = "evaluacionesEconomicas", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    public abstract SolicitudEvaluacion toEntity(SolicitudEvaluacionDto dto);

    public abstract List<SolicitudEvaluacionDto> toDtoList(List<SolicitudEvaluacion> entities);

    // === LÓGICA DE ASIGNACIÓN DE EVALUADORES ===

    /**
     * Aplica la lógica de asignación automática de evaluadores después del mapeo
     */
    @AfterMapping
    protected void asignarEvaluadoresAutomaticamente(@MappingTarget SolicitudEvaluacion solicitud) {
        System.out.println("🎯 Iniciando asignación automática de evaluadores para solicitud");

        try {
            // 1. Asignar evaluador artístico disponible
            String evaluadorArtisticoId = buscarEvaluadorArtisticoDisponible();
            if (evaluadorArtisticoId != null) {
                solicitud.setIdEvaluadorArtistico(evaluadorArtisticoId);
                solicitud.setEstado("PENDIENTE_ARTISTICA");
                System.out.println("✅ Evaluador artístico asignado: " + evaluadorArtisticoId);
            } else {
                solicitud.setEstado("EN_ESPERA_EVALUADOR_ARTISTICO");
                System.out.println("⚠️ No hay evaluadores artísticos disponibles");
            }

            // 2. Pre-asignar evaluador económico disponible
            String evaluadorEconomicoId = buscarEvaluadorEconomicoDisponible();
            if (evaluadorEconomicoId != null) {
                solicitud.setIdEvaluadorEconomico(evaluadorEconomicoId);
                System.out.println("✅ Evaluador económico pre-asignado: " + evaluadorEconomicoId);
            } else {
                System.out.println("⚠️ No hay evaluadores económicos disponibles");
            }

            // 3. Establecer fechas
            LocalDateTime ahora = LocalDateTime.now();
            solicitud.setFechaSolicitud(ahora);
            solicitud.setFechaUltimaActualizacion(ahora);

            System.out.println("🎉 Asignación automática completada");

        } catch (Exception e) {
            System.err.println("❌ Error en asignación automática: " + e.getMessage());
            e.printStackTrace();
            // En caso de error, al menos establecer estado básico
            solicitud.setEstado("PENDIENTE_ASIGNACION");
            solicitud.setFechaUltimaActualizacion(LocalDateTime.now());
        }
    }

    /**
     * Busca un evaluador artístico disponible (con menos de 5 solicitudes)
     */
    private String buscarEvaluadorArtisticoDisponible() {
        try {
            Optional<String> evaluadorId = solicitudRepository.findEvaluadorArtisticoDisponible();
            
            if (evaluadorId.isPresent()) {
                String idEvaluador = evaluadorId.get();
                int solicitudesPendientes = solicitudRepository.countSolicitudesPendientesByEvaluador(idEvaluador);
                
                System.out.println("🔍 Evaluador artístico " + idEvaluador + " tiene " + solicitudesPendientes + " solicitudes pendientes");
                
                if (solicitudesPendientes < 5) {
                    return idEvaluador;
                } else {
                    System.out.println("❌ Evaluador artístico " + idEvaluador + " ya tiene 5 solicitudes");
                    return null;
                }
            } else {
                System.out.println("❌ No se encontraron evaluadores artísticos disponibles");
                return null;
            }
        } catch (Exception e) {
            System.err.println("❌ Error al buscar evaluador artístico: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca un evaluador económico disponible (con menos de 5 solicitudes)
     */
    private String buscarEvaluadorEconomicoDisponible() {
        try {
            Optional<String> evaluadorId = solicitudRepository.findEvaluadorEconomicoDisponible();
            
            if (evaluadorId.isPresent()) {
                String idEvaluador = evaluadorId.get();
                int solicitudesPendientes = solicitudRepository.countSolicitudesPendientesByEvaluador(idEvaluador);
                
                System.out.println("🔍 Evaluador económico " + idEvaluador + " tiene " + solicitudesPendientes + " solicitudes pendientes");
                
                if (solicitudesPendientes < 5) {
                    return idEvaluador;
                } else {
                    System.out.println("❌ Evaluador económico " + idEvaluador + " ya tiene 5 solicitudes");
                    return null;
                }
            } else {
                System.out.println("❌ No se encontraron evaluadores económicos disponibles");
                return null;
            }
        } catch (Exception e) {
            System.err.println("❌ Error al buscar evaluador económico: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método público para verificar disponibilidad de evaluador
     */
    public boolean evaluadorPuedeRecibirSolicitudes(String evaluadorId) {
        try {
            int solicitudesPendientes = solicitudRepository.countSolicitudesPendientesByEvaluador(evaluadorId);
            return solicitudesPendientes < 5;
        } catch (Exception e) {
            System.err.println("❌ Error al verificar disponibilidad: " + e.getMessage());
            return false;
        }
    }
} 