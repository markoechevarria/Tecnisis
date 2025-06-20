package com.example.tecnisis.backend.mapper;

import com.example.tecnisis.backend.dto.EvaluacionEconomicaDto;
import com.example.tecnisis.backend.entity.EvaluacionEconomica;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EvaluacionEconomicaMapper {
    
    @Mapping(source = "id", target = "idEvaluacionEconomica")
    @Mapping(source = "solicitud.idSolicitud", target = "idSolicitud")
    @Mapping(source = "evaluador.idUsuario", target = "idEvaluadorEconomico")
    @Mapping(source = "evaluador.nombre", target = "nombreEvaluador")
    @Mapping(target = "estadoTexto", ignore = true)
    @Mapping(target = "rentabilidadTexto", ignore = true)
    @Mapping(target = "tituloObra", ignore = true)
    EvaluacionEconomicaDto toDto(EvaluacionEconomica evaluacion);
    
    @Mapping(source = "idEvaluacionEconomica", target = "id")
    @Mapping(target = "solicitud", ignore = true)
    @Mapping(target = "evaluador", ignore = true)
    EvaluacionEconomica toEntity(EvaluacionEconomicaDto dto);
    
    List<EvaluacionEconomicaDto> toDtoList(List<EvaluacionEconomica> evaluaciones);
    List<EvaluacionEconomica> toEntityList(List<EvaluacionEconomicaDto> dtos);
} 