@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.loginformjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginformjetpackcompose.ui.theme.LoginFormJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginForm()
        }
    }
}

@Preview
@Composable
fun LoginForm() {
    Screan {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ){
            UserField()
            PasswordField()
            LoginButton()
        }
    }
}

@Composable
private fun LoginButton() {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Login")
    }
}

@Composable
private fun PasswordField() {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text(text = "Password") }
    )
}

@Composable
private fun UserField() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
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