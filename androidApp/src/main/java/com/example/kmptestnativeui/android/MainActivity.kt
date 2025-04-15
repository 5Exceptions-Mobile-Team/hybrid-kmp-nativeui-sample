package com.example.kmptestnativeui.android


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmptestnativeui.Greeting

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material3.TextField
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kmptestnativeui.android.ui.dashboard.Dashboard
import com.example.kmptestnativeui.android.ui.detail.Detail
import com.example.kmptestnativeui.android.ui.login.Login
import com.example.kmptestnativeui.android.viewmodel.WeatherViewModel
import com.example.kmptestnativeui.core.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    val viewModel: WeatherViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost()
                }
            }
        }
//        lifecycleScope.launch(Dispatchers.IO) {
//            observer()
//        }
    }

}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            Login(navController)
        }
        composable(Screen.Dashboard.route) {
            Dashboard(navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("location") { type = NavType.StringType })
        ) { backStackEntry ->
            val location = backStackEntry.arguments?.getString("location") ?: ""
            val lat = backStackEntry.arguments?.getString("lat") ?: ""
            val long = backStackEntry.arguments?.getString("long") ?: ""
            Detail(navController, location,lat,long)
        }
    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object Detail : Screen("detail/{location}/{lat}/{long}") {
        fun createRoute(location: String,lat : String,long : String): String = "detail/$location/$lat/$long"
    }
}