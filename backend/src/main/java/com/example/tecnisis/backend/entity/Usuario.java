package com.example.tecnisis.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDateTime;

/**
 * Entidad Usuario que mapea exactamente la tabla 'usuarios' de PostgreSQL
 * ID generado automáticamente con secuencia: 6 dígitos con padding de ceros
 */
@Entity
@Table(name = "usuarios", indexes = {
    @Index(name = "idx_usuarios_dni", columnList = "dni", unique = true),
    @Index(name = "idx_usuarios_correo", columnList = "correo", unique = true),
    @Index(name = "idx_usuarios_tipo", columnList = "tipo_usuario")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"passwordHash"})
public class Usuario {
    
    @Id
    @Column(name = "id_usuario", length = 255)
    @EqualsAndHashCode.Include
    private String idUsuario; // Generado automáticamente por la secuencia PostgreSQL
    
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    
    @Column(name = "dni", nullable = false, unique = true, length = 255)
    private String dni;
    
    @Column(name = "telefono", length = 255)
    private String telefono;
    
    @Column(name = "correo", nullable = false, unique = true, length = 255)
    private String correo;
    
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;
    
    @Column(name = "tipo_usuario", length = 255)
    private String tipoUsuario;
    
    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro; // Generado automáticamente por PostgreSQL
    
    @Column(name = "estado")
    private Boolean estado = true; // Activo por defecto
    
    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso;
    
    @Column(name = "sexo", length = 1)
    private String sexo;
    
    @Column(name = "direccion", length = 255)
    private String direccion;
    
    // ===== MÉTODOS DE CONVENIENCIA =====
    
    /**
     * Método de compatibilidad para servicios existentes
     */
    public String getId() {
        return this.idUsuario;
    }
    
    /**
     * Método de compatibilidad para servicios existentes
     */
    public void setId(String id) {
        this.idUsuario = id;
    }
    
    /**
     * Verifica si el usuario está activo
     */
    public boolean isActivo() {
        return estado != null && estado;
    }
    
    /**
     * Verifica si es un anfitrión
     */
    public boolean isAnfitrion() {
        return "ANFITRION".equals(tipoUsuario);
    }
    
    /**
     * Verifica si es un artista
     */
    public boolean isArtista() {
        return "ARTISTA".equals(tipoUsuario);
    }
    
    /**
     * Verifica si es un evaluador artístico
     */
    public boolean isEvaluadorArtistico() {
        return "EVALUADOR_ARTISTICO".equals(tipoUsuario);
    }
    
    /**
     * Verifica si es un evaluador económico
     */
    public boolean isEvaluadorEconomico() {
        return "EVALUADOR_ECONOMICO".equals(tipoUsuario);
    }
    
    /**
     * Actualiza el último acceso al momento actual
     */
    public void actualizarUltimoAcceso() {
        this.ultimoAcceso = LocalDateTime.now();
    }
    
    // ===== HOOKS DE JPA =====
    
    @PrePersist
    protected void onCreate() {
        if (estado == null) {
            estado = true;
        }
    }
}