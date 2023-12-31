@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.loginformjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginformjetpackcompose.ui.theme.LoginFormJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Creamos una variable para controlar la navegación (navController).
            // La función rememberNavController() es similar a remember, pero específicamente para el NavController
            val navController = rememberNavController()
            // Dentro de la función NavHost, definiremos el grafo de navegación de la aplicación
            NavHost(navController, startDestination = "login"){ // startDestination: indica la primera pantalla que tendrá que cargar
                // En la función composable() definimos una ruta de destino (por parametro), que el navController utilizará para dirigirse.
                composable("login"){
                    // Dentro de esta misma funcion composable() indicamos el (o los) composables que tendrá que cargar.
                    LoginForm(
                        onLogin = {
                            // Cuando se ejecute la función onLogin, es decir onClick de LoginButton,
                            // el navController cambiará su navegación hacia la ruta "main":
                            navController.navigate("main")  // llamará a composable(route = "main")
                        }
                    )
                }

                composable("main"){
                    Main()  // Contiene los composables para la pantalla despues del login.
                }

            }
        }
    }
}

@Preview
@Composable
fun Main() {
    Screan {
        Box(
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Main Screen",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Composable
fun LoginForm(onLogin: () -> Unit) {
    Screan {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ){
            /* La función especial de Jetpack Compose llamada rebember, la primera vez que se ejecute
               creará el mutableStateOf, pero las siguientes veces utilizará el valor previo.
               by --> Delegado. Para actualizar directamente el estado sin tener que utilizar el .value
            */
            var user by remember { mutableStateOf("") }
            var pass by remember { mutableStateOf("") }
            val buttonEnabled = user.isNotEmpty() && pass.isNotEmpty()

            UserField(value = user, onValueChange = { user = it })
            PasswordField(value = pass, onValueChange = { pass = it })
            LoginButton(buttonEnabled, onLogin)
        }
    }
}

@Composable
private fun LoginButton(enabled: Boolean, onLogin: () -> Unit) {
    Button(
        onClick = onLogin,
        enabled = enabled
    ) {
        Text(text = "Login")
    }
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var passVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        label = { Text(text = "Password") },
        visualTransformation = if(passVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconToggleButton(checked = passVisible, onCheckedChange = { passVisible = it }) {
                val icon = if(passVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    )
}

@Composable
private fun UserField(
    value: String,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        label = { Text(text = "User") }
    )
}

@Composable
fun Screan(content: @Composable () -> Unit) {
    LoginFormJetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}