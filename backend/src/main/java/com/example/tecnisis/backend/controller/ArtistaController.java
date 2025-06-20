package com.example.tecnisis.backend.controller;

import com.example.tecnisis.backend.common.ApiResponse;
import com.example.tecnisis.backend.dto.ArtistaDto;
import com.example.tecnisis.backend.entity.Artista;
import com.example.tecnisis.backend.mapper.ArtistaMapper;
import com.example.tecnisis.backend.repository.ArtistaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/artistas")
@Slf4j
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;
    
    @Autowired
    private ArtistaMapper artistaMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ArtistaDto>>> getAllArtistas() {
        try {
            List<Artista> artistas = artistaRepository.findAllByOrderByFechaRegistroDesc();
            List<ArtistaDto> artistasDto = artistaMapper.toDtoList(artistas);
            
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Artistas obtenidos exitosamente",
                artistasDto
            ));
        } catch (Exception e) {
            log.error("Error al obtener artistas: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener artistas: " + e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ArtistaDto>> getArtistaById(@PathVariable String id) {
        try {
            Optional<Artista> artista = artistaRepository.findById(id);
            if (artista.isPresent()) {
                ArtistaDto artistaDto = artistaMapper.toDto(artista.get());
                return ResponseEntity.ok(new ApiResponse<>(
                    true,
                    "Artista encontrado exitosamente",
                    artistaDto
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Artista no encontrado", null));
            }
        } catch (Exception e) {
            log.error("Error al obtener artista: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener artista: " + e.getMessage(), null));
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ApiResponse<ArtistaDto>> getArtistaByUsuarioId(@PathVariable String idUsuario) {
        try {
            Optional<Artista> artista = artistaRepository.findByUsuarioId(idUsuario);
            if (artista.isPresent()) {
                ArtistaDto artistaDto = artistaMapper.toDto(artista.get());
                return ResponseEntity.ok(new ApiResponse<>(
                    true,
                    "Artista encontrado exitosamente",
                    artistaDto
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Artista no encontrado para el usuario", null));
            }
        } catch (Exception e) {
            log.error("Error al obtener artista por usuario: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener artista: " + e.getMessage(), null));
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<ArtistaDto>>> buscarArtistas(@RequestParam String texto) {
        try {
            List<Artista> artistas = artistaRepository.findByTexto(texto);
            List<ArtistaDto> artistasDto = artistaMapper.toDtoList(artistas);
            
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Búsqueda completada exitosamente",
                artistasDto
            ));
        } catch (Exception e) {
            log.error("Error al buscar artistas: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al buscar artistas: " + e.getMessage(), null));
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<ApiResponse<List<ArtistaDto>>> getArtistasActivos() {
        try {
            List<Artista> artistas = artistaRepository.findByUsuarioActivo();
            List<ArtistaDto> artistasDto = artistaMapper.toDtoList(artistas);
            
            return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Artistas activos obtenidos exitosamente",
                artistasDto
            ));
        } catch (Exception e) {
            log.error("Error al obtener artistas activos: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al obtener artistas activos: " + e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ArtistaDto>> createArtista(@RequestBody ArtistaDto artistaDto) {
        try {
            // Verificar si ya existe un artista con este usuario
            if (artistaRepository.existsByUsuarioId(artistaDto.getIdUsuario())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(false, "Ya existe un artista con este usuario", null));
            }
            
            Artista artista = artistaMapper.toEntity(artistaDto);
            Artista savedArtista = artistaRepository.save(artista);
            ArtistaDto savedDto = artistaMapper.toDto(savedArtista);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Artista creado exitosamente", savedDto));
        } catch (Exception e) {
            log.error("Error al crear artista: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al crear artista: " + e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ArtistaDto>> updateArtista(@PathVariable String id, @RequestBody ArtistaDto artistaDto) {
        try {
            Optional<Artista> existingArtista = artistaRepository.findById(id);
            if (!existingArtista.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Artista no encontrado", null));
            }
            
            Artista artista = artistaMapper.toEntity(artistaDto);
            artista.setIdArtista(id);
            Artista updatedArtista = artistaRepository.save(artista);
            ArtistaDto updatedDto = artistaMapper.toDto(updatedArtista);
            
            return ResponseEntity.ok(new ApiResponse<>(true, "Artista actualizado exitosamente", updatedDto));
        } catch (Exception e) {
            log.error("Error al actualizar artista: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al actualizar artista: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteArtista(@PathVariable String id) {
        try {
            if (!artistaRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Artista no encontrado", null));
            }
            
            artistaRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Artista eliminado exitosamente", null));
        } catch (Exception e) {
            log.error("Error al eliminar artista: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error al eliminar artista: " + e.getMessage(), null));
        }
    }
} 