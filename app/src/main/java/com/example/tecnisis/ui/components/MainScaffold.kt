package com.example.tecnisis.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    title: String,
    navController: NavController,
    showBottomBar: Boolean = false,
    drawerItems: List<String> = listOf("Inicio", "Técnicas", "Expertos", "Reportes"),
    onDrawerItemClick: (String) -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "TECNISIS",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Divider()
                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(item) },
                        selected = false,
                        onClick = {
                            onDrawerItemClick(item)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(title) },
                    navigationIcon = {
                        IconButton(onClick = {
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                        }) {
                            Icon(Icons.Default.Settings, contentDescription = "Ajustes")
                        }
                    }
                )
            },
            bottomBar = {
                if (showBottomBar) {
                    BottomAppBar {
                        NavigationBar {
                            NavigationBarItem(
                                selected = false,
                                onClick = {  },
                                icon = { Icon(Icons.Default.Person, contentDescription = null) },
                                label = { Text("Perfil") }
                            )
                            NavigationBarItem(
                                selected = false,
                                onClick = {  },
                                icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                                label = { Text("Ajustes") }
                            )
                        }
                    }
                }
            },
            content = { paddingValues ->
                content(paddingValues)
            }
        )
    }
}
