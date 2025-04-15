package com.example.kmptestnativeui.weather.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(

    @SerialName("latitude"              ) var latitude             : String?       = null,
    @SerialName("longitude"             ) var longitude            : String?       = null,
    @SerialName("generationtime_ms"     ) var generationtime_ms     : String?       = null,
    @SerialName("utc_offset_seconds"    ) var utc_offset_seconds     : String?          = null,
    @SerialName("timezone"              ) var timezone             : String?       = null,
    @SerialName("timezone_abbreviation" ) var timezone_abbreviation : String?       = null,
    @SerialName("elevation"             ) var elevation            : String?          = null,
    @SerialName("current_units"         ) var current_units         : CurrentUnits? = CurrentUnits(),
    @SerialName("current"               ) var current              : Current?      = Current(),
    @SerialName("hourly_units"          ) var hourly_units          : HourlyUnits?  = HourlyUnits(),
    @SerialName("hourly"                ) var hourly               : Hourly?       = Hourly()
//    val latitude: String,
//    val longitude: String,
//    val generationtime_ms: String,
//    val utc_offset_seconds: String,
//    val timezone: String,
//    val timezone_abbreviation: String,
//    val elevation: String,
//    val current_units: CurrentUnits,
//    val current: CurrentWeather,
//    val hourly_units: HourlyUnits,
//    val hourly: HourlyWeather
)

//@Serializable
//data class CurrentUnits(
//    val time: String,
//    val Stringerval: String,
//    val temperature_2m: String,
//    val wind_speed_10m: String
//)
//
//@Serializable
//data class CurrentWeather(
//    val time: String,
//    val Stringerval: String,
//    val temperature_2m: String,
//    val wind_speed_10m: String
//)

//@Serializable
//data class HourlyUnits(
//    val time: String,
//    val temperature_2m: String,
//    val relative_humidity_2m: String? = null,  // Optional in case not always present
//    val wind_speed_10m: String
//)
//
//@Serializable
//data class HourlyWeather(
//    val time: List<String>,
//    val temperature_2m: List<String>,
//    val relative_humidity_2m: List<String>? = null, // Optional if present
//    val wind_speed_10m: List<String>
//)
@Serializable
data class CurrentUnits (

    @SerialName("time"           ) var time          : String? = null,
    @SerialName("Stringerval"       ) var Stringerval      : String? = null,
    @SerialName("temperature_2m" ) var temperature_2m : String? = null,
    @SerialName("wind_speed_10m" ) var wind_speed_10m  : String? = null

)
@Serializable
data class Current (

    @SerialName("time"           ) var time          : String? = null,
    @SerialName("Stringerval"    ) var Stringerval   : String?    = null,
    @SerialName("temperature_2m" ) var temperature_2m : String?    = null,
    @SerialName("wind_speed_10m" ) var wind_speed_10m  : String? = null

)


@Serializable
data class HourlyUnits (

    @SerialName("time"                 ) var time               : String? = null,
    @SerialName("temperature_2m"       ) var temperature_2m      : String? = null,
    @SerialName("relative_humidity_2m" ) var relative_humidity_2m : String? = null,
    @SerialName("wind_speed_10m"       ) var wind_speed_10m       : String? = null

)


@Serializable
data class Hourly (

    @SerialName("time"                 ) var time               : ArrayList<String> = arrayListOf(),
    @SerialName("temperature_2m"       ) var temperature_2m      : ArrayList<String> = arrayListOf(),
    @SerialName("relative_humidity_2m" ) var relative_humidity_2m : ArrayList<String>    = arrayListOf(),
    @SerialName("wind_speed_10m"       ) var wind_speed_10m       : ArrayList<String> = arrayListOf()

)