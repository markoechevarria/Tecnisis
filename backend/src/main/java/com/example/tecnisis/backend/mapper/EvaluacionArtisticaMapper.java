package com.example.tecnisis.backend.mapper;

import com.example.tecnisis.backend.dto.EvaluacionArtisticaDto;
import com.example.tecnisis.backend.entity.EvaluacionArtistica;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EvaluacionArtisticaMapper {
    
    @Mapping(source = "id", target = "idEvaluacionArtistica")
    @Mapping(source = "solicitud.idSolicitud", target = "idSolicitud")
    @Mapping(source = "evaluador.idUsuario", target = "idEvaluadorArtistico")
    @Mapping(source = "evaluador.nombre", target = "nombreEvaluador")
    @Mapping(target = "estadoTexto", ignore = true)
    @Mapping(target = "tituloObra", ignore = true)
    EvaluacionArtisticaDto toDto(EvaluacionArtistica evaluacion);
    
    @Mapping(source = "idEvaluacionArtistica", target = "id")
    @Mapping(target = "solicitud", ignore = true)
    @Mapping(target = "evaluador", ignore = true)
    EvaluacionArtistica toEntity(EvaluacionArtisticaDto dto);
    
    List<EvaluacionArtisticaDto> toDtoList(List<EvaluacionArtistica> evaluaciones);
    List<EvaluacionArtistica> toEntityList(List<EvaluacionArtisticaDto> dtos);
} 