package org.interapidisimo.interrapidisimoapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import org.interapidisimo.interrapidisimoapp.ui.theme.InterrapidisimoAppTheme
import org.interapidisimo.interrapidisimoapp.ui.viewmodel.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import org.interapidisimo.interrapidisimoapp.ui.view.BottomNavigationOption
import org.interapidisimo.interrapidisimoapp.ui.view.NavigationHost
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mainViewModel.getCurrentVersion()
        /*mainViewModel.login(
            loginRequestBody = LoginRequestBody(
                mac = "",
                applicationName = "Controller APP",
                password = "SW50ZXIyMDIx",
                path = "",
                user = "cGFtLm1lcmVkeTIx"
            )
        )*/
        //mainViewModel.getTablesInfoFromServer()
        //mainViewModel.getLocalitiesFromServer()
        enableEdgeToEdge()
        setContent {
            InterrapidisimoAppTheme {
                MainScreenOne(mainViewModel)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenOne(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val navigationItem = listOf(
        BottomNavigationOption.TablesInfoScreen,
        BottomNavigationOption.LocalitiesScreen
    )
    mainViewModel.getCurrentVersion()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(navController, navigationItem)
        },
        floatingActionButton = {
            Fab(mainViewModel)
        },
        isFloatingActionButtonDocked = true
    ){
        NavigationHost(navController, mainViewModel)
    }

    /*if(mainViewModel.isDialogShown){
        CustomDialog(mainViewModel)
    }*/
}

@Composable
fun Fab(mainViewModel: MainViewModel){
    FloatingActionButton(
        onClick = {
            //mainViewModel.onBuyClick()
        },
        backgroundColor = androidx.compose.material.MaterialTheme.colors.primaryVariant
    ){
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Recompensas"
        )
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}

@Composable
fun BottomNavigation(
    navController: NavHostController,
    bottomNavigationOptions: List<BottomNavigationOption>
){
    BottomAppBar(
        cutoutShape = androidx.compose.material.MaterialTheme.shapes.small.copy(
            CornerSize(percent = 45)
        )
    ){
        BottomNavigation{
            val currentRoute = currentRoute(navController = navController)
            bottomNavigationOptions.forEach{ bottomNavigationItem ->
                BottomNavigationItem(
                    selected = currentRoute == bottomNavigationItem.route,
                    onClick = {navController.navigate(bottomNavigationItem.route)},
                    icon = {
                        Icon(
                            painter = painterResource(id = bottomNavigationItem.icon),
                            contentDescription = bottomNavigationItem.tittle
                        )
                    },
                    label = {
                        Text(bottomNavigationItem.tittle)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterrapidisimoAppTheme {
        //Greeting("Android")
    }
}