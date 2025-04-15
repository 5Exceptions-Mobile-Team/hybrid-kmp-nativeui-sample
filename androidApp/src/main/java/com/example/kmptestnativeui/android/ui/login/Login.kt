package com.example.kmptestnativeui.android.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kmptestnativeui.android.Screen
import com.example.kmptestnativeui.android.viewmodel.WeatherViewModel
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import kotlin.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController) {
    var input = rememberSaveable { mutableStateOf("") }
    var passWord = rememberSaveable { mutableStateOf("") }

    KoinContext {
        val myViewModel = koinViewModel<WeatherViewModel>()
        Column (modifier = Modifier.background(Color.White).fillMaxSize()) {
            Text(text= "Login", modifier = Modifier.fillMaxWidth().padding(10.dp,40.dp), textAlign = TextAlign.Center, fontSize = 24.sp)

            OutlinedTextField(
                value = input.value,
                onValueChange = { it-> input.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(10.dp,40.dp,10.dp,10.dp))

            OutlinedTextField(
                value = passWord.value,
                onValueChange = { it-> passWord.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(10.dp,10.dp))



            Button(onClick = { navController.navigate(Screen.Dashboard.route)  }, modifier = Modifier.fillMaxWidth().padding(10.dp,10.dp)) {
                Text(text= "Login", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp)
            }

        }
    }
}
