package org.interapidisimo.interrapidisimoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import org.interapidisimo.interrapidisimoapp.data.model.LoginRequestBody
import org.interapidisimo.interrapidisimoapp.ui.theme.InterrapidisimoAppTheme
import org.interapidisimo.interrapidisimoapp.ui.viewmodel.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.getCurrentVersion()
        mainViewModel.login(
            loginRequestBody = LoginRequestBody(
                mac = "",
                applicationName = "Controller APP",
                password = "SW50ZXIyMDIx",
                path = "",
                user = "cGFtLm1lcmVkeTIx"
            )
        )
        mainViewModel.getDatabaseSchema()
        mainViewModel.getLocalities()
        /*enableEdgeToEdge()
        setContent {
            InterrapidisimoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }*/
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterrapidisimoAppTheme {
        Greeting("Android")
    }
}