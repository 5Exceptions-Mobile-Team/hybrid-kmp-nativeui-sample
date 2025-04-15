package com.example.kmptestnativeui.android.ui.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kmptestnativeui.android.Screen
import com.example.kmptestnativeui.android.viewmodel.WeatherViewModel
import com.example.kmptestnativeui.core.NetworkResult
import com.example.kmptestnativeui.weather.domain.model.WeatherResponse
import com.google.gson.Gson
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Detail(navController : NavHostController? = null,location : String= "",lat : String = "", long: String = ""){
    KoinContext {
        val myViewModel = koinViewModel<WeatherViewModel>()
        val state by myViewModel.weatherStateFlow.collectAsState()
        //observer(myViewModel,setter)
        LaunchedEffect(Unit) {
            myViewModel.getWeather(lat,long)
        }
        Column (Modifier
            .fillMaxSize()
            .background(Color.White)) {
            Text(text= location, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 40.dp), textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            when (val result = state) {
                is NetworkResult.Error -> {
                    TextInput("Error")
                    TextInput("Latitude : ${lat}")
                    TextInput("Longitude : ${long}")
                }
                NetworkResult.Loading -> {
                    TextInput("Loading")
                }
                is NetworkResult.Success -> {
                    if(result.data != null){
                        val input = Gson().fromJson(result!!.data.toString(), WeatherResponse::class.java)
                        TextInput("Latitude : ${input.latitude?:""}")
                        TextInput("Longitude : ${input.longitude?:""}")
                        TextInput("Temperature : ${input?.current?.temperature_2m?:""} ${input?.current_units?.temperature_2m?:""}")
                        TextInput("Wind Speed : ${input?.current?.wind_speed_10m?:""} ${input?.current_units?.wind_speed_10m?:""}")
                        BoldTextInput("Hourly :")
                        DetailListScreen(input.hourly!!.temperature_2m,input.hourly!!.wind_speed_10m,input.hourly!!.relative_humidity_2m,
                            input.hourly_units!!.temperature_2m,input.hourly_units!!.wind_speed_10m,input.hourly_units!!.relative_humidity_2m)
                    }
                }

                null -> {}
            }

        }
    }
}

@Composable
fun TextInput(label : String, modifier : Modifier = Modifier){
    Text(text= label, modifier = modifier
        .fillMaxWidth()
        .padding(10.dp, 5.dp), textAlign = TextAlign.Center, fontSize = 18.sp)
}
@Composable
fun BoldTextInput(label : String, modifier : Modifier = Modifier){
    Text(text= label, modifier = modifier
        .fillMaxWidth()
        .padding(10.dp, 5.dp), textAlign = TextAlign.Start, fontSize = 18.sp
        ,style = MaterialTheme.typography.bodyLarge
    )
}

@Preview
@Composable
fun DetailView(){
    Detail()
}




@Composable
fun DetailListScreen(temperature: List<String>,windSpeed: List<String>,relativeHumidity: List<String>,
                     temperatureUnit: String?, windSpeedUnit: String?,relativeHumidityUnit: String?) {
    val state = rememberScrollState()
    LazyColumn  (
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                state,
                orientation = androidx.compose.foundation.gestures.Orientation.Vertical
            )
            .padding(16.dp)
    ) {
        items(temperature.size) { index ->
            DetailItem ("${temperature[index]} $temperatureUnit","${windSpeed[index]} $windSpeedUnit","${relativeHumidity[index]} $relativeHumidityUnit")
        }
    }
}

@Composable
fun DetailItem(temperature: String,windSpeed: String,relativeHumidity: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column {
            Text(
                text = "temperature : $temperature",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "windSpeed : $windSpeed",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "relativeHumidity : $relativeHumidity",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}