package com.example.tecnisis.backend.repository;

import com.example.tecnisis.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//Esto de aqui es para que compruebe que el email y el dni son unicos y no se pueden repetir en otro usuario
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByDni(String dni);
}