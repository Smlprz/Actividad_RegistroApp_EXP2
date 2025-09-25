package com.example.registroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.registroapp.ui.theme.RegistroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistroAppTheme {
                //Nombre de las funciones que tienen el main
                loginScreen()
            }
        }
    }
}
//Composable android
@Composable
fun loginScreen() {
    //Estados o variables de mi app
    var usuario by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var confirmarClave by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var sede by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize() //Ocupa toda la pantalla
            .background(Color(0xFFEDF2F7)) //Color Gris claro 0xFF
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally, //Centrado horizontal
        verticalArrangement = Arrangement.Center //Centrado vertical
    ){ //Logo DuocUc
        Image(
            painter = painterResource(id=R.drawable.logo_duoc), //Extracción logo DuocUc
            contentDescription = "Logotip oficial de DUOC UC",
            modifier = Modifier
                .height(100.dp)
                .padding(bottom=32.dp)
        )
        //Campos User
        TextField(
            value = usuario,
            onValueChange = {usuario = it}, //Actualiza el estado
            label = {Text("Usuario",)},
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()//Ocultamos caracteres
        )
        //Campo Password
        TextField(
            value = clave,
            onValueChange = {clave = it},
            label = {Text("contraseña")},
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        //Campo ConfirmarClave
        TextField(
            value = confirmarClave,
            onValueChange = {confirmarClave = it},
            label = {Text("confirmar contraseña")},
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        //Campo Sede
        TextField(
            value = sede,
            onValueChange = {sede = it},
            label = {Text("Ingrese el nombre de la sede")},
            singleLine = true,
        )

        Spacer(Modifier.height(20.dp))
        //BTO login (Usuario, contraseña, confirmarContraseña)
        Button(onClick = {
            mensaje = if (usuario.isBlank() || clave.isBlank() || confirmarClave.isBlank()) {
                "Completa todos los campos"
            } else if (clave != confirmarClave) {
                "Las contraseñas no coinciden"
            } else if (usuario == "admin" && clave == "colojew66") {
                "Bienvenido, $usuario, de la sede, $sede, "
            } else {
                "Usuario o contraseña incorrecta"
            }
    },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFc107),
                contentColor = Color (0XFF000000)
            )
        )
        {
            Text("Registrar")
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
    if (mensaje.isNotEmpty()) {
        Text(
            text = mensaje,
            fontSize = 18.sp,
            color = if (mensaje.contains("✅")) Color(0xFF2E7D32) else Color(0xFFC62828)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    RegistroAppTheme {
        loginScreen()
    }
}
