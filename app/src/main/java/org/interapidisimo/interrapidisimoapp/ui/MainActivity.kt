package org.interapidisimo.interrapidisimoapp.ui

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import dagger.hilt.android.AndroidEntryPoint
import org.interapidisimo.interrapidisimoapp.ui.theme.InterrapidisimoAppTheme
import org.interapidisimo.interrapidisimoapp.ui.view.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterrapidisimoAppTheme {
                val window = this.window
                val insetsController = WindowCompat.getInsetsController(window, window.decorView)

                // Ocultar barras
                SideEffect {
                    insetsController.systemBarsBehavior =
                        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    insetsController.hide(WindowInsetsCompat.Type.systemBars())
                }

                MainScreen()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    InterrapidisimoAppTheme {
        MainScreen()
    }
}