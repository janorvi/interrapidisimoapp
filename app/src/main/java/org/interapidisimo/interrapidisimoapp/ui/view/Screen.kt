package org.interapidisimo.interrapidisimoapp.ui.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Login : Screen("login", "Login", Icons.Default.Place)
    object Localities : Screen("localities", "Localities", Icons.Default.LocationOn)
    object Tables : Screen("tables", "Tables", Icons.Default.List)
}