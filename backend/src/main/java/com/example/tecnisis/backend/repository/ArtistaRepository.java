package com.example.tecnisis.backend.repository;

import com.example.tecnisis.backend.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, String> {
    
    // Buscar por ID de usuario usando SQL nativo
    @Query(value = "SELECT * FROM artistas WHERE id_usuario = :idUsuario", nativeQuery = true)
    Optional<Artista> findByUsuarioId(@Param("idUsuario") String idUsuario);
    
    // Buscar artistas por nombre del usuario (case insensitive)
    @Query(value = """
        SELECT a.* FROM artistas a 
        JOIN usuarios u ON a.id_usuario = u.id_usuario 
        WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))
        ORDER BY a.fecha_registro DESC
        """, nativeQuery = true)
    List<Artista> findByUsuarioNombreContaining(@Param("nombre") String nombre);
    
    // Buscar artistas por biografía (case insensitive)
    @Query(value = """
        SELECT * FROM artistas 
        WHERE LOWER(biografia) LIKE LOWER(CONCAT('%', :biografia, '%'))
        ORDER BY fecha_registro DESC
        """, nativeQuery = true)
    List<Artista> findByBiografiaContaining(@Param("biografia") String biografia);
    
    // Buscar artista por email del usuario
    @Query(value = """
        SELECT a.* FROM artistas a 
        JOIN usuarios u ON a.id_usuario = u.id_usuario 
        WHERE LOWER(u.email) = LOWER(:email)
        """, nativeQuery = true)
    Optional<Artista> findByUsuarioEmail(@Param("email") String email);
    
    // Verificar si existe artista con ID de usuario
    @Query(value = "SELECT COUNT(*) > 0 FROM artistas WHERE id_usuario = :idUsuario", nativeQuery = true)
    boolean existsByUsuarioId(@Param("idUsuario") String idUsuario);
    
    // Verificar si existe artista con email del usuario
    @Query(value = """
        SELECT COUNT(*) > 0 FROM artistas a 
        JOIN usuarios u ON a.id_usuario = u.id_usuario 
        WHERE LOWER(u.email) = LOWER(:email)
        """, nativeQuery = true)
    boolean existsByUsuarioEmail(@Param("email") String email);
    
    // Obtener artistas ordenados por fecha de registro (más recientes primero)
    @Query(value = "SELECT * FROM artistas ORDER BY fecha_registro DESC", nativeQuery = true)
    List<Artista> findAllByOrderByFechaRegistroDesc();
    
    // Buscar artistas por usuario activo
    @Query(value = """
        SELECT a.* FROM artistas a 
        JOIN usuarios u ON a.id_usuario = u.id_usuario 
        WHERE u.estado = true 
        ORDER BY a.fecha_registro DESC
        """, nativeQuery = true)
    List<Artista> findByUsuarioActivo();
    
    // Búsqueda combinada por texto (nombre del usuario o biografía)
    @Query(value = """
        SELECT DISTINCT a.* FROM artistas a 
        JOIN usuarios u ON a.id_usuario = u.id_usuario 
        WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(a.biografia) LIKE LOWER(CONCAT('%', :texto, '%'))
        ORDER BY a.fecha_registro DESC
        """, nativeQuery = true)
    List<Artista> findByTexto(@Param("texto") String texto);
    
    // Obtener artistas registrados en un rango de fechas
    @Query(value = """
        SELECT * FROM artistas 
        WHERE fecha_registro BETWEEN CAST(:fechaInicio AS timestamp) AND CAST(:fechaFin AS timestamp)
        ORDER BY fecha_registro DESC
        """, nativeQuery = true)
    List<Artista> findByFechaRegistroBetween(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
} 