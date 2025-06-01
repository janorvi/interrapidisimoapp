package org.interapidisimo.interrapidisimoapp.ui.view

import org.interapidisimo.interrapidisimoapp.R

sealed class BottomNavigationOption(
    val icon: Int,
    val tittle: String,
    val route: String
){
    object TablesInfoScreen: BottomNavigationOption(R.drawable.outline_check_circle_24, "Tablas", "tables_info_screen")
    object LocalitiesScreen: BottomNavigationOption(R.drawable.outline_cancel_24, "Localidades", "localities_screen")
}