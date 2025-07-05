package com.markoen.tecnisisapp.ui.views.anfitrion.registrarObra

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.markoen.tecnisisapp.R
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import coil.compose.rememberAsyncImagePainter
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.core.content.ContextCompat
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.markoen.tecnisisapp.domain.models.Tecnica
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun PantallaRegistrarObra(
    id: Int,
    id_perfil: Int,
    id_artista: Int,
    navegarElegirExperto: (Int, Int, Int, Int) -> Unit,
    registrarObraViewModel: RegistrarObraViewModel = hiltViewModel(),
    navegarAtras: () -> Unit
) {
    val scrollState = rememberScrollState()
    val registrarObraUIState by registrarObraViewModel.uiState.collectAsState()
    registrarObraViewModel.obtenerDatos(id, id_perfil, id_artista)

    val context = LocalContext.current

    var tempPhotoUri by remember { mutableStateOf<Uri?>(null) }
    val photoFile = remember { File(context.cacheDir, "temp_obra_photo_${System.currentTimeMillis()}.jpg") }
    val photoUri: Uri = remember(photoFile) { FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", photoFile) }

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) { tempPhotoUri = photoUri; registrarObraViewModel.actualizarFotoUriTemporal(photoUri) }
            else { tempPhotoUri = null; registrarObraViewModel.actualizarFotoUriTemporal(null) }
        }
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean -> if (isGranted) { takePictureLauncher.launch(photoUri) } }
    )

    val checkAndRequestCameraPermission: () -> Unit = {
        when {
            ContextCompat.checkSelfPermission( context, Manifest.permission.CAMERA ) == PackageManager.PERMISSION_GRANTED -> { takePictureLauncher.launch(photoUri) }
            else -> { requestPermissionLauncher.launch(Manifest.permission.CAMERA) }
        }
    }

    LaunchedEffect(Unit) {
        if (registrarObraUIState.fecha_obra == "") {
            val calendar = Calendar.getInstance()
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            registrarObraViewModel.actualizarFechaObra(format.format(calendar.time))
        }
    }

    if (registrarObraUIState.isLoading) {
        Dialog(
            onDismissRequest = {  },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                modifier = Modifier.size(100.dp).background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.image_fx_redondeada) ,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = "TECNISIS",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clickable {  }
        ) {
            IconButton( onClick = { navegarAtras() } ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Registro de Obra", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Ingresar Datos", style = MaterialTheme.typography.titleMedium)

                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(registrarObraUIState.artista.nombre, style = MaterialTheme.typography.titleMedium)
                    }
                }

                camposTexto("Obra",{ registrarObraViewModel.actualizarNombreObra(nombre = it) }, registrarObraUIState.nombre_obra )
                DropdownInputField(
                    label = "Técnica",
                    options = registrarObraUIState.tecnicasLista,
                    selectedTecnicaId = registrarObraUIState.id_tecnica_obra,
                    onOptionSelected = { idTecnicaSeleccionada ->
                        registrarObraViewModel.actualizarTecnicaObra(idTecnicaSeleccionada)
                    }
                )

                DateInputField(
                    label = "Fecha",
                    selectedDate = registrarObraUIState.fecha_obra,
                    onDateSelected = { newDate ->
                        registrarObraViewModel.actualizarFechaObra(newDate)
                    }
                )

                /*
                camposTextoFinal("Dimensiones", { registrarObraViewModel.actualizarDimensionesObra(dimensiones = it) }, registrarObraUIState.dimensiones_obra )
                 */

                DropdownStringInputField(
                    label = "Dimensiones",
                    options = listOf("Pequeño", "Mediano", "Grande"),
                    selectedOption = registrarObraUIState.dimensiones_obra,
                    onOptionSelected = { selectedDim ->
                        registrarObraViewModel.actualizarDimensionesObra(dimensiones = selectedDim)
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Foto de la Obra", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                tempPhotoUri?.let { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = "Foto de Obra",
                        modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(8.dp)),
                        alignment = Alignment.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = { checkAndRequestCameraPermission() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Tomar Foto")
                }

                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        registrarObraViewModel.registrarObra()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    enabled = tempPhotoUri != null && registrarObraUIState.photoUploadState !is PhotoUploadState.Loading
                ) {
                    Text("Registrar Obra")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "© 2025 Todos los derechos reservados",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }

    if (registrarObraUIState.obra_registrada) {
        navegarElegirExperto(id, id_perfil, id_artista, registrarObraUIState.id_obra)
    }
}

@Composable
fun camposTexto (label: String, cambiarValor: (String) -> Unit, _obra: String ) {
    OutlinedTextField(
        value = _obra,
        onValueChange = cambiarValor,
        label = { Text(label) },
        leadingIcon = { Icon(Icons.Default.Person, null) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(onNext = { defaultKeyboardAction(ImeAction.Next) }),
    )
}

@Composable
fun camposTextoFinal (label: String, cambiarValor: (String) -> Unit, _obra: String ) {
    OutlinedTextField(
        value = _obra,
        onValueChange = cambiarValor,
        label = { Text(label) },
        leadingIcon = { Icon(Icons.Default.Person, null) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(onNext = { defaultKeyboardAction(ImeAction.Done) }),
    )
}

@Composable
fun camposNumero (label: String, cambiarValor: (String) -> Unit, _obra: String ) {
    OutlinedTextField(
        value = _obra,
        onValueChange = cambiarValor,
        label = { Text(label) },
        leadingIcon = { Icon(Icons.Default.Person, null) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(onNext = { defaultKeyboardAction(ImeAction.Next) }),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownInputField(
    label: String,
    options: List<Tecnica>,
    selectedTecnicaId: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val selectedTecnica = options.find { it.id == selectedTecnicaId }
    val selectedText = selectedTecnica?.nombre_tecnica ?: "Seleccione una opción"

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { tecnica ->
                DropdownMenuItem(
                    text = { Text(tecnica.nombre_tecnica) },
                    onClick = {
                        onOptionSelected(tecnica.id)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownStringInputField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val selectedText = if (selectedOption.isBlank()) "Seleccione una opción" else selectedOption

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { optionText ->
                DropdownMenuItem(
                    text = { Text(optionText) },
                    onClick = {
                        onOptionSelected(optionText)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DateInputField(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            calendar.set(year, month, dayOfMonth)
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            onDateSelected(format.format(calendar.time))
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {  },
        label = { Text(label) },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(painter = painterResource(id = R.drawable.calendar_symbol_svgrepo_com), contentDescription = "Seleccionar fecha")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() }
    )
}