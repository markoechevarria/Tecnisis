package com.example.tecnisis

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.ComposeNavigator
import com.example.tecnisis.navigation.AppNavGraph
import com.example.tecnisis.navigation.Rutas
import com.example.tecnisis.data.UserPreferences

class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: NavHostController
    private lateinit var userPreferences: UserPreferences

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController().apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            userPreferences = UserPreferences(LocalContext.current)
            AppNavGraph(navController = navController, userPreferences = userPreferences)
        }

        // Esperar a que la composición se complete
        composeTestRule.waitForIdle()
    }

    @Test//bien
    fun navHost_verifyStartDestination_isLogin() {
        composeTestRule.waitForIdle()
        navController.assertCurrentRouteName(Rutas.LOGIN)
        composeTestRule.onNodeWithText("Iniciar sesión").assertIsDisplayed()
    }


    @Test//bien
    fun navHost_navigateToInicioScreen_viaLogin() {
        composeTestRule.waitForIdle()

        // En lugar de navegación directa, simula el flujo completo
        // O usa runOnUiThread para navegación programática segura
        composeTestRule.runOnUiThread {
            if (navController.currentBackStackEntry?.destination?.route != Rutas.INICIO) {
                navController.navigate(Rutas.INICIO)
            }
        }

        composeTestRule.waitForIdle()
        navController.assertCurrentRouteName(Rutas.INICIO)
        composeTestRule.onNodeWithText("TECNISIS").assertIsDisplayed()
    }

    @Test//bien
    fun navHost_registerButton_isEnabled() {
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("¿No tienes cuenta? Regístrate")
            .assertExists()
            .assertIsEnabled()
    }


    // FLUJO: Evaluar solicitud desde "Detalle de solicitud"
    @Test//bien
    fun flujo_evaluarSolicitud_desdeDetalle() {
        // Navegar a detalle de solicitud
        composeTestRule.runOnUiThread {
            navController.navigate(Rutas.DETALLE_SOLICITUD)
        }
        composeTestRule.waitForIdle()

        navController.assertCurrentRouteName(Rutas.DETALLE_SOLICITUD)
        composeTestRule.onNodeWithText("Detalle de solicitud").assertIsDisplayed()

        // Buscar y hacer click en botón evaluar
        composeTestRule.onNodeWithText("Evaluar", useUnmergedTree = true)
            .assertExists()
            .performClick()

        composeTestRule.waitForIdle()
        navController.assertCurrentRouteName(Rutas.EVALUAR_SOLICITUD)
        composeTestRule.onNodeWithText("Aprobar").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rechazar").assertIsDisplayed()
    }



    @Test//bien
    fun navigation_handleBackPress_properly() {
        composeTestRule.waitForIdle()

        // Navegar a una pantalla secundaria
        composeTestRule.onNodeWithText("¿No tienes cuenta? Regístrate").performClick()
        composeTestRule.waitForIdle()
        navController.assertCurrentRouteName(Rutas.REGISTER)

        // Simular back press
        composeTestRule.runOnUiThread {
            navController.popBackStack()
        }
        composeTestRule.waitForIdle()

        // Verificar que regresó correctamente
        navController.assertCurrentRouteName(Rutas.LOGIN)
    }

    // Verificar que el botón de regreso no aparece en la pantalla de inicio
    @Test
    fun inicio_noMuestraBotonRegresar() {
        // Asegúrate de estar en INICIO
        composeTestRule.runOnUiThread {
            navController.navigate(Rutas.INICIO)
        }
        composeTestRule.waitForIdle()
        // Busca el botón de retroceso por contentDescription (ajusta si tienes un string resource)
        composeTestRule.onNodeWithContentDescription("Atrás", useUnmergedTree = true)
            .assertDoesNotExist()
    }

}

// Extensión mejorada con mejor manejo de errores
fun NavHostController.assertCurrentRouteName(expectedRouteName: String) {
    val currentRoute = currentBackStackEntry?.destination?.route
    assert(currentRoute == expectedRouteName) {
        "La ruta actual es '$currentRoute', pero se esperaba '$expectedRouteName'. " +
                "Destino actual: ${currentDestination?.displayName ?: "null"}"
    }
}