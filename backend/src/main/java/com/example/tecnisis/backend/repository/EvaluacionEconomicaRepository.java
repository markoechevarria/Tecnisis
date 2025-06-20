package com.example.tecnisis.backend.repository;

import com.example.tecnisis.backend.entity.EvaluacionEconomica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluacionEconomicaRepository extends JpaRepository<EvaluacionEconomica, String> {
    
    // Buscar evaluaciones por solicitud de evaluación
    @Query(value = "SELECT * FROM evaluaciones_economicas WHERE id_solicitud = :solicitudId", nativeQuery = true)
    List<EvaluacionEconomica> findBySolicitudId(@Param("solicitudId") String solicitudId);
    
    // Buscar evaluaciones por evaluador económico
    @Query(value = "SELECT * FROM evaluaciones_economicas WHERE id_evaluador_economico = :evaluadorId ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionEconomica> findByEvaluadorId(@Param("evaluadorId") String evaluadorId);
    
    // Buscar evaluaciones por estado
    @Query(value = "SELECT * FROM evaluaciones_economicas WHERE estado = :estado ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionEconomica> findByEstado(@Param("estado") Integer estado);
    
    // Verificar si existe evaluación para solicitud y evaluador específicos
    @Query(value = "SELECT COUNT(*) > 0 FROM evaluaciones_economicas WHERE id_solicitud = :solicitudId AND id_evaluador_economico = :evaluadorId", nativeQuery = true)
    boolean existsBySolicitudAndEvaluador(@Param("solicitudId") String solicitudId, @Param("evaluadorId") String evaluadorId);
    
    // Buscar evaluación específica por solicitud y evaluador
    @Query(value = "SELECT * FROM evaluaciones_economicas WHERE id_solicitud = :solicitudId AND id_evaluador_economico = :evaluadorId", nativeQuery = true)
    Optional<EvaluacionEconomica> findBySolicitudAndEvaluador(@Param("solicitudId") String solicitudId, @Param("evaluadorId") String evaluadorId);
    
    // Obtener evaluaciones con valoración económica mínima
    @Query(value = "SELECT * FROM evaluaciones_economicas WHERE valoracion_economica >= :valoracionMinima ORDER BY valoracion_economica DESC", nativeQuery = true)
    List<EvaluacionEconomica> findByValoracionMinima(@Param("valoracionMinima") Double valoracionMinima);
    
    // Obtener evaluaciones aprobadas (estado = 1)
    @Query(value = "SELECT * FROM evaluaciones_economicas WHERE estado = 1 ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionEconomica> findAprobadas();
    
    // Obtener evaluaciones rechazadas (estado = 0)
    @Query(value = "SELECT * FROM evaluaciones_economicas WHERE estado = 0 ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionEconomica> findRechazadas();
    
    // Obtener evaluaciones pendientes (estado es null)
    @Query(value = "SELECT * FROM evaluaciones_economicas WHERE estado IS NULL ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionEconomica> findPendientes();
    
    // Obtener evaluaciones por rentabilidad
    @Query(value = "SELECT * FROM evaluaciones_economicas WHERE rentabilidad = :rentabilidad ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionEconomica> findByRentabilidad(@Param("rentabilidad") Boolean rentabilidad);
    
    // Obtener estadísticas por evaluador
    @Query(value = """
        SELECT 
            id_evaluador_economico,
            COUNT(*) as total_evaluaciones,
            AVG(valoracion_economica) as promedio_valoracion,
            COUNT(CASE WHEN estado = 1 THEN 1 END) as aprobadas,
            COUNT(CASE WHEN estado = 0 THEN 1 END) as rechazadas,
            COUNT(CASE WHEN rentabilidad = true THEN 1 END) as rentables
        FROM evaluaciones_economicas 
        WHERE id_evaluador_economico = :evaluadorId
        GROUP BY id_evaluador_economico
        """, nativeQuery = true)
    Object[] getEstadisticasByEvaluador(@Param("evaluadorId") String evaluadorId);
    
    // Buscar evaluaciones por rango de fechas
    @Query(value = """
        SELECT * FROM evaluaciones_economicas 
        WHERE fecha_evaluacion BETWEEN CAST(:fechaInicio AS timestamp) AND CAST(:fechaFin AS timestamp) 
        ORDER BY fecha_evaluacion DESC
        """, nativeQuery = true)
    List<EvaluacionEconomica> findByFechaEvaluacionBetween(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
    
    // Obtener evaluaciones con comentarios
    @Query(value = "SELECT * FROM evaluaciones_economicas WHERE comentarios IS NOT NULL AND comentarios != '' ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionEconomica> findWithComentarios();
    
    // Obtener promedio de valoraciones por evaluador
    @Query(value = """
        SELECT id_evaluador_economico, AVG(valoracion_economica) as promedio 
        FROM evaluaciones_economicas 
        WHERE valoracion_economica IS NOT NULL 
        GROUP BY id_evaluador_economico
        """, nativeQuery = true)
    List<Object[]> getPromedioValoracionesByEvaluador();
    
    // Verificar si existe evaluación para una solicitud
    @Query(value = "SELECT COUNT(*) > 0 FROM evaluaciones_economicas WHERE id_solicitud = :solicitudId", nativeQuery = true)
    boolean existsBySolicitudId(@Param("solicitudId") String solicitudId);
    
    // Obtener todas las evaluaciones ordenadas por fecha
    @Query(value = "SELECT * FROM evaluaciones_economicas ORDER BY fecha_evaluacion DESC", nativeQuery = true)
    List<EvaluacionEconomica> findAllByOrderByFechaEvaluacionDesc();
    
    // Buscar evaluaciones en rango de valoración económica
    @Query(value = """
        SELECT * FROM evaluaciones_economicas 
        WHERE valoracion_economica BETWEEN :valoracionMin AND :valoracionMax 
        ORDER BY valoracion_economica DESC
        """, nativeQuery = true)
    List<EvaluacionEconomica> findByValoracionBetween(@Param("valoracionMin") Double valoracionMin, @Param("valoracionMax") Double valoracionMax);
} 