package org.interapidisimo.interrapidisimoapp.ui.view

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.interapidisimo.interrapidisimoapp.ui.viewmodel.LoginViewModel
import org.interapidisimo.interrapidisimoapp.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val isLoggedIn by loginViewModel.isLoggedIn.observeAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val event by mainViewModel.message.observeAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        mainViewModel.getCurrentVersion()
    }

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn == true && currentRoute == Screen.Login.route) {
            navController.navigate(Screen.Localities.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    LaunchedEffect(event) {
        event?.getContentIfNotHandled()?.let { message ->
            keyboardController?.hide()
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    val activity = context.findActivity()
                    activity?.finishAffinity()
                }
            ) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Salir")
            }
        },
        floatingActionButtonPosition = FabPosition.End, // o Center
        isFloatingActionButtonDocked = false,
        bottomBar = {
            //if (isLoggedIn && currentRoute in listOf(Screen.Localities.route, Screen.Tables.route)) {
                BottomNavigation {
                    listOf(Screen.Localities, Screen.Tables).forEach { screen ->
                        BottomNavigationItem(
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(Screen.Localities.route)
                                    launchSingleTop = true
                                }
                            },
                            label = { Text(screen.label) },
                            icon = {
                                Icon(screen.icon, contentDescription = screen.label)
                            }
                        )
                    }
                }
            //}
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) { LoginScreen(snackbarHostState) }
            composable(Screen.Localities.route) { LocalitiesScreen(snackbarHostState) }
            composable(Screen.Tables.route) { TablesInfoScreen(snackbarHostState) }
        }
    }
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}