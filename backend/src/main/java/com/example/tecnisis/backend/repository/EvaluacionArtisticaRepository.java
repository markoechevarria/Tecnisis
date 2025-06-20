package com.example.tecnisis.backend.repository;

import com.example.tecnisis.backend.entity.EvaluacionArtistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluacionArtisticaRepository extends JpaRepository<EvaluacionArtistica, String> {
    
    // Buscar evaluaciones por solicitud de evaluación
    @Query(value = "SELECT * FROM evaluaciones_artisticas WHERE id_solicitud = :solicitudId", nativeQuery = true)
    List<EvaluacionArtistica> findBySolicitudId(@Param("solicitudId") String solicitudId);
    
    // Buscar evaluaciones por evaluador artístico
    @Query(value = "SELECT * FROM evaluaciones_artisticas WHERE id_evaluador_artistico = :evaluadorId ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionArtistica> findByEvaluadorId(@Param("evaluadorId") String evaluadorId);

    // Buscar evaluaciones por estado
    @Query(value = "SELECT * FROM evaluaciones_artisticas WHERE estado = :estado ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionArtistica> findByEstado(@Param("estado") Integer estado);
    
    // Verificar si existe evaluación para solicitud y evaluador específicos
    @Query(value = "SELECT COUNT(*) > 0 FROM evaluaciones_artisticas WHERE id_solicitud = :solicitudId AND id_evaluador_artistico = :evaluadorId", nativeQuery = true)
    boolean existsBySolicitudAndEvaluador(@Param("solicitudId") String solicitudId, @Param("evaluadorId") String evaluadorId);
    
    // Buscar evaluación específica por solicitud y evaluador
    @Query(value = "SELECT * FROM evaluaciones_artisticas WHERE id_solicitud = :solicitudId AND id_evaluador_artistico = :evaluadorId", nativeQuery = true)
    Optional<EvaluacionArtistica> findBySolicitudAndEvaluador(@Param("solicitudId") String solicitudId, @Param("evaluadorId") String evaluadorId);
    
    // Obtener evaluaciones con calificación mínima
    @Query(value = "SELECT * FROM evaluaciones_artisticas WHERE calificacion >= :calificacionMinima ORDER BY calificacion DESC", nativeQuery = true)
    List<EvaluacionArtistica> findByCalificacionMinima(@Param("calificacionMinima") Integer calificacionMinima);
    
    // Obtener evaluaciones aprobadas (estado = 1)
    @Query(value = "SELECT * FROM evaluaciones_artisticas WHERE estado = 1 ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionArtistica> findAprobadas();
    
    // Obtener evaluaciones rechazadas (estado = 0)
    @Query(value = "SELECT * FROM evaluaciones_artisticas WHERE estado = 0 ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionArtistica> findRechazadas();
    
    // Obtener evaluaciones pendientes (estado es null)
    @Query(value = "SELECT * FROM evaluaciones_artisticas WHERE estado IS NULL ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionArtistica> findPendientes();
    
    // Obtener estadísticas por evaluador
    @Query(value = """
        SELECT 
            id_evaluador_artistico,
            COUNT(*) as total_evaluaciones,
            AVG(calificacion) as promedio_calificacion,
            COUNT(CASE WHEN estado = 1 THEN 1 END) as aprobadas,
            COUNT(CASE WHEN estado = 0 THEN 1 END) as rechazadas
        FROM evaluaciones_artisticas 
        WHERE id_evaluador_artistico = :evaluadorId
        GROUP BY id_evaluador_artistico
        """, nativeQuery = true)
    Object[] getEstadisticasByEvaluador(@Param("evaluadorId") String evaluadorId);
    
    // Buscar evaluaciones por rango de fechas
    @Query(value = """
        SELECT * FROM evaluaciones_artisticas 
        WHERE fecha_evaluacion BETWEEN CAST(:fechaInicio AS timestamp) AND CAST(:fechaFin AS timestamp) 
        ORDER BY fecha_evaluacion DESC
        """, nativeQuery = true)
    List<EvaluacionArtistica> findByFechaEvaluacionBetween(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
    
    // Obtener evaluaciones con comentarios
    @Query(value = "SELECT * FROM evaluaciones_artisticas WHERE comentarios IS NOT NULL AND comentarios != '' ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionArtistica> findWithComentarios();
    
    // Obtener promedio de calificaciones por evaluador
    @Query(value = """
        SELECT id_evaluador_artistico, AVG(calificacion) as promedio 
        FROM evaluaciones_artisticas 
        WHERE calificacion IS NOT NULL 
        GROUP BY id_evaluador_artistico
        """, nativeQuery = true)
    List<Object[]> getPromedioCalificacionesByEvaluador();
    
    // Verificar si existe evaluación para una solicitud
    @Query(value = "SELECT COUNT(*) > 0 FROM evaluaciones_artisticas WHERE id_solicitud = :solicitudId", nativeQuery = true)
    boolean existsBySolicitudId(@Param("solicitudId") String solicitudId);
    
    // Obtener todas las evaluaciones ordenadas por fecha
    @Query(value = "SELECT * FROM evaluaciones_artisticas ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionArtistica> findAllByOrderByFechaEvaluacionDesc();
} 