package com.example.tecnisis.data

object listaUsuarios {
    val usuarios: List<Usuario> = listOf<Usuario>(
        Usuario(1,1, "Ana", "ana@gmail.com", "ana123"),
        Usuario(2,1, "Angel", "angel@gmail.com", "angel123"),
        Usuario(3,1, "Alex", "alex@gmail.com", "alex123"),
        Usuario(4,1,"Aldo", "aldo@gmail.com", "aldo123"),
        Usuario(5,2, "Davinci", "davinci@gmail.com", "davinci123"),
        Usuario(6,2, "Van Gogh", "vangogh@gmail.com", "vangogh123"),
        Usuario(7,2, "Picasso", "picasso@gmail.com", "picasso123"),
        Usuario(8,3, "Eduardo", "eduardo@gmail.com", "eduardo123"),
        Usuario(9,3, "Ernesto", "ernesto@gmail.com", "ernesto123"),
        Usuario(10,4, "Gerardo", "gerardo@gmail.com", "gerardo123")
    )
}
object ListaPerfiles {
    val perfiles: List<Perfil> = listOf<Perfil>(
        Perfil(1, "Anfitrion"),
        Perfil(2, "Evaluador Artistico"),
        Perfil(3, "Evaluador Economico"),
        Perfil(4, "Gerente"),
    )
}
object ListaOpciones {
    val opciones: List<Opcion> = listOf<Opcion>(
        Opcion(1,1, "Busqueda Artista"),
        Opcion(2,1, "Registrar artista"),
        Opcion(3,1, "Solicitudes registradas"),

        Opcion(4,2, "Ver solicitudes registradas"),
        Opcion(5,2, "Ver lista obras aprobadas"),

        Opcion(6,3, "Solicitudes registradas"),
        Opcion(7,3, "Evaluar obras aprobadas"),

        Opcion(8,4, "Expertos"),
        Opcion(9,4, "Tecnicas"),
        Opcion(10,4, "Reportes"),
    )
}

object ListaArtistas {
    val artistasRegistrados: List<Artista> = listOf<Artista>(
        Artista( 1, "NombreArtista1", "ArtistaDni1", "DireccionArtista1", "Telefono1" ),
        Artista( 2, "NombreArtista2", "ArtistaDni2", "DireccionArtista2", "Telefono2" ),
        Artista( 3, "NombreArtista3", "ArtistaDni3", "DireccionArtista3", "Telefono3" ),
        Artista( 4, "NombreArtista4", "ArtistaDni4", "DireccionArtista4", "Telefono4" )
    )
}
object ListaObras {
    val obrasRegistrados: List<Obra> = listOf<Obra>(
        Obra( 1, 1, 1, "Hoy", "20x20" ),
        Obra( 2, 1, 2, "Ayer", "20x30" ),
        Obra( 3, 2, 2, "Anteayer", "20x40" ),
        Obra( 4, 2, 1, "Hace 3 dias", "20x50" ),
        Obra( 5, 2, 2, "Hace 3 dias", "60x60" ),
        Obra( 6, 2, 2, "El mes pasado", "70x70" ),
        Obra( 7, 3, 2, "El año pasado", "80x80" ),
    )
}
object ListaTecnicas {
    val tecnicasRegistradas: List<Tecnica> = listOf<Tecnica>(
        Tecnica( 1, "Tecnica1", "Alta"),
        Tecnica( 2, "Tecnica2", "Media"),
    )
}
object ListaEvaluadoresArtisticos {
    val evaluadoresArtisticos: List<Usuario> = listOf<Usuario>(
        Usuario(6,2, "Van Gogh", "vangogh@gmail.com", "vangogh123"),
        Usuario(7,2, "Picasso", "picasso@gmail.com", "picasso123"),
    )
}
object ListaSolicitudesRegistradas {
    val solicitudesRegistradas: List<Solicitud> = listOf<Solicitud>(
        Solicitud( 1,1,1,6 ),
        Solicitud( 2,2,2,6 ),
        Solicitud( 3,2,3,7 ),
        Solicitud( 4,3,7,2, aprobadaEvaluadorArtistico = true, aprobadaEValuadorEconomico = true )
    )
}

class Perfil (
    val id: Int = 0,
    val nombre: String = "",
)
class Opcion (
    val id: Int = 0,
    val id_perfil: Int = 0,
    val texto: String = "",
)
class Usuario(
    val id: Int,
    val id_perfil: Int,
    val nombre: String,
    val correo: String,
    val contrasena: String
)
class Artista (
    val id: Int = 0,
    val nombre: String = "",
    val dni: String = "",
    val direccion: String = "",
    val telefono: String = ""
)
class Obra (
    val id: Int = 0,
    val id_artista: Int = 0,
    val id_tecnica: Int = 0,
    val fecha: String = "",
    val dimensiones: String = ""
)
class Tecnica (
    val id: Int = 0,
    val nombre_tecnica: String = "",
    val nivel_apreciacion: String = "",
)
class Solicitud (
    val id: Int = 0,
    val id_artista: Int,
    val id_obra: Int,
    val id_experto: Int,
    val aprobadaEvaluadorArtistico: Boolean = false,
    val aprobadaEValuadorEconomico: Boolean = false,
    val porcentaje_ganancia: Double = 0.0,
    val valor: Double = 0.0
)