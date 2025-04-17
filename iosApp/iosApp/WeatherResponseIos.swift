//
//  WeatherResponseIos.swift
//  iosApp
//
//  Created by admin on 16/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation

struct WeatherResponseIos: Codable {
    let latitude: Double
    let longitude: Double
    let generationTimeMs: Double
    let utcOffsetSeconds: Int
    let timezone: String
    let timezoneAbbreviation: String
    let elevation: Double
    let currentUnits: CurrentUnits
    let current: CurrentWeather
    let hourlyUnits: HourlyUnits
    let hourly: HourlyWeather

    enum CodingKeys: String, CodingKey {
        case latitude, longitude
        case generationTimeMs = "generationtime_ms"
        case utcOffsetSeconds = "utc_offset_seconds"
        case timezone
        case timezoneAbbreviation = "timezone_abbreviation"
        case elevation
        case currentUnits = "current_units"
        case current
        case hourlyUnits = "hourly_units"
        case hourly
    }
}

struct CurrentUnits: Codable {
    let time: String
    let interval: String
    let temperature2M: String
    let windSpeed10M: String

    enum CodingKeys: String, CodingKey {
        case time, interval
        case temperature2M = "temperature_2m"
        case windSpeed10M = "wind_speed_10m"
    }
}

struct CurrentWeather: Codable {
    let time: String
    let interval: Int
    let temperature2M: Double
    let windSpeed10M: Double

    enum CodingKeys: String, CodingKey {
        case time, interval
        case temperature2M = "temperature_2m"
        case windSpeed10M = "wind_speed_10m"
    }
}

struct HourlyUnits: Codable {
    let time: String
    let temperature2M: String
    let relativeHumidity2M: String?
    let windSpeed10M: String

    enum CodingKeys: String, CodingKey {
        case time
        case temperature2M = "temperature_2m"
        case relativeHumidity2M = "relative_humidity_2m"
        case windSpeed10M = "wind_speed_10m"
    }
}

struct HourlyWeather: Codable {
    let time: [String]
    let temperature2M: [Double]
    let relativeHumidity2M: [Int]?
    let windSpeed10M: [Double]

    enum CodingKeys: String, CodingKey {
        case time
        case temperature2M = "temperature_2m"
        case relativeHumidity2M = "relative_humidity_2m"
        case windSpeed10M = "wind_speed_10m"
    }
}
