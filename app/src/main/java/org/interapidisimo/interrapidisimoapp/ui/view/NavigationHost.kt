package org.interapidisimo.interrapidisimoapp.ui.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.interapidisimo.interrapidisimoapp.ui.viewmodel.MainViewModel

@Composable
fun NavigationHost(navHostController: NavHostController, mainViewModel: MainViewModel){
    NavHost(
        navController = navHostController,
        startDestination = BottomNavigationOption.TablesInfoScreen.route
    ){
        composable(BottomNavigationOption.TablesInfoScreen.route){
            TablesInfoScreen(mainViewModel = mainViewModel)
        }
        composable(BottomNavigationOption.LocalitiesScreen.route){
            LocalitiesScreen(mainViewModel = mainViewModel)
        }
    }
}
