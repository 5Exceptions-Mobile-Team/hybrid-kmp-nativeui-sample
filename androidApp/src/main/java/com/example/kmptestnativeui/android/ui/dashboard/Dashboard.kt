package com.example.kmptestnativeui.android.ui.dashboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kmptestnativeui.android.Screen

@Composable
fun Dashboard(navController : NavHostController? = null){
    Column (Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Text(text= "Dashboard", modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 40.dp), textAlign = TextAlign.Center, fontSize = 18.sp)
        val locationList = arrayListOf("New York", "London", "Tokyo", "Sydney")
        LocationListScreen(locations = locationList,navController)
    }
}


@Preview
@Composable
fun DashboardView(){
    Dashboard()
}

@Composable
fun LocationListScreen(locations: List<String>,navController : NavHostController? = null) {
    val latList = arrayListOf("40.730610", "51.509865", "35.6895", "-33.865143")
    val longList = arrayListOf("-73.935242", "-0.118092", "139.69171", "151.209900")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(locations.size) { location ->
            LocationItem(locations[location]){
                Log.d("TAG", "LocationListScreen: $it")
                val index = locations.indexOf(it)
                Log.d("TAG", "LocationListScreen: $index")
                if(navController != null){
                    navController!!.navigate(Screen.Detail.createRoute(it,latList[index],longList[index]))
                }else{
                    Log.d("TAG", "LocationListScreen: null")
                }
            }
        }
    }
}

@Composable
fun LocationItem(location: String,onClickListener: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClickListener(location) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Text(
            text = location,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


