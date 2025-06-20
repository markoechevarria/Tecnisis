/*
 * Copyright (c)2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tecnisis.data

// Datos de ejemplo para pruebas de ViewModel relacionados con artistas y obras

const val MAX_NO_OF_ARTISTAS = 5
const val MAX_NO_OF_OBRAS = 5

// Lista de artistas de ejemplo (dni, nombre)
val artistasDeEjemplo: List<Pair<String, String>> = listOf(
    "12345678" to "Juan Pérez",
    "87654321" to "María García",
    "11223344" to "Carlos López",
    "44332211" to "Ana Torres",
    "55554444" to "Luis Fernández"
)

// Lista de obras de ejemplo (nombre, técnica, dimensiones, fecha)
val obrasDeEjemplo: List<Map<String, String>> = listOf(
    mapOf("nombre" to "Paisaje Nocturno", "tecnica" to "Óleo", "dimensiones" to "100x80", "fecha" to "2025-01-15"),
    mapOf("nombre" to "Retrato Abstracto", "tecnica" to "Acrílico", "dimensiones" to "60x90", "fecha" to "2025-01-14"),
    mapOf("nombre" to "Marina Azul", "tecnica" to "Acuarela", "dimensiones" to "50x70", "fecha" to "2025-02-10"),
    mapOf("nombre" to "Bosque Encantado", "tecnica" to "Óleo", "dimensiones" to "120x100", "fecha" to "2025-03-01"),
    mapOf("nombre" to "Ciudad Moderna", "tecnica" to "Mixta", "dimensiones" to "80x60", "fecha" to "2025-04-20")
)

// Utilidad para obtener un artista por DNI
fun obtenerArtistaPorDni(dni: String): String? = artistasDeEjemplo.find { it.first == dni }?.second

// Utilidad para obtener una obra por nombre
fun obtenerObraPorNombre(nombre: String): Map<String, String>? = obrasDeEjemplo.find { it["nombre"] == nombre } 