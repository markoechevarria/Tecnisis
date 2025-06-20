package com.example.tecnisis.backend.mapper;

import com.example.tecnisis.backend.dto.ArtistaDto;
import com.example.tecnisis.backend.entity.Artista;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistaMapper {
    
    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    @Mapping(source = "usuario.nombre", target = "nombre")
    @Mapping(source = "usuario.dni", target = "dni")
    @Mapping(source = "usuario.telefono", target = "telefono")
    @Mapping(source = "usuario.correo", target = "correo")
    @Mapping(source = "usuario.tipoUsuario", target = "tipoUsuario")
    @Mapping(source = "usuario.estado", target = "estado")
    @Mapping(source = "usuario.sexo", target = "sexo")
    @Mapping(source = "usuario.direccion", target = "direccion")
    ArtistaDto toDto(Artista artista);
    
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "obras", ignore = true)
    Artista toEntity(ArtistaDto dto);
    
    List<ArtistaDto> toDtoList(List<Artista> artistas);
    List<Artista> toEntityList(List<ArtistaDto> dtos);
} 