package org.interapidisimo.interrapidisimoapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import org.interapidisimo.interrapidisimoapp.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    snackbarHostState: SnackbarHostState,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("cGFtLm1lcmVkeTIx") }
    var password by remember { mutableStateOf("SW50ZXIyMDIx") }
    var passwordVisible by remember { mutableStateOf(false) }

    val event by loginViewModel.message.observeAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(event) {
        event?.getContentIfNotHandled()?.let { message ->
            keyboardController?.hide()
            snackbarHostState.showSnackbar(message)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                //val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    //Icon(imageVector = image, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                loginViewModel.login(
                    user = username,
                    password = password
                )
            }
        ) {
            Text("Iniciar sesion")
        }
    }
}