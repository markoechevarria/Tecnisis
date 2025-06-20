package com.example.tecnisis.backend.repository;

import com.example.tecnisis.backend.entity.SolicitudEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitudEvaluacionRepository extends JpaRepository<SolicitudEvaluacion, String> {
    
    // Obtener todas las solicitudes con detalles
    @Query(value = """
        SELECT s.* FROM solicitudes_evaluacion s
        ORDER BY s.fecha_ultima_actualizacion DESC
        """, nativeQuery = true)
    List<SolicitudEvaluacion> findAllWithDetails();
    
    // Buscar por ID específico
    @Query(value = """
        SELECT * FROM solicitudes_evaluacion 
        WHERE id_solicitud = :id
        """, nativeQuery = true)
    Optional<SolicitudEvaluacion> findByIdWithDetails(@Param("id") String id);
    
    // Filtrar por estado
    @Query(value = """
        SELECT * FROM solicitudes_evaluacion 
        WHERE estado = :estado
        ORDER BY fecha_ultima_actualizacion DESC
        """, nativeQuery = true)
    List<SolicitudEvaluacion> findByEstadoWithDetails(@Param("estado") String estado);
    
    // Solicitudes por artista específico
    @Query(value = """
        SELECT s.* FROM solicitudes_evaluacion s
        WHERE s.id_artista = :artistaId
        ORDER BY s.fecha_ultima_actualizacion DESC
        """, nativeQuery = true)
    List<SolicitudEvaluacion> findByArtistaIdWithDetails(@Param("artistaId") String artistaId);
    
    // Solicitudes disponibles para evaluador artístico
    @Query(value = """
        SELECT s.* FROM solicitudes_evaluacion s 
        WHERE s.estado = 'PENDIENTE_ARTISTICA'
        OR s.id_evaluador_artistico = :evaluadorId
        ORDER BY s.fecha_ultima_actualizacion DESC
        """, nativeQuery = true)
    List<SolicitudEvaluacion> findForEvaluadorArtistico(@Param("evaluadorId") String evaluadorId);
    
    // Solicitudes disponibles para evaluador económico
    @Query(value = """
        SELECT s.* FROM solicitudes_evaluacion s 
        WHERE s.estado = 'APROBADA_ARTISTICA'
        OR s.id_evaluador_economico = :evaluadorId
        ORDER BY s.fecha_ultima_actualizacion DESC
        """, nativeQuery = true)
    List<SolicitudEvaluacion> findForEvaluadorEconomico(@Param("evaluadorId") String evaluadorId);
    
    // Búsqueda por texto en artista, obra o técnica
    @Query(value = """
        SELECT DISTINCT s.* FROM solicitudes_evaluacion s
        JOIN obras_arte o ON s.id_obra = o.id_obra
        JOIN artistas a ON o.id_artista = a.id_artista
        JOIN usuarios u ON a.id_usuario = u.id_usuario
        LEFT JOIN tecnicas_artisticas t ON o.id_tecnica = t.id_tecnica
        WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(o.titulo) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
        ORDER BY s.fecha_ultima_actualizacion DESC
        """, nativeQuery = true)
    List<SolicitudEvaluacion> findByTextoWithDetails(@Param("texto") String texto);
    
    // Consulta con filtros combinados
    @Query(value = """
        SELECT DISTINCT s.* FROM solicitudes_evaluacion s
        JOIN obras_arte o ON s.id_obra = o.id_obra
        JOIN artistas a ON o.id_artista = a.id_artista
        JOIN usuarios u ON a.id_usuario = u.id_usuario
        LEFT JOIN tecnicas_artisticas t ON o.id_tecnica = t.id_tecnica
        WHERE (:estado IS NULL OR s.estado = :estado)
          AND (:texto IS NULL OR 
               LOWER(u.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR
               LOWER(o.titulo) LIKE LOWER(CONCAT('%', :texto, '%')) OR
               LOWER(t.nombre) LIKE LOWER(CONCAT('%', :texto, '%')))
        ORDER BY s.fecha_ultima_actualizacion DESC
        """, nativeQuery = true)
    List<SolicitudEvaluacion> findWithFilters(
        @Param("estado") String estado,
        @Param("texto") String texto
    );
    
    // Obtener solicitudes pendientes
    @Query(value = """
        SELECT * FROM solicitudes_evaluacion 
        WHERE estado = 'PENDIENTE' 
        ORDER BY fecha_solicitud ASC
        """, nativeQuery = true)
    List<SolicitudEvaluacion> findPendientesSolicitudes();
    
    // Obtener solicitudes por rango de fechas
    @Query(value = """
        SELECT * FROM solicitudes_evaluacion 
        WHERE fecha_solicitud BETWEEN CAST(:fechaInicio AS timestamp) AND CAST(:fechaFin AS timestamp)
        ORDER BY fecha_solicitud DESC
        """, nativeQuery = true)
    List<SolicitudEvaluacion> findByFechaSolicitudBetween(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
    
    // Obtener solicitudes aprobadas artísticamente pero pendientes de evaluación económica
    @Query(value = """
        SELECT s.* FROM solicitudes_evaluacion s 
        WHERE s.id_solicitud IN (
            SELECT ea.id_solicitud 
            FROM evaluaciones_artisticas ea 
            WHERE ea.estado = 1
        )
        AND s.id_solicitud NOT IN (
            SELECT ee.id_solicitud 
            FROM evaluaciones_economicas ee 
            WHERE ee.estado IS NOT NULL
        )
        ORDER BY s.fecha_solicitud ASC
        """, nativeQuery = true)
    List<SolicitudEvaluacion> findApprovedArtisticPendingEconomic();
    
    // === NUEVAS CONSULTAS PARA LÓGICA DE NEGOCIO ===
    
    /**
     * Cuenta las solicitudes pendientes asignadas a un evaluador
     * Incluye estados: PENDIENTE_ARTISTICA, EN_EVALUACION_ECONOMICA
     */
    @Query(value = """
        SELECT COUNT(*) FROM solicitudes_evaluacion 
        WHERE (id_evaluador_artistico = :evaluadorId OR id_evaluador_economico = :evaluadorId)
        AND estado IN ('PENDIENTE_ARTISTICA', 'EN_EVALUACION_ECONOMICA')
        """, nativeQuery = true)
    int countSolicitudesPendientesByEvaluador(@Param("evaluadorId") String evaluadorId);
    
    /**
     * Busca evaluadores artísticos disponibles (con menos de 5 solicitudes pendientes)
     * Ordenados aleatoriamente para distribución equitativa
     */
    @Query(value = """
        SELECT u.id_usuario FROM usuarios u 
        WHERE u.tipo_usuario = 'EVALUADOR_ARTISTICO' 
        AND u.estado = 'ACTIVO'
        AND (
            SELECT COUNT(*) FROM solicitudes_evaluacion se 
            WHERE se.id_evaluador_artistico = u.id_usuario 
            AND se.estado = 'PENDIENTE_ARTISTICA'
        ) < 5
        ORDER BY RANDOM() 
        LIMIT 1
        """, nativeQuery = true)
    Optional<String> findEvaluadorArtisticoDisponible();
    
    /**
     * Busca evaluadores económicos disponibles (con menos de 5 solicitudes pendientes)
     * Ordenados aleatoriamente para distribución equitativa
     */
    @Query(value = """
        SELECT u.id_usuario FROM usuarios u 
        WHERE u.tipo_usuario = 'EVALUADOR_ECONOMICO' 
        AND u.estado = 'ACTIVO'
        AND (
            SELECT COUNT(*) FROM solicitudes_evaluacion se 
            WHERE se.id_evaluador_economico = u.id_usuario 
            AND se.estado = 'EN_EVALUACION_ECONOMICA'
        ) < 5
        ORDER BY RANDOM() 
        LIMIT 1
        """, nativeQuery = true)
    Optional<String> findEvaluadorEconomicoDisponible();
    

} 